package sysc4806.graduateAdmissions.model;

import lombok.*;

import javax.persistence.*;

/**
 * This class is used to specify privileges for roles. A Role with have a collection
 * of these objects to determine what it can and cannot do. A Privilege is made up of three
 * parts: An action (eg. create, delete), a target for that action (eg. application, interest),
 * and an owner of that target (eg. self, everyone). Combinations of these allow the
 * specification of many different privileges for roles.
 *
 * For example, a student would have privileges for creating and deleting their own applications.
 * Admin staff would have privileges for creating and deleting any student's application.
 *
 * @author luke
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Privilege {
    //the primary key for a privilege
    @Id
    @Column(name = "privilege_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private long id;
    //the type of CRUD operation the privilege specified
    @Enumerated(EnumType.STRING)
    private Operation operation;
    //the type of object that an operation is performed on
    @Enumerated(EnumType.STRING)
    private Target target;
    //the owner of the specified target object of the specified operation
    @Enumerated(EnumType.STRING)
    private Owner owner;
}
