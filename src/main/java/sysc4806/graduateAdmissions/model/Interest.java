package sysc4806.graduateAdmissions.model;

import lombok.*;

import javax.persistence.*;


/**
 * This class represents an interest area for a user. This is used to match
 * profs to students for consideration of their graduate application. An
 * interest has a keyword, which describes the interest, and a department,
 * since similar keywords may mean things in different contexts
 *
 * @author luke
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Interest {
    //the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private long id;
    //the department for interest context
    @Enumerated(EnumType.STRING)
    private Department department;
    //the keyword which defines the interest
    @Getter private String keyword;
}
