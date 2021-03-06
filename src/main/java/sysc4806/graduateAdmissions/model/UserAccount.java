package sysc4806.graduateAdmissions.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

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
public class UserAccount {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "3"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Role role;
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Interest> interests;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
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
    public UserAccount(String firstname, String lastname, String mail, Role r, Set<Interest> interest, Set<Application> application){
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = mail;
        this.role = r;
        this.interests = interest;
        this.applications = application;
    }
}
