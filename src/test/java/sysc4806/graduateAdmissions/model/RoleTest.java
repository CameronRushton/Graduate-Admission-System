package sysc4806.graduateAdmissions.model;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Role class. Since we are using lombok to generate the
 * constructors, getters, setters, etc. they are all explicitly tested here.
 *
 * @author luke
 */
class RoleTest {
    private Role role, roleWithPrivileges;
    private final String roleName1 = "student";
    private Privilege createSelfApplication;
    private Privilege readAllStudentsApplications;
    private Privilege updateAllStudentsApplications;
    private Privilege updateSelfApplication;
    private Set<Privilege> privileges;

    @BeforeEach
    void setUp() {
        createSelfApplication = Privilege.builder().id(42)
                .operation(Operation.CREATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        readAllStudentsApplications = Privilege.builder().id(8)
                .operation(Operation.READ).owner(Owner.ALL_STUDENTS)
                .target(Target.APPLICATION).build();
        updateAllStudentsApplications = Privilege.builder().id(8)
                .operation(Operation.UPDATE).owner(Owner.ALL_STUDENTS)
                .target(Target.APPLICATION).build();
        updateSelfApplication = Privilege.builder().id(9)
                .operation(Operation.UPDATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        privileges = Sets.newHashSet(createSelfApplication, updateSelfApplication);
        role = Role.builder().build();
        roleWithPrivileges = new Role(roleName1, privileges);
    }

    /**Test to ensure default values of fields of a Privilege are correct*/
    @Test
    public void testNoArgsConstructor(){
        assertNotNull(role);
        assertNull(role.getRoleName());
        assertNotNull(role.getPrivileges());
        assertEquals(0, role.getPrivileges().size());
    }

    /**Test to ensure that the all args constructor correctly sets fields*/
    @Test
    public void testArgsConstructor(){
        assertEquals(roleName1, roleWithPrivileges.getRoleName());
        assertEquals(privileges, roleWithPrivileges.getPrivileges());
        assertEquals(privileges.size(), roleWithPrivileges.getPrivileges().size());
    }

    /**Test to ensure that the builder correctly sets fields*/
    @Test
    public void testBuilder(){
        role = Role.builder().roleName(roleName1).privileges(privileges).build();
        assertEquals(roleName1, role.getRoleName());
        assertEquals(privileges, role.getPrivileges());
        assertEquals(privileges.size(), role.getPrivileges().size());
    }

    /**Test that the setRoleName method correctly sets the roleName field*/
    @Test
    public void setRoleName(){
        role.setRoleName(roleName1);
        assertEquals(roleName1, role.getRoleName());
    }

    /**Test that the setPrivileges method correctly sets the privileges field*/
    @Test
    public void setPrivileges(){
        role.setPrivileges(privileges);
        assertEquals(privileges, role.getPrivileges());
    }

    /**Test that the generated toString behaves as expected*/
    @Test
    public void testToString(){
        //because the collection is a set, the two Privileges could be in either order
        assertTrue(roleWithPrivileges.toString().equals(
                "Role(roleName=student, privileges=[" +
                updateSelfApplication.toString() + ", " +
                createSelfApplication.toString() + "])")
                ||
                roleWithPrivileges.toString().equals(
                        "Role(roleName=student, privileges=[" +
                                createSelfApplication.toString() + ", " +
                                updateSelfApplication.toString() + "])")
        );
    }

    /**Test that two Role objects with identical fields are considered equal*/
    @Test
    public void testEquals(){
        Role role2 = new Role(roleName1, privileges);
        assertEquals(roleWithPrivileges, role2);
    }

    /**Test that objects with different roleName values are not considered equal*/
    @Test
    public void testRoleNameNotEquals(){
        Role role2 = new Role("admin", privileges);
        assertNotEquals(role, role2);
    }

    /**Test that objects with different Privileges are not considered equal*/
    @Test
    public void testPrivilegesNotEquals(){
        Set<Privilege> differentPrivileges = Sets.newHashSet(readAllStudentsApplications);
        Role role2 = new Role(roleName1, differentPrivileges);
        assertNotEquals(role, role2);
    }

    /**
     * helper method for tests that add a new Privilege to a Role
     *
     * @param newPrivileges the Privileges to add to a Role
     * @return true if the number of Privileges in the Role changed size
     */
    private boolean addPrivilegeHelper(Privilege... newPrivileges){
        int numPrivileges = roleWithPrivileges.getPrivileges().size();
        int numNewPrivileges = 0;
        for(Privilege privilege : newPrivileges){
            if(!roleWithPrivileges.hasPrivilege(privilege))
                numNewPrivileges++;
        }
        boolean result = roleWithPrivileges.addPrivilege(newPrivileges);
        assertEquals(numPrivileges + numNewPrivileges,
                roleWithPrivileges.getPrivileges().size());
        return result;
    }

    /**Test that adds a single Privilege to a Role*/
    @Test
    public void testAddOnePrivilege(){
        assertTrue(addPrivilegeHelper(readAllStudentsApplications));
    }

    /**Test that ensures Privileges are not duplicated when added twice to a Role*/
    @Test
    public void testAddExistingPrivilege(){
        assertFalse(addPrivilegeHelper(createSelfApplication));
    }

    /**Test that adds multiple Privileges to a Role*/
    @Test
    public void testAddMultiplePrivileges(){
        assertTrue(addPrivilegeHelper(readAllStudentsApplications, updateAllStudentsApplications));
    }

    /**Test that a created Role does have a specific Privilege*/
    @Test
    public void testHasPrivilegeTrue(){
        assertTrue(roleWithPrivileges.hasPrivilege(updateSelfApplication));
    }

    /**Test that a created Role does not have a specific Privilege*/
    @Test
    public void testHasPrivilegeFalse(){
        assertFalse(roleWithPrivileges.hasPrivilege(updateAllStudentsApplications));
    }

    /**Test that the removal of a Privilege from a role works correctly*/
    @Test
    public void testRemoveExistingPrivilege(){
        Privilege toRemove = createSelfApplication;
        int numPrivileges = roleWithPrivileges.getPrivileges().size();
        assertTrue(roleWithPrivileges.removePrivilege(toRemove));
        assertEquals(numPrivileges - 1, roleWithPrivileges.getPrivileges().size());
        assertFalse(roleWithPrivileges.hasPrivilege(toRemove));
    }

    /**Test that the removal of a Privilege not in the Role's Privilege set works as expected*/
    @Test
    public void testRemovePrivilegeNotInSet(){
        int numPrivileges = roleWithPrivileges.getPrivileges().size();
        assertFalse(roleWithPrivileges.removePrivilege(readAllStudentsApplications));
        assertEquals(numPrivileges, roleWithPrivileges.getPrivileges().size());
    }
}