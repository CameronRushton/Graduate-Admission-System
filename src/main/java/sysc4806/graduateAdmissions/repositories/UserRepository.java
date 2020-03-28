package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sysc4806.graduateAdmissions.model.UserAccount;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.Role;

import java.util.List;

/**
 * This repository will be used for persistent storage of Users
 *
 * @author Kevin Sun
 */
@Repository
public interface UserRepository extends CrudRepository<UserAccount, Long> {
    List<UserAccount> findByRole(Role role);
    List<UserAccount> findByRole_roleName(String roleName);
    List<UserAccount> findByInterests(Interest interest);
    List<UserAccount> findByEmail(String email);
    List<UserAccount> findByApplications_id(long id);
}
