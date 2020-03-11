package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Owner;
import sysc4806.graduateAdmissions.model.Privilege;

import java.util.List;

/**
 * This repository will be used for persistent storage of Privileges
 *
 * @author Eric
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    List<Privilege> findByOwner(Owner owner);
}
