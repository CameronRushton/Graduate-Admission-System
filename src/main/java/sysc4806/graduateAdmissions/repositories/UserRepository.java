package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
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
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByRole(Role role);
    List<User> findByInterests(Interest interests);
}
