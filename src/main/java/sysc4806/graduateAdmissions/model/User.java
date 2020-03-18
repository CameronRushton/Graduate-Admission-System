package sysc4806.graduateAdmissions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
/**
 * Represents one of three possible users in the system (Student, Professor, or Admin)
 * Used to user details, credentials, and information.
 *
 * @author Kevin Sun
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToOne
    private Role role;
    @OneToMany
    private Set<Interest> interests;
    @OneToMany
    private Set<Application> applications;

    /**
     * Creates a User with an autogenerated id and given parameters
     *
     * @param firstname String represents the first name of the user
     * @param lastname String represents the last name of the user
     * @param mail String represents the email of the user
     * @param r Role represents the role of the user
     * @param interest Array List interest contains the chosen interests of the user
     * @param application ArrayList application contains all applications of the user
     */
    public User(String firstname, String lastname, String mail, String pass, Role r, Set<Interest> interest, Set<Application> application){
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = mail;
        this.password = pass;
        this.role = r;
        this.interests = interest;
        this.applications = application;
    }
}
