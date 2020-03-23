package sysc4806.graduateAdmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.repositories.InterestRepository;
import sysc4806.graduateAdmissions.repositories.RoleRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserManager {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private InterestRepository interestRepository;

    @Autowired
    public UserManager(RoleRepository roleRepository,
                       UserRepository userRepository,
                       InterestRepository interestRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
    }

    public Collection<User> getUsersByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if (role.isPresent()) {
            Role myRole = role.get();
            System.out.println(Arrays.toString(userRepository.findByRole(myRole).toArray()));
        }
        return null;
    }

    public Collection<User> getUsersByInterestId(Long interestId) {
        Optional<Interest> interest = interestRepository.findById(interestId);
        if (interest.isPresent()) {
            Interest myInterest = interest.get();
            System.out.println(Arrays.toString(userRepository.findByInterests(myInterest).toArray()));
        }
        return null;
    }

}
