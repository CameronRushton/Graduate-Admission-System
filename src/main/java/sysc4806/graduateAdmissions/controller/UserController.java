package sysc4806.graduateAdmissions.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.UserAccount;
import sysc4806.graduateAdmissions.repositories.UserRepository;
import sysc4806.graduateAdmissions.service.UserManager;

import java.util.Optional;

/**
 * Controller for CRUD operations on User
 *
 * @author Kevin Sun
 */
@RestController
@RequestMapping("/users")
@Slf4j
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
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    /**
     * get User specified by id
     *
     * @param userId the user's id
     * @return JSON containing the requested User(s)
     */
    @GetMapping(path="/{id}")
    public ResponseEntity getUserByID(@PathVariable("id") Long userId) {
        Optional<UserAccount> user = repository.findById(userId);
        return ResponseEntity.status(user.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(user.orElse(null));
    }

    /**
     * get all Users with a specified role name
     *
     * @param roleName the case sensitive role name of the user
     * @return JSON containing the User(s)
     */
    @GetMapping("/roles")
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
     * get the user who made a specific application
     *
     * @param id the id of the application
     * @return JSON containing the User who made the application
     */
    @GetMapping("/applicant")
    public ResponseEntity getApplicantByApplicationId(@RequestParam("id") long id){
        log.info(String.valueOf(repository.findByApplications_id(id)));
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByApplications_id(id));
    }

    /**
     * get all User(s) with a specified email
     *
     * @param email the String of email to be returned
     * @return JSON containing the User(s)
     */
    @GetMapping("/email")
    public ResponseEntity getUserOfEmail(@RequestParam("email") String email){
        return ResponseEntity.status(HttpStatus.OK).body(userManager.getUsersByEmail(email));
    }

    /**
     * Create a new user in the system
     *
     * @param userAccount the Privilege to be created
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserAccount userAccount){
        Optional<UserAccount> newUser = userManager.createNewUser(userAccount);
        if (newUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with ID " + userAccount.getId() + " already exists.");
    }

    /**
     * Delete Privilege with specified id
     *
     * @param id id of the User to be deleted
     * @return ResponseEntity describing the outcome of the operation
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        Optional<UserAccount> user = repository.findById(id);
        if(user.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok("User with id " + user.get().getId() + " deleted");
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update a specified User
     *
     * @param userAccount the User to be updated
     * @return ResponseEntity describing the outcome of the operation
     */
    @PutMapping
    @CrossOrigin
    public ResponseEntity updateUser(@RequestBody UserAccount userAccount) {
        Optional<UserAccount> updatedUser = userManager.updateUser(userAccount);
        if (updatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
