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
import recruitmentmanagement.domain.AnalyzeDataUpdated;

@Entity
@Table(name = "ApplicationAnalyzeData_table")
@Data
//<<< DDD / Aggregate Root
public class ApplicationAnalyzeData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer totalApplyCount;

    private Integer passedCount;

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
        AnalyzeDataUpdated analyzeDataUpdated = new AnalyzeDataUpdated(applicationAnalyzeData);
        analyzeDataUpdated.publishAfterCommit();
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
            AnalyzeDataUpdated analyzeDataUpdated = new AnalyzeDataUpdated(applicationAnalyzeData);
            analyzeDataUpdated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
