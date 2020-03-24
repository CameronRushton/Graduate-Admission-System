package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Session;

/**
 * This repository enables retrieval of Session objects from persistent storage.
 * Sessions are fetched by id.
 *
 * @author luke
 */
public interface SessionRepository extends CrudRepository<Session, Long> {}
