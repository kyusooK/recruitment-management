package recruitmentmanagement.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import recruitmentmanagement.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "resumes", path = "resumes")
public interface ResumeRepository
    extends PagingAndSortingRepository<Resume, Long> {}
