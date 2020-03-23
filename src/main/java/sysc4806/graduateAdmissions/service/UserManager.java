package sysc4806.graduateAdmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.repositories.InterestRepository;
import sysc4806.graduateAdmissions.repositories.RoleRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
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
            return userRepository.findByRole(myRole);
        }
        return Collections.emptyList();
    }

    public Collection<User> getUsersByInterestId(Long interestId) {
        Optional<Interest> interest = interestRepository.findById(interestId);
        if (interest.isPresent()) {
            Interest myInterest = interest.get();
            return userRepository.findByInterests(myInterest);
        }
        return Collections.emptyList();
    }

    public Collection<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
