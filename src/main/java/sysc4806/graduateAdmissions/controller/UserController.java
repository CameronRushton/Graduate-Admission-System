//package sysc4806.graduateAdmissions.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import sysc4806.graduateAdmissions.model.User;
//import sysc4806.graduateAdmissions.model.Role;
//import sysc4806.graduateAdmissions.model.Application;
//import sysc4806.graduateAdmissions.model.Interest;
//import sysc4806.graduateAdmissions.repositories.UserRepository;
//
//import java.util.Optional;
//
///**
// * Controller for CRUD operations on User
// *
// * @author Kevin Sun
// */
//@RestController
//@RequestMapping("/user/")
//public class UserController {
//    @Autowired
//    private UserRepository repository;
//
//    /**
//     * get all Users or return User specified by id
//     *
//     * @param id optional to return only User with specified id
//     * @return JSON containing the requested User(s)
//     */
//    @GetMapping("")
//    public ResponseEntity getUser(@RequestParam(required=false) Long id) {
//        if(id == null)
//            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
//        else
//            return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
//    }
//
//    /**
//     * get all Privilege(s) with a specified Role
//     *
//     * @param role the Role of the returned User
//     * @return JSON containing the User(s)
//     */
//    @GetMapping("role")
//    public ResponseEntity getUserofRole(@RequestParam() Role role){
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findByRole(role));
//    }
//
//    /**
//     * get all User(s) with a specified Interest
//     *
//     * @param interest the Interest of users to be returned
//     * @return JSON containing the User(s)
//     */
//    @GetMapping("interest")
//    public ResponseEntity getUserOfInterest(@RequestParam() Interest interest){
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findByInterest(interest));
//    }
//
//    /**
//     * Create a new user in the system
//     *
//     * @param user the Privilege to be created
//     * @return ResponseEntity describing the outcome of the operation
//     */
//    @PostMapping("create")
//    public ResponseEntity createUser(@RequestBody User user){
//        repository.save(user);
//        return ResponseEntity.ok("User added");
//    }
//
//    /**
//     * Delete Privilege with specified id
//     *
//     * @param id id of the User to be deleted
//     * @return ResponseEntity describing the outcome of the operation
//     */
//    @DeleteMapping("{id}")
//    @CrossOrigin
//    public ResponseEntity deleteUser(@PathVariable("id") Long id){
//        Optional<User> user = repository.findById(id);
//        if(user.isPresent()){
//            repository.deleteById(id);
//            return ResponseEntity.ok("user with id " + user.get().getId() + " deleted");
//        } else {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    /**
//     * update a specified User
//     *
//     * @param user the User to be updated
//     * @return ResponseEntity describing the outcome of the operation
//     */
//    @PostMapping("updateUser")
//    public ResponseEntity updateUser(@RequestBody User user){
//        repository.save(user);
//        return ResponseEntity.ok("user with id " + user.getId() + " updated");
//    }
//
//}
