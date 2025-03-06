package recruitmentmanagement.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import recruitmentmanagement.domain.*;

@Component
public class InterviewHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Interview>> {

    @Override
    public EntityModel<Interview> process(EntityModel<Interview> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/saveinterviewresult"
                )
                .withRel("saveinterviewresult")
        );

        return model;
    }
}
