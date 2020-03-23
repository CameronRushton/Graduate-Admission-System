package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.repositories.UserRepository;
import sysc4806.graduateAdmissions.service.UserManager;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controller for CRUD operations on User
 *
 * @author Kevin Sun
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserManager userManager;

    /**
     * GET /users
     *
     * @return JSON containing the requested Users
     */
    @GetMapping
    public ResponseEntity queryAllUsers() {
        Iterable<User> users = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    /**
     * get User specified by id
     *
     * @param userId the user's id
     * @return JSON containing the requested User(s)
     */
    @GetMapping(path="/{id}")
    public ResponseEntity queryTerm(@PathVariable("id") Long userId) {
        Optional<User> user = repository.findById(userId);
        return ResponseEntity.status(user.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(user.orElse(null));
    }

    /**
     * get all Users with a specified role name
     *
     * @param roleName the Role of the returned User
     * @return JSON containing the User(s)
     */
    @GetMapping("/role")
    public ResponseEntity getUserOfRole(@RequestParam("roleName") String roleName) {
        return ResponseEntity.status(HttpStatus.OK).body(userManager.getUsersByRoleName(roleName));
    }

    /**
     * get all Users with a specified interest ID
     *
     * @param interest_id the interest id of users to be returned
     * @return JSON containing the User(s)
     */
    @GetMapping("/interests/{id}")
    public ResponseEntity getUserOfInterest(@PathVariable Long interest_id) {
        return ResponseEntity.status(HttpStatus.OK).body(userManager.getUsersByInterestId(interest_id));
    }

    /**
     * get all User(s) with a specified email
     *
     * @param email the String of email to be returned
     * @return JSON containing the User(s)
     */
    @GetMapping
    public ResponseEntity getUserOfEmail(@RequestParam("email") String email){
        userManager.getUsersByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Create a new user in the system
     *
     * @param user the Privilege to be created
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping()
    public ResponseEntity createUser(@RequestBody User user){
        user.setRole(Role.builder().roleName("Student").privileges(new HashSet<>()).build());
        repository.save(user);
        return ResponseEntity.ok("User added");
    }

    /**
     * Delete Privilege with specified id
     *
     * @param id id of the User to be deleted
     * @return ResponseEntity describing the outcome of the operation
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok("user with id " + user.get().getId() + " deleted");
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update a specified User
     *
     * @param user the User to be updated
     * @return ResponseEntity describing the outcome of the operation
     */
    @PutMapping
    public ResponseEntity updateUser(@RequestBody User user) {
        repository.save(user);
        return ResponseEntity.ok("user with id " + user.getId() + " updated");
    }

}
