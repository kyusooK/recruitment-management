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

    private String interviewScore;

    private String position;

    public static ApplicationAnalyzeDataRepository repository() {
        ApplicationAnalyzeDataRepository applicationAnalyzeDataRepository = ReportApplication.applicationContext.getBean(
            ApplicationAnalyzeDataRepository.class
        );
        return applicationAnalyzeDataRepository;
    }

    //<<< Clean Arch / Port Method
    public static void collectData(InterviewResultSaved interviewResultSaved) {
        //implement business logic here:

        /** Example 1:  new item 
        ApplicationAnalyzeData applicationAnalyzeData = new ApplicationAnalyzeData();
        repository().save(applicationAnalyzeData);

        AnalyzeDataRegistered analyzeDataRegistered = new AnalyzeDataRegistered(applicationAnalyzeData);
        analyzeDataRegistered.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if interviewResultSaved.resumeId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> interviewMap = mapper.convertValue(interviewResultSaved.getResumeId(), Map.class);

        repository().findById(interviewResultSaved.get???()).ifPresent(applicationAnalyzeData->{
            
            applicationAnalyzeData // do something
            repository().save(applicationAnalyzeData);

            AnalyzeDataRegistered analyzeDataRegistered = new AnalyzeDataRegistered(applicationAnalyzeData);
            analyzeDataRegistered.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
