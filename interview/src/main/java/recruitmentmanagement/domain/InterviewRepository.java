package recruitmentmanagement.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import recruitmentmanagement.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "interviews",
    path = "interviews"
)
public interface InterviewRepository
    extends PagingAndSortingRepository<Interview, Long> {}
