package sysc4806.graduateAdmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.repositories.InterestRepository;
import sysc4806.graduateAdmissions.repositories.RoleRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InterestRepository interestRepository;

    public Collection<User> getUsersByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if (role.isPresent()) {
            Role myRole = role.get();
            System.out.println(Arrays.toString(userRepository.findByRole(myRole).toArray()));
        }
        return null;
    }

    public Collection<User> getUsersByKeyword(String keyword) {
        List<Interest> interest = interestRepository.findByKeyword(keyword);
        if (!interest.isEmpty()) {
            String word = interest.getKeyword();
            System.out.println(Arrays.toString(interestRepository.findByKeyword(word).toArray()));
        }
        return null;
    }

    public Collection<User> getUsersByEmail(String email) {
        List<User> mail = userRepository.findByEmail(email);
        if (!mail.isEmpty()) {
            String Email = mail.getEmail();
            System.out.println(Arrays.toString(userRepository.findByEmail(email).toArray()));
        }
        return null;
    }
}
