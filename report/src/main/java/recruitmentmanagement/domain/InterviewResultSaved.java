package recruitmentmanagement.domain;

import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

@Data
@ToString
public class InterviewResultSaved extends AbstractEvent {

    private Long id;
    private Object resumeId;
    private Integer interviewScore;
    private Boolean passed;
}
