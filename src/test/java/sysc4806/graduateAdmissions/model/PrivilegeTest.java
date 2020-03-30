package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Privilege class. Since we are using lombok to generate the
 * constructors, getters, setters, etc. they are all explicitly tested here.
 *
 * @author luke
 */
class PrivilegeTest {
    private Privilege privilege;
    private long ID = 5;
    private Operation operation = Operation.CREATE;
    private Target target = Target.APPLICATION;
    private Owner owner = Owner.SELF;

    @BeforeEach
    public void setUp(){
        privilege = Privilege.builder().owner(Owner.SELF).operation(Operation.READ).target(Target.USER).build();
    }

    /**Test to ensure default values of fields of a Privilege are correct*/
    @Test
    public void testNoArgsConstructor(){
        Privilege noArgsPrivilege = new Privilege();
        assertNotNull(noArgsPrivilege);
        assertNull(noArgsPrivilege.getOperation());
        assertNull(noArgsPrivilege.getOwner());
        assertNull(noArgsPrivilege.getTarget());
        assertNotNull(noArgsPrivilege.getId());
    }

    /**Test to ensure that the all args constructor correctly sets fields*/
    @Test
    public void testArgsConstructor(){
        privilege = new Privilege(ID, operation, target, owner);
        assertEquals(ID, privilege.getId());
        assertEquals(operation, privilege.getOperation());
        assertEquals(target, privilege.getTarget());
        assertEquals(owner, privilege.getOwner());
    }

    /**Test to ensure that the builder correctly sets fields*/
    @Test
    public void testBuilder(){
        privilege = Privilege.builder().id(ID).operation(operation)
                .owner(owner).target(target).build();
        assertEquals(ID, privilege.getId());
        assertEquals(operation, privilege.getOperation());
        assertEquals(target, privilege.getTarget());
        assertEquals(owner, privilege.getOwner());
    }

    /**Test that the setID method correctly sets the id field*/
    @Test
    public void setId(){
        privilege.setId(ID);
        assertEquals(ID, privilege.getId());
    }

    /**Test that the setOperation method correctly sets the operation field*/
    @Test
    public void setOperation(){
        privilege.setOperation(operation);
        assertEquals(operation, privilege.getOperation());
    }

    /**Test that the setTarget method correctly sets the target field*/
    @Test
    public void setTarget(){
        privilege.setTarget(target);
        assertEquals(target, privilege.getTarget());
    }

    /**Test that the setOwner method correctly sets the owner field*/
    @Test
    public void setOwner(){
        privilege.setOwner(owner);
        assertEquals(owner, privilege.getOwner());
    }

    /**Test that the generated toString behaves as expected*/
    @Test
    public void testToString(){
        privilege = new Privilege(ID, operation, target, owner);
        assertEquals("Privilege(id=5, operation=CREATE, target=APPLICATION, owner=SELF)",
                privilege.toString());
    }

    /**Test that two Privilege objects with identical fields are considered equal*/
    @Test
    public void testEquals(){
        Privilege privilegeA = new Privilege(ID, operation, target, owner);
        Privilege privilegeB = new Privilege(ID, operation, target, owner);
        assertEquals(privilegeA, privilegeB);
    }

    /**Test that two Privilege objects with all fields equal except their ids are still considered equal*/
    @Test
    public void testEqualsDifferentID(){
        Privilege privilegeA = new Privilege(ID, operation, target, owner);
        Privilege privilegeB = new Privilege(ID + 42, operation, target, owner);
        assertEquals(privilegeA, privilegeB);
    }

    /**Test that objects with different field values are not considered equal*/
    @Test
    public void testNotEquals(){
        Privilege privilegeA = new Privilege(ID, operation, target, owner);
        assertNotEquals(privilegeA, privilege);
    }
}