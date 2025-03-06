package recruitmentmanagement.domain;

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
    
    public AISummaryResponse analyzeResume(String career, String qualifications, String motivation) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("api-key", azureKey);

    // Using traditional string concatenation instead of text blocks
    String prompt = "다음 이력서 정보를 분석하여 제공해주세요:\n" +
        "1. 간단한 요약\n" +
        "2. 전반적인 품질을 0-100점 사이로 평가\n\n" +
        "경력사항: " + career + "\n" +
        "자격요건: " + qualifications + "\n" +
        "지원동기: " + motivation + "\n\n" +
        "다음 JSON 형식으로 응답해주세요:\n" +
        "{\n" +
        "    \"summary\": \"여기에 요약을 작성\",\n" +
        "    \"score\": 점수\n" +
        "}";

    HttpEntity<String> request = new HttpEntity<>(
        "{\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}", 
        headers
    );

    try {
        ResponseEntity<String> response = restTemplate.postForEntity(
            azureEndpoint + "/openai/deployments/your-deployment-name/chat/completions?api-version=2023-05-15",
            request,
            String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String aiContent = root.path("choices").get(0).path("message").path("content").asText();
        
        JsonNode aiResponse = mapper.readTree(aiContent);
        return new AISummaryResponse(
            aiResponse.get("summary").asText(),
            aiResponse.get("score").asInt()
        );
    } catch (Exception e) {
        // Add proper error handling
        throw new RuntimeException("Failed to analyze resume: " + e.getMessage(), e);
    }
}
}