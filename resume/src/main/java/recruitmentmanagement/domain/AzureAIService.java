package recruitmentmanagement.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.*;

@Service
public class AzureAIService {

    public static class AISummaryResponse {
        private final String summary;
        private final int score;

        public AISummaryResponse(String summary, int score) {
            this.summary = summary;
            this.score = score;
        }

        public String getSummary() {
            return summary;
        }

        public int getScore() {
            return score;
        }
    }

    @Value("${azure.openai.endpoint}")
    private String azureEndpoint;
    
    @Value("${azure.openai.key}")
    private String azureKey;

    @Value("${azure.openai.deployment}")
    private String deploymentName;

    private final RestTemplate restTemplate;
    
    public AzureAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public AISummaryResponse analyzeResume(String career, String qualifications, String motivation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", azureKey);
        
        String prompt = String.format(
            "다음 이력서를 분석하고, 점수(0-100)를 매겨주세요. 응답 형식은 반드시 다음과 같이 해주세요: '점수: [점수]\\n\\n[분석내용]'\n\n경력사항:\n%s\n\n자격요건:\n%s\n\n지원동기:\n%s",
            career.replace("\n", "\\n"),
            qualifications.replace("\n", "\\n"),
            motivation.replace("\n", "\\n")
        );
    
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
    
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", Collections.singletonList(message));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 800);
    
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
    
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                azureEndpoint + "/openai/deployments/" + deploymentName + "/chat/completions?api-version=2023-05-15",
                request,
                String.class
            );
            
            // Check if response body is null
            if (response.getBody() == null) {
                throw new RuntimeException("Response body is null");
            }
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String content = root.path("choices").get(0).path("message").path("content").asText();
            
            // 응답에서 점수와 내용 분리
            String[] parts = content.split("\\n\\n", 2);
            int score = Integer.parseInt(parts[0].split(": ")[1]);
            String summary = parts[1];
            
            return new AISummaryResponse(summary, score);
        } catch (Exception e) {
            throw new RuntimeException("Failed to analyze resume: " + e.getMessage(), e);
        }
    }
}