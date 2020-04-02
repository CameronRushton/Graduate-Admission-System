package sysc4806.graduateAdmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.UserAccount;
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

    public Optional<UserAccount> createNewUser(UserAccount userAccount) {
        if (userAccount.getId() != null && userRepository.existsById(userAccount.getId())) {
            return Optional.empty();
        }
        return Optional.of(userRepository.save(userAccount));
    }

    // TODO: replace mapping with Mapper object
    public Optional<UserAccount> updateUser(UserAccount userAccount) {
        Optional<UserAccount> existingUser = userRepository.findById(userAccount.getId());
        if (existingUser.isPresent()) {
            if (userAccount.getFirstName() != null && !userAccount.getFirstName().isEmpty()) existingUser.get().setFirstName(userAccount.getFirstName());
            if (userAccount.getLastName() != null && !userAccount.getLastName().isEmpty()) existingUser.get().setLastName(userAccount.getLastName());
            if (userAccount.getRole() != null) existingUser.get().setRole(userAccount.getRole());
            if (userAccount.getInterests() != null) existingUser.get().setInterests(userAccount.getInterests());
            if (userAccount.getApplications() != null) existingUser.get().setApplications(userAccount.getApplications());
            if (userAccount.getEmail() != null && !userAccount.getEmail().isEmpty()) existingUser.get().setEmail(userAccount.getEmail());
            return Optional.of(userRepository.save(existingUser.get()));
        }
        return Optional.empty();
    }

    public Collection<UserAccount> getUsersByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if (role.isPresent()) {
            Role myRole = role.get();
            return userRepository.findByRole(myRole);
        }
        return Collections.emptyList();
    }

    public Collection<UserAccount> getUsersByInterestId(Long interestId) {
        Optional<Interest> interest = interestRepository.findById(interestId);
        if (interest.isPresent()) {
            Interest myInterest = interest.get();
            return userRepository.findByInterests(myInterest);
        }
        return Collections.emptyList();
    }

    public Collection<UserAccount> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
