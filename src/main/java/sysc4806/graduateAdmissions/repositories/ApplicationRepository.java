package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Application;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.User;

import java.util.List;

/**
 * This repository enables retrieval of Application objects from persistent storage.
 * Applications can be fetched by id, or by applicant.
 *
 * @author Madelyn
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findByApplicant(User applicant);
    //TODO; make findBy proffessor (may be diffrent because is part of a collection)
}
