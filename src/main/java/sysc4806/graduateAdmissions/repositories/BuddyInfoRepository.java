package sysc4806.graduateAdmissions.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import sysc4806.graduateAdmissions.model.BuddyInfo;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    List<BuddyInfo> findByName(String name);

    BuddyInfo findById(long id);
}