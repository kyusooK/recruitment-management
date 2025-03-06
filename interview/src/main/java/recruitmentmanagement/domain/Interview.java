package recruitmentmanagement.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import recruitmentmanagement.InterviewApplication;

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
    public void onPostPersist() {}

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

        recruitmentmanagement.external.Reservation reservation = new recruitmentmanagement.external.Reservation();

        reservation.setTaskId(this.getId().toString());
        reservation.setTitle("면접 결과 안내");
        reservation.setNow(true);
        if(saveInterviewresultCommand.getPassed().equals(true)){
            reservation.setDescription("면접 통과를 축하드립니다!" + " 면접 점수: " + saveInterviewresultCommand.getInterviewScore());
            

        }else{
            reservation.setDescription("저희 회사에 지원해주셔서 감사합니다. 안타깝게도 귀하의 면접 결과, 불합격되었음을 알려드립니다 ");
        }

        InterviewApplication.applicationContext
            .getBean(recruitmentmanagement.external.ReservationService.class)
            .createReservation(reservation);

        this.setInterviewScore(saveInterviewresultCommand.getInterviewScore());
        this.setPassed(saveInterviewresultCommand.getPassed());

        InterviewResultSaved interviewResultSaved = new InterviewResultSaved(this);
        interviewResultSaved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void setInterviewSchedule(ResumePassed resumePassed) {
        
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 3);
        Date interviewCalendar = calendar.getTime();

        Interview interview = new Interview();
        interview.setLocation("8F 워크라운지");
        interview.setInterviewDate(interviewCalendar);
        
        recruitmentmanagement.external.Reservation reservation = new recruitmentmanagement.external.Reservation();
        
        reservation.setTaskId(resumePassed.getId().toString());
                reservation.setTitle("면접 안내");
                reservation.setDescription(
                    "저희 회사 채용공고에 지원해주셔서 진심으로 감사드립니다. 서류 심사에 통과되어 면접 일정에 관해 공유 드립니다. " 
                    + " 면접 일시: "
                    + interview.getInterviewDate() 
                    + " 면접 장소: "
                    + interview.getLocation()
                    );
                
                reservation.setNow(true);

        InterviewApplication.applicationContext
            .getBean(recruitmentmanagement.external.ReservationService.class)
            .createReservation(reservation);
        

        ScheduleSet scheduleSet = new ScheduleSet(interview);
        scheduleSet.publishAfterCommit();

    }
}
