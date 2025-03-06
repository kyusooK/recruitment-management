package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AnalyzeDataUpdated extends AbstractEvent {

    private Long id;
    private Integer totalApplyCount;
    private Integer passedCount;

    public AnalyzeDataUpdated(ApplicationAnalyzeData aggregate) {
        super(aggregate);
    }

    public AnalyzeDataUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
