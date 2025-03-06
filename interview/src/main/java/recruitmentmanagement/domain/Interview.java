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

    @PostPersist
    public void onPostPersist() {

        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> resumeMap = mapper.convertValue(this.getResumeId(), Map.class);

        recruitmentmanagement.external.Reservation reservation = new recruitmentmanagement.external.Reservation();
        
        reservation.setTaskId(this.getId().toString());
                reservation.setTitle("면접 안내");
                reservation.setDescription(
                    "지원자 성명: " 
                    + resumeMap.get("name").toString()
                    + " 면접 일시: "
                    + this.getInterviewDate() 
                    + "면접 장소:  "
                    + this.getLocation());
                
                reservation.setNow(true);


        InterviewApplication.applicationContext
            .getBean(recruitmentmanagement.external.ReservationService.class)
            .createReservation(reservation);

        ScheduleSet scheduleSet = new ScheduleSet(this);
        scheduleSet.publishAfterCommit();
    }

    public static InterviewRepository repository() {
        InterviewRepository interviewRepository = InterviewApplication.applicationContext.getBean(
            InterviewRepository.class
        );
        return interviewRepository;
    }

    //<<< Clean Arch / Port Method
    public void saveInterviewresult() {
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

}
//>>> DDD / Aggregate Root
