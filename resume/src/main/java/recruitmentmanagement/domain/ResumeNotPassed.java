package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ResumeNotPassed extends AbstractEvent {

    private Long id;
    private UserId userId;
    private String summation;
    private Integer summationScore;
    private String position;

    public ResumeNotPassed(Resume aggregate) {
        super(aggregate);
    }

    public ResumeNotPassed() {
        super();
    }
}
//>>> DDD / Domain Event
