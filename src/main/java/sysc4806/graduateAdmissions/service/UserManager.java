package sysc4806.graduateAdmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysc4806.graduateAdmissions.dto.TermDTO;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.repositories.InterestRepository;
import sysc4806.graduateAdmissions.repositories.RoleRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;

import javax.validation.Valid;
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

    public Optional<User> createNewUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            return Optional.empty();
        }
        return Optional.of(userRepository.save(user));
    }

    // TODO: replace mapping with Mapper object
    public Optional<User> updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            if (user.getFirstName() != null && !user.getFirstName().isEmpty()) existingUser.get().setFirstName(user.getFirstName());
            if (user.getLastName() != null && !user.getLastName().isEmpty()) existingUser.get().setLastName(user.getLastName());
            if (user.getRole() != null) existingUser.get().setRole(user.getRole());
            if (user.getRole() != null) existingUser.get().setInterests(user.getInterests());
            if (user.getRole() != null) existingUser.get().setApplications(user.getApplications());
            if (user.getEmail() != null && !user.getEmail().isEmpty()) existingUser.get().setEmail(user.getEmail());
            return Optional.of(userRepository.save(existingUser.get()));
        }
        return Optional.empty();
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
