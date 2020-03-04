package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Role;
import sysc4806.graduateAdmissions.repositories.RoleRepository;

import java.util.Optional;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepository repo;

    /**
     * Get Role by id or return all roles
     *
     * @param id optional to find specific role
     * @return Json containing Roles
     */
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity getRole(@RequestParam(required=false) Long id, @RequestParam(required = false) String name) {
        if(id == null && name == null)
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
        else if(id != null)
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
        else
            return ResponseEntity.status(HttpStatus.OK).body(repo.findByName(name));
    }

    /**
     * serves a form to create a new Interest
     *
     * @param model the Model to pass values to the thymeleaf template
     * @return the name of the form to create a new Interest
     */
    @GetMapping("/create")
    public String createRoleForm(Model model) {
        model.addAttribute("role", Role.builder().build());
        model.addAttribute("postLocation", "/role/create");
        model.addAttribute("operation", "Create");
        return "role/create";
    }

    /**
     * Add role to database
     *
     * @param role Role to save
     * @param model the Model to pass values to the thymeleaf template
     * @return result page
     */
    @PostMapping("/create")
    public String createRole(@ModelAttribute Role role, Model model) {
        repo.save(role);
        model.addAttribute("message", "interest successfully added");
        return "role/result";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id, Model model) {
        Optional<Role> role = repo.findById(id);
        if(role.isPresent()){
            repo.delete(role.get());
            model.addAttribute("message", "role " + role.get().getRoleName() +
                    " successfully deleted");
        } else{
            model.addAttribute("message", "role not found");
        }
        return "role/result";
    }
}
