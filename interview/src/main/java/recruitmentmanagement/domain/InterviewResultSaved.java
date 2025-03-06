package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class InterviewResultSaved extends AbstractEvent {

    private Long id;
    private ResumeId resumeId;
    private Integer interviewScore;
    private Boolean passed;

    public InterviewResultSaved(Interview aggregate) {
        super(aggregate);
    }

    public InterviewResultSaved() {
        super();
    }
}
//>>> DDD / Domain Event
