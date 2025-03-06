package recruitmentmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import recruitmentmanagement.InterviewApplication;
import recruitmentmanagement.domain.ScheduleSet;

@Entity
@Table(name = "Interview_table")
@Data
//<<< DDD / Aggregate Root
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date interviewDate;

    private String location;

    private Integer interviewScore;

    private Boolean passed;

    @Embedded
    private ResumeId resumeId;

    public static InterviewRepository repository() {
        InterviewRepository interviewRepository = InterviewApplication.applicationContext.getBean(
            InterviewRepository.class
        );
        return interviewRepository;
    }

    //<<< Clean Arch / Port Method
    public void saveInterviewresult(
        SaveInterviewresultCommand saveInterviewresultCommand
    ) {
        //implement business logic here:

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        recruitmentmanagement.external.Reservation reservation = new recruitmentmanagement.external.Reservation();
        // mappings goes here
        InterviewApplication.applicationContext
            .getBean(recruitmentmanagement.external.ReservationService.class)
            .createReservation(reservation);

        InterviewResultSaved interviewResultSaved = new InterviewResultSaved(
            this
        );
        interviewResultSaved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void setInterviewSchedule(ResumePassed resumePassed) {
        //implement business logic here:

        /** Example 1:  new item 
        Interview interview = new Interview();
        repository().save(interview);

        ScheduleSet scheduleSet = new ScheduleSet(interview);
        scheduleSet.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if resumePassed.userId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> resumeMap = mapper.convertValue(resumePassed.getUserId(), Map.class);

        repository().findById(resumePassed.get???()).ifPresent(interview->{
            
            interview // do something
            repository().save(interview);

            ScheduleSet scheduleSet = new ScheduleSet(interview);
            scheduleSet.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
