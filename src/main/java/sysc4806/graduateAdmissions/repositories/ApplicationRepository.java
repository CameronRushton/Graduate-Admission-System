package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Application;

import java.util.List;

/**
 * This repository enables retrieval of Application objects from persistent storage.
 * Applications can be fetched by id, or by applicant.
 *
 * @author Madelyn
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findByApplicant_id(Long id);
    //TODO; make findBy professor (may be different because is part of a collection)
}
