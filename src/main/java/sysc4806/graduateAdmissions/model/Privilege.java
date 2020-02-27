package sysc4806.graduateAdmissions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {
    //the primary key for a privilege
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private long id;
    //the type of CRUD operation the privilege specified
    private Operation operation;
    //the type of object that an operation is performed on
    private Target target;
    //the owner of the specified target object of the specified operation
    private Owner owner;
}
