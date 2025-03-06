package recruitmentmanagement.domain;

import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

@Data
@ToString
public class ResumeSummerized extends AbstractEvent {

    private Long resumeId;
    private String name;
    private String email;
    private String phoneNumber;
    private String career;
    private String qualifications;
    private String motivation;
    private String summation;
    private Integer summationScore;
}
