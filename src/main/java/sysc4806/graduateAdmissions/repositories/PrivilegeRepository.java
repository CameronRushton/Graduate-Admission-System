package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Operation;
import sysc4806.graduateAdmissions.model.Owner;
import sysc4806.graduateAdmissions.model.Privilege;
import sysc4806.graduateAdmissions.model.Target;

import java.util.List;

/**
 * This repository will be used for persistent storage of Privileges
 *
 * @author Eric
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    List<Privilege> findByOwner(Owner owner);
    List<Privilege> findByTarget(Target target);
    List<Privilege> findByOperation(Operation operation);
}
