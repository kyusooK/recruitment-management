package recruitmentmanagement.domain;

import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

@Data
@ToString
public class ResumePassed extends AbstractEvent {

    private Long id;
    private Object userId;
    private String summation;
    private Integer summationScore;
}
