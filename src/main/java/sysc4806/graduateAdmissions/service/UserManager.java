package sysc4806.graduateAdmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.repositories.RoleRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserManager {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public Collection<User> getUsersByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if (role.isPresent()) {
            Role myRole = role.get();
            System.out.println(Arrays.toString(userRepository.findByRole(myRole).toArray()));
        }
        return null;
    }

}
