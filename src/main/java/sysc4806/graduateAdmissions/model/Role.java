package sysc4806.graduateAdmissions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * This class specifies a role for the system's role-based access control.
 * Each role has a unique name, and a set of privileges that specify what
 * type of operations the role can perform.
 *
 * A User has one or more roles
 *
 * @author luke
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    //the primary key a role
    @Id
    @Column(name = "role_id")
    private String roleName;
    //the privileges for the role
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "role_id")
    @Builder.Default
    private Set<Privilege> privileges = new HashSet<>();

    /**
     * add one or more privileges to the Role's set of privileges
     *
     * @param privileges the privilege(s) to add to the Role's set of privileges privilege
     * @return true if the privilege set changed as a result of this call
     */
    public boolean addPrivilege(Privilege... privileges){
        return this.privileges.addAll(Arrays.asList(privileges));
    }

    /**
     * remove a Role's privilege at a certain index in the set of privileges
     *
     * @param privilege the privilege to remove in the Role's set of privileges
     * @return true if the set contained the specified privilege
     */
    public boolean removePrivilege(Privilege privilege){
        return this.privileges.remove(privilege);
    }

    /**
     * determines if the Role has a specific privilege
     *
     * @param privilege the privilege to check the Role for
     * @return true if the Role has the specified Privilege
     */
    public boolean hasPrivilege(Privilege privilege){
        return this.privileges.contains(privilege);
    }
}
