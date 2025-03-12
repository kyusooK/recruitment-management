package recruitmentmanagement.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import recruitmentmanagement.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "passedReports",
    path = "passedReports"
)
public interface PassedReportRepository
    extends PagingAndSortingRepository<PassedReport, Long> {}
