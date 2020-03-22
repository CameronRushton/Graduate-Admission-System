package sysc4806.graduateAdmissions.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * An object that describes an application to a degree in our system. An application has
 * an applicant, a department, degree, and professors that they are applying to study under,
 * a status that describes what stage the application is in, and any information inputted by the
 * applicant in the application forms.
 *
 * @author madelynkrasnay
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    //generated Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private long id;

    //The owner of the application
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User applicant;

    //term that the application is for
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Term term;

    //department and degree type. We may want to make
    // these objects at some point in the future.
    @Enumerated(EnumType.STRING)
    private Department department;
    private String degree;

    //Preferred professors. These will be the ones
    // to approve the application.
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> professors;


    //The status of the application, ie, is it incomplete,
    // pending approval, approved, etc.
    @Enumerated(EnumType.STRING)
    private Status status;

    //Some examples of fields from the application forms.
    //TODO: Add the rest of the form fields
    private double gpa;
    private String resumeFileName; //TODO: make a file.
}
