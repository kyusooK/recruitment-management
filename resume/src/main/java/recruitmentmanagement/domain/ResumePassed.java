package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ResumePassed extends AbstractEvent {

    private Long id;
    private UserId userId;
    private String summation;
    private Integer summationScore;

    public ResumePassed(Resume aggregate) {
        super(aggregate);
    }

    public ResumePassed() {
        super();
    }
}
//>>> DDD / Domain Event
