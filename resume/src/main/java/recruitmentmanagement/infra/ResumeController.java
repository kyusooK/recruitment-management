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
// @RequestMapping(value="/resumes")
@Transactional
public class ResumeController {

    @Autowired
    ResumeRepository resumeRepository;

    @RequestMapping(
        value = "/resumes/{id}/summerizeaibasedresume",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Resume summerizeAibasedresume(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /resume/summerizeAibasedresume  called #####"
        );
        Optional<Resume> optionalResume = resumeRepository.findById(id);

        optionalResume.orElseThrow(() -> new Exception("No Entity Found"));
        Resume resume = optionalResume.get();
        resume.summerizeAibasedresume();

        resumeRepository.save(resume);
        return resume;
    }
}
//>>> Clean Arch / Inbound Adaptor
