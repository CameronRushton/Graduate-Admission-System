package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Session;
import sysc4806.graduateAdmissions.model.UserAccount;

import java.util.List;

/**
 * This repository enables retrieval of Session objects from persistent storage.
 * Sessions are fetched by id or user.
 *
 * @author luke
 */
public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> findById(String id);
    List<Session> findByUser(UserAccount user);
}
