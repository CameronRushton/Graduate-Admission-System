package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.User;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.repositories.UserRepository;
import sysc4806.graduateAdmissions.service.UserManager;

import java.util.HashSet;
import java.util.Optional;

/**
 * Controller for CRUD operations on User
 *
 * @author Kevin Sun
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserManager userManager;

    /**
     * get all Users or return User specified by id
     *
     * @param id optional to return only User with specified id
     * @return JSON containing the requested User(s)
     */
    @GetMapping("")
    public ResponseEntity getUser(@RequestParam(required=false) Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
    }

    /**
     * get all Privilege(s) with a specified Role
     *
     * @param roleName the Role of the returned User
     * @return JSON containing the User(s)
     */
    @GetMapping("role")
    public ResponseEntity getUserOfRole(@RequestParam String roleName) {
        userManager.getUsersByRoleName(roleName);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * get all User(s) with a specified Interest
     *
     * @param keyword the String of keywords to be returned
     * @return JSON containing the User(s)
     */
    @GetMapping("keyword")
    public ResponseEntity getUserOfKeyword(@RequestParam() String keyword){
        userManager.getUsersByKeyword(keyword);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * get all User(s) with a specified email
     *
     * @param email the String of email to be returned
     * @return JSON containing the User(s)
     */
    @GetMapping("keyword")
    public ResponseEntity getUserOfEmail(@RequestParam() String email){
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
    @CrossOrigin
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
    @PostMapping("updateUser")
    public ResponseEntity updateUser(@RequestBody User user){
        repository.save(user);
        return ResponseEntity.ok("user with id " + user.getId() + " updated");
    }

}
