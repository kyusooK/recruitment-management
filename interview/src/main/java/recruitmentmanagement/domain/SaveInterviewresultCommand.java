package recruitmentmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class SaveInterviewresultCommand {

    private Long id;
    private Integer interviewScore;
    private Boolean passed;
}
