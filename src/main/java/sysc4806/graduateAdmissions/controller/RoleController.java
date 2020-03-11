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
     * Get Role by id or return all roles
     *
     * @param id optional to find specific role
     * @return JSON containing Roles
     */
    @ResponseBody
    @GetMapping("")
    public ResponseEntity getRole(@RequestParam(required=false) Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
    }

    /**
     * get Role by name
     *
     * @param name the name of the Role to be retrieved
     * @return JSON containing the Role
     */
    @ResponseBody
    @GetMapping("name")
    public ResponseEntity getRoleByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByRoleName(name));
    }

    /**
     * create a new Role
     *
     * @param role the Role to be added to the system
     * @return ResponseEntity describing the outcome of the operation
     */
    @ResponseBody
    @PostMapping("create")
    public ResponseEntity createRoleForm(@RequestBody() Role role) {
        repository.save(role);
        return ResponseEntity.ok("Role added");
    }

    /**
     *
     * @param id the id of the Role to delete
     * @return ResponseEntity describing the outcome of the operation
     */
    @DeleteMapping("{id}")
    @CrossOrigin
    public ResponseEntity deleteRole(@PathVariable("id") Long id) {
        Optional<Role> role = repository.findById(id);
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
     * @param id id of the Role to be updated
     * @param privilege the privilege to be added
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping("add")
    public ResponseEntity addRolePrivilege(@RequestParam Long id, @RequestBody Privilege privilege){
        Optional<Role> role = repository.findById(id);
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
     * @param id id of the Role to be updated
     * @param privilege the privilege to be removed
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping("remove")
    public ResponseEntity removeRolePrivilege(@RequestParam Long id, @RequestBody Privilege privilege){
        Optional<Role> role = repository.findById(id);
        if(role.isPresent()){
            Role r = role.get();
            r.removePrivilege(privilege);
            repository.save(r);
            return ResponseEntity.ok("removed privilege " + privilege.getId() + " from Role");
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
