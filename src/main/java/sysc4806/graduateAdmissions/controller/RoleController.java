package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Privilege;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.repositories.RoleRepository;

import java.util.Optional;

/**
 * Controller for CRUD operations on Role
 *
 * @author Eric
 */
@RestController
@RequestMapping("/role/")
public class RoleController {
    @Autowired
    RoleRepository repository;

    /**
     * Get Role by name or return all roles
     *
     * @param name optional to find specific role
     * @return JSON containing Roles
     */
    @GetMapping("")
    public ResponseEntity getRole(@RequestParam(required=false) String name) {
        if(name == null)
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(repository.findByRoleName(name));
    }

    /**
     * create a new Role
     *
     * @param role the Role to be added to the system
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping("create")
    public ResponseEntity createRole(@RequestBody() Role role) {
        repository.save(role);
        return ResponseEntity.ok("Role added");
    }

    /**
     *
     * @param name the roleName of the Role to delete
     * @return ResponseEntity describing the outcome of the operation
     */
    @DeleteMapping("{name}")
    @CrossOrigin
    public ResponseEntity deleteRole(@PathVariable("name") String name) {
        Optional<Role> role = repository.findByRoleName(name);
        if(role.isPresent()){
            repository.delete(role.get());
            return ResponseEntity.ok(role.get().getRoleName() + " Role deleted");
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update a specified Role
     *
     * @param role the Role to be updated
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping("update")
    public ResponseEntity updateRole(@RequestBody Role role){
        repository.save(role);
        return ResponseEntity.ok(role.getRoleName() + " Role updated");
    }

    /**
     * add Privilege from Role
     *
     * @param name roleName of the Role to be updated
     * @param privilege the privilege to be added
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping("add")
    public ResponseEntity addRolePrivilege(@RequestParam String name, @RequestBody Privilege privilege){
        Optional<Role> role = repository.findByRoleName(name);
        if(role.isPresent()){
            Role r = role.get();
            r.addPrivilege(privilege);
            repository.save(r);
            return ResponseEntity.ok("added privilege " + privilege.getId() + " too Role");
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * remove Privilege from Role
     *
     * @param name roleName of the Role to be updated
     * @param privilege the privilege to be removed
     * @return ResponseEntity describing the outcome of the operation
     */
    @DeleteMapping("remove")
    public ResponseEntity removeRolePrivilege(@RequestParam String name, @RequestBody Privilege privilege){
        Optional<Role> role = repository.findByRoleName(name);
        if(role.isPresent()){
            Role r = role.get();
            r.removePrivilege(privilege);
            repository.save(r);
            return ResponseEntity.ok("Removed privilege " + privilege.getOwner() + " " + privilege.getOperation() + " on " + privilege.getTarget() + " from role " + r.getRoleName());
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
