package sysc4806.graduateAdmissions.model;

import lombok.*;

import javax.persistence.*;

/**
 * An object to represent user sessions
 *
 * @author luke
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    //Primary key given to client in cookie
    @Id
    private String id;
    //the user associated with the session
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private UserAccount user;
}
