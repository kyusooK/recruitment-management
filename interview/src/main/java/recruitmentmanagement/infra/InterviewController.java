package recruitmentmanagement.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recruitmentmanagement.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/interviews")
@Transactional
public class InterviewController {

    @Autowired
    InterviewRepository interviewRepository;

    @RequestMapping(
        value = "/interviews/{id}/saveinterviewresult",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Interview saveInterviewresult(
        @PathVariable(value = "id") Long id,
        @RequestBody SaveInterviewresultCommand saveInterviewresultCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /interview/saveInterviewresult  called #####"
        );
        Optional<Interview> optionalInterview = interviewRepository.findById(
            id
        );

        optionalInterview.orElseThrow(() -> new Exception("No Entity Found"));
        Interview interview = optionalInterview.get();
        interview.saveInterviewresult(saveInterviewresultCommand);

        interviewRepository.save(interview);
        return interview;
    }
}
//>>> Clean Arch / Inbound Adaptor
