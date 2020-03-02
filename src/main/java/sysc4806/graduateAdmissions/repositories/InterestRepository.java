package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;

import java.util.List;

public interface InterestRepository extends CrudRepository<Interest, Long> {
    List<Interest> findByDepartment(Department department);
    Interest findById(long id);
}
