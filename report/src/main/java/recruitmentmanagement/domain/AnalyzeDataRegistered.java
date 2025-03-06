package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AnalyzeDataRegistered extends AbstractEvent {

    private Long id;
    private Integer totalApplyCount;
    private Integer passedCount;

    public AnalyzeDataRegistered(ApplicationAnalyzeData aggregate) {
        super(aggregate);
    }

    public AnalyzeDataRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
