package recruitmentmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import recruitmentmanagement.ResumeApplication;
import recruitmentmanagement.domain.ResumeReceived;

@Entity
@Table(name = "Resume_table")
@Data
//<<< DDD / Aggregate Root
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UserId userId;

    private String position;

    private String career;

    private String qualifications;

    private String motivation;

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

    //<<< Clean Arch / Port Method
    public void summerizeResume() {
        //implement business logic here:

        ResumeSummerized resumeSummerized = new ResumeSummerized(this);
        resumeSummerized.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
