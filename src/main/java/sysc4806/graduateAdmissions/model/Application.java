package sysc4806.graduateAdmissions.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    //generated Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private long id;

    //The owner of the application
    //TODO: make a user when user object is created
    private String applicant;

    //term that the application is for
    //TODO: make a term when the term object is created
    private String term;

    //department and degree type. We may wat to make
    // these objects at some point in the future.
    private String department;
    private String degree;

    //Preferred professors. These will be the ones
    // to approve the application.
    //TODO: Make a set of users. Enforce that the user's role must be prof.
    private String profesors;


    //The status of the application, ie, is it incomplete,
    // pending approval, approved, etc.
    private Status status;

    //Some examples of fields from the application forms.
    //TODO: Add the rest of the form fields (when
    private float gpa;
    private String resumeFileName; //TODO: make a file.
}
