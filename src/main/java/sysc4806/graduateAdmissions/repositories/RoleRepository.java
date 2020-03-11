package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Role;

/**
 * This repository will be used for persistent storage of roles
 *
 * @author Eric
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
