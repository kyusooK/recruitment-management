package recruitmentmanagement.domain;

import recruitmentmanagement.domain.ResumeReceived;
import recruitmentmanagement.domain.ResumePassed;
import recruitmentmanagement.ResumeApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name="Resume_table")
@Data

//<<< DDD / Aggregate Root
public class Resume  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
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
    public void onPostPersist(){


        ResumeReceived resumeReceived = new ResumeReceived(this);
        resumeReceived.publishAfterCommit();



        ResumePassed resumePassed = new ResumePassed(this);
        resumePassed.publishAfterCommit();

    
    }

    public static ResumeRepository repository(){
        ResumeRepository resumeRepository = ResumeApplication.applicationContext.getBean(ResumeRepository.class);
        return resumeRepository;
    }

    public void summerize resume(){
        //
    }


//<<< Clean Arch / Port Method
    public void summerizeAibasedresume(){
        
        //implement business logic here:
        


        ResumeNotPassed resumeNotPassed = new ResumeNotPassed(this);
        resumeNotPassed.publishAfterCommit();
    }
//>>> Clean Arch / Port Method



}
//>>> DDD / Aggregate Root
