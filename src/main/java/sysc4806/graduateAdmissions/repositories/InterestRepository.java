package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;

import java.util.List;

/**
 * This repository enables retrieval of Interest objects from persistent storage.
 * Interests can be fetched by id, or by department.
 *
 * @author luke
 */
public interface InterestRepository extends CrudRepository<Interest, Long> {
    List<Interest> findByDepartment(Department department);
    List<Interest> findByKeyword(String keyword);
}
