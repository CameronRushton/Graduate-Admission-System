package sysc4806.graduateAdmissions.repositories;

import sysc4806.graduateAdmissions.model.User;

import java.util.List;

/**
 * Used for persistent storage of User objects
 *
 * @author luke
 */
public interface UserRepository {
    List<User> findByEmail();
}
