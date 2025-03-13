package recruitmentmanagement.domain;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import recruitmentmanagement.ReportApplication;

@Entity
@Table(name = "ApplicationAnalyzeData_table")
@Data
//<<< DDD / Aggregate Root
public class ApplicationAnalyzeData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String applicantName;

    private Integer summationScore;

    private Integer interviewScore;

    private String position;

    public static ApplicationAnalyzeDataRepository repository() {
        ApplicationAnalyzeDataRepository applicationAnalyzeDataRepository = ReportApplication.applicationContext.getBean(
            ApplicationAnalyzeDataRepository.class
        );
        return applicationAnalyzeDataRepository;
    }

    //<<< Clean Arch / Port Method
    public static void collectData(InterviewResultSaved interviewResultSaved) {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> interviewMap = mapper.convertValue(interviewResultSaved.getResumeId(), Map.class);
    
        // ID 추출 전 로그 추가
        System.out.println("Interview Map: " + interviewMap);
    
        // Extract the resumeId correctly
        Object idObject = interviewMap.get("id");
        Long resumeId = null;
        if (idObject instanceof Map) {
            Map<String, Object> idMap = (Map<String, Object>) idObject;
            resumeId = Long.valueOf(idMap.get("id").toString());
        } else if (idObject instanceof Number) {
            resumeId = ((Number) idObject).longValue();
        } else if (idObject instanceof String) {
            resumeId = Long.valueOf((String) idObject);
        }
    
        // Fetching data from the Resume service
        RestTemplate restTemplate = new RestTemplate();
        String resumeServiceUrl = "http://localhost:8082/resumes/" + resumeId;
        System.out.println("Requesting Resume URL: " + resumeServiceUrl);
        ResponseEntity<Map> resumeInfo = restTemplate.getForEntity(resumeServiceUrl, Map.class);
    
        if (resumeInfo.getBody() != null) {
            Map<String, Object> resumeBody = resumeInfo.getBody();
            
            // Resume body 로그 추가
            System.out.println("Resume Body: " + resumeBody);
    
            // Extract userId correctly
            Map<String, Object> userIdMap = (Map<String, Object>) resumeBody.get("userId");
            Long userId = null;
            if (userIdMap != null) {
                userId = Long.valueOf(userIdMap.get("id").toString());
            }
    
            if (userId != null) {
                // Fetching user data from the User service
                String userServiceUrl = "http://localhost:8084/users/" + userId;
                System.out.println("Requesting User URL: " + userServiceUrl);
                ResponseEntity<Map> userInfo = restTemplate.getForEntity(userServiceUrl, Map.class);
    
                if (userInfo.getBody() != null) {
                    Map<String, Object> userBody = userInfo.getBody();
                    String name = (String) userBody.get("name");
    
                    ApplicationAnalyzeData applicationAnalyzeData = new ApplicationAnalyzeData();
                    applicationAnalyzeData.setApplicantName(name);
                    applicationAnalyzeData.setPosition((String) resumeBody.get("position"));
                    applicationAnalyzeData.setSummationScore((Integer) resumeBody.get("summationScore"));
                    applicationAnalyzeData.setInterviewScore(interviewResultSaved.getInterviewScore());
    
                    repository().save(applicationAnalyzeData);
    
                    AnalyzeDataRegistered analyzeDataRegistered = new AnalyzeDataRegistered(applicationAnalyzeData);
                    analyzeDataRegistered.publishAfterCommit();
                }
            } else {
                System.out.println("User ID is null");
            }
        }
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
