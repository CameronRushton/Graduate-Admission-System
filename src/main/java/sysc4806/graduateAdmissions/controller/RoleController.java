package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Privilege;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.repositories.RoleRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller for CRUD operations on Role
 *
 * @author Eric
 */
@RestController
@RequestMapping("/roles/")
@Transactional
public class RoleController {
    @Autowired
    RoleRepository repository;

    /**
     * Get Role by name or return all roles
     *
     * @param name optional to find specific role
     * @return JSON containing Roles
     */
    @GetMapping
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
    @PostMapping
    public ResponseEntity createRole(@Valid @RequestBody() Role role) {
        repository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role added");
    }

    /**
     *
     * @param name the roleName of the Role to delete
     * @return ResponseEntity describing the outcome of the operation
     */
    @DeleteMapping("{name}")
    @CrossOrigin
    public ResponseEntity deleteRole(@PathVariable("name") String name) {
        if(repository.existsByRoleName(name)){
            repository.deleteByRoleName(name);
            return ResponseEntity.ok(name + " Role deleted");
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * update a specified Role
     *
     * @param role the Role to be updated
     * @return ResponseEntity describing the outcome of the operation
     */
    @PutMapping
    @CrossOrigin
    public ResponseEntity updateRole(@Valid @RequestBody Role role){
        Optional<Role> oldRole = repository.findByRoleName(role.getRoleName());
        if(oldRole.isPresent()){
            repository.save(role);
            return ResponseEntity.ok(role.getRoleName() + " Role updated");
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * add Privilege from Role
     *
     * @param name roleName of the Role to be updated
     * @param privilege the privilege to be added
     * @return ResponseEntity describing the outcome of the operation
     */
    @PutMapping("privilege")
    @CrossOrigin
    public ResponseEntity addRolePrivilege(@RequestParam String name, @Valid @RequestBody Privilege privilege){
        Optional<Role> role = repository.findByRoleName(name);
        if(role.isPresent()){
            Role r = role.get();
            if(!r.hasPrivilege(privilege)) {
                r.addPrivilege(privilege);
                repository.save(r);
                return ResponseEntity.ok("added privilege " + privilege.getId() + " too Role");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("role already has this privilege");
            }
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
    @DeleteMapping("privilege")
    @CrossOrigin
    public ResponseEntity removeRolePrivilege(@RequestParam String name, @Valid @RequestBody Privilege privilege){
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
