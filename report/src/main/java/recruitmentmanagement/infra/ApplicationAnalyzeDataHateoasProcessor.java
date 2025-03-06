package recruitmentmanagement.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import recruitmentmanagement.domain.*;

@Component
public class ApplicationAnalyzeDataHateoasProcessor
    implements
        RepresentationModelProcessor<EntityModel<ApplicationAnalyzeData>> {

    @Override
    public EntityModel<ApplicationAnalyzeData> process(
        EntityModel<ApplicationAnalyzeData> model
    ) {
        return model;
    }
}
