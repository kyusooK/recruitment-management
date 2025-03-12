package recruitmentmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import recruitmentmanagement.ReportApplication;
import recruitmentmanagement.domain.AnalyzeDataRegistered;

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
        Map<Long, Object> interviewMap = mapper.convertValue(interviewResultSaved.getResumeId(), Map.class);

        Long resumeId = Long.valueOf(interviewMap.get("id").toString());

        // Fetching data from the Resume service
        RestTemplate restTemplate = new RestTemplate();
        String resumeServiceUrl = "http://localhost:8082/resumes/" + resumeId;
        Resume resume = restTemplate.getForObject(resumeServiceUrl, Resume.class);

        ApplicationAnalyzeData applicationAnalyzeData = new ApplicationAnalyzeData();
        applicationAnalyzeData.setPosition(resume.getPosition());
        applicationAnalyzeData.setSummationScore(resume.getSummationScore());
        applicationAnalyzeData.setInterviewScore(interviewResultSaved.getInterviewScore());

        repository().save(applicationAnalyzeData);

        AnalyzeDataRegistered analyzeDataRegistered = new AnalyzeDataRegistered(applicationAnalyzeData);
        analyzeDataRegistered.publishAfterCommit();
        

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
