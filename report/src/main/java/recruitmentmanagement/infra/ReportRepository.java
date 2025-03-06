package recruitmentmanagement.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import recruitmentmanagement.domain.*;

@RepositoryRestResource(collectionResourceRel = "reports", path = "reports")
public interface ReportRepository
    extends PagingAndSortingRepository<Report, Long> {}
