package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.User;

import java.util.List;

/**
 * Used for persistent storage of User objects
 *
 * @author luke
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByEmail(String email);
}
