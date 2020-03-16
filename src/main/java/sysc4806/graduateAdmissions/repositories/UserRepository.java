package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.model.Application;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.Role;

import java.util.List;

/**
 * This repository will be used for persistent storage of Users
 *
 * @author Kevin Sun
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<Privilege> findByUser(User user);
    List<Privilege> findByRole(Role role);
    List<Privilege> findByInterest(Interest interest);
}
