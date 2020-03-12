package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Operation;
import sysc4806.graduateAdmissions.model.Owner;
import sysc4806.graduateAdmissions.model.Privilege;
import sysc4806.graduateAdmissions.model.Target;
import sysc4806.graduateAdmissions.repositories.PrivilegeRepository;

import java.util.Optional;

/**
 * Controller for CRUD operations on Privilege
 *
 * @author Eric
 */
@RestController
@RequestMapping("/privilege/")
public class PrivilegeController {
    @Autowired
    private PrivilegeRepository repository;

    /**
     * get all Privilege or return Privilege specified by id
     *
     * @param id optional to return only Privilege with specified id
     * @return JSON containing the requested Privilege(s)
     */
    @GetMapping("")
    public ResponseEntity getPrivilege(@RequestParam(required=false) Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
    }

    /**
     * get all Privilege(s) with a specified Owner
     *
     * @param owner the owner of the returned Privilege
     * @return JSON containing the Privilege(s)
     */
    @GetMapping("owner")
    public ResponseEntity getPrivilegeOfOwner(@RequestParam() Owner owner){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByOwner(owner));
    }

    /**
     * get all Privilege(s) with a specified Target
     *
     * @param target the Target of privileges to be returned
     * @return JSON containing the Privilege(s)
     */
    @GetMapping("target")
    public ResponseEntity getPrivilegeOfTarget(@RequestParam() Target target){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByTarget(target));
    }

    /**
     * get all Privilege(s) with a specified Operation
     *
     * @param operation the Operation of the returned Privilege(s)
     * @return JSON containing the Privilege(s)
     */
    @GetMapping("operation")
    public ResponseEntity getPrivilegeOfOperation(@RequestParam()Operation operation){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByOperation(operation));
    }

    /**
     * create a new privilege in the system
     *
     * @param privilege the Privilege to be created
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping("create")
    public ResponseEntity createPrivilege(@RequestBody Privilege privilege){
        repository.save(privilege);
        return ResponseEntity.ok("Privilege added");
    }

    /**
     * delete Privilege with specified id
     *
     * @param id id of the Privilege to be deleted
     * @return ResponseEntity describing the outcome of the operation
     */
    @DeleteMapping("{id}")
    @CrossOrigin
    public ResponseEntity deletePrivilege(@PathVariable("id") Long id){
        Optional<Privilege> privilege = repository.findById(id);
        if(privilege.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok("privilege with id " + privilege.get().getId() + " deleted");
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update a specified Privilege
     *
     * @param privilege the Privilege to be updated
     * @return ResponseEntity describing the outcome of the operation
     */
    @PostMapping("update")
    public ResponseEntity updatePrivilege(@RequestBody Privilege privilege){
        repository.save(privilege);
        return ResponseEntity.ok("privilege with id " + privilege.getId() + " updated");
    }

}
