package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ScheduleSet extends AbstractEvent {

    private Long id;
    private ResumeId resumeId;
    private Date interviewDate;
    private String location;

    public ScheduleSet(Interview aggregate) {
        super(aggregate);
    }

    public ScheduleSet() {
        super();
    }
}
//>>> DDD / Domain Event
