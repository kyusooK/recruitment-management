package recruitmentmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import recruitmentmanagement.ResumeApplication;
import recruitmentmanagement.domain.AzureAIService.AISummaryResponse;
import recruitmentmanagement.domain.ResumeReceived;

@Entity
@Table(name = "Resume_table")
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UserId userId;


    private String position;

    @Lob
    private String career;

    @Lob
    private String qualifications;

    @Lob
    private String motivation;

    @Lob
    private String summation;

    private Integer summationScore;

    @PostPersist
    public void onPostPersist() {
        ResumeReceived resumeReceived = new ResumeReceived(this);
        resumeReceived.publishAfterCommit();
    }

    public static ResumeRepository repository() {
        ResumeRepository resumeRepository = ResumeApplication.applicationContext.getBean(
            ResumeRepository.class
        );
        return resumeRepository;
    }

    public void summerizeResume() {
        repository().findById(this.getId()).ifPresent(resume ->{
            AzureAIService azureAIService = ResumeApplication.applicationContext.getBean(AzureAIService.class);
            
            AISummaryResponse aiResponse = azureAIService.analyzeResume(
                this.career,
                this.qualifications,
                this.motivation
            );
            
            this.summation = aiResponse.getSummary();
            this.summationScore = aiResponse.getScore();
        });

        ResumeSummerized resumeSummerized = new ResumeSummerized(this);
        resumeSummerized.publishAfterCommit();
    }
}