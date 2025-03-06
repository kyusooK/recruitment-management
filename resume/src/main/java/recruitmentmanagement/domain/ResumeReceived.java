package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import recruitmentmanagement.domain.*;
import recruitmentmanagement.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ResumeReceived extends AbstractEvent {

    private Long resumeId;
    private String name;
    private String email;
    private String phoneNumber;
    private String career;
    private String qualifications;
    private String motivation;

    public ResumeReceived(Resume aggregate) {
        super(aggregate);
    }

    public ResumeReceived() {
        super();
    }
}
//>>> DDD / Domain Event
