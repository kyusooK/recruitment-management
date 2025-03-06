package recruitmentmanagement.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import recruitmentmanagement.domain.*;

@Component
public class ResumeHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Resume>> {

    @Override
    public EntityModel<Resume> process(EntityModel<Resume> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/summerizeaibasedresume"
                )
                .withRel("summerizeaibasedresume")
        );

        return model;
    }
}
