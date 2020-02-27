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
        privilege = new Privilege();
    }

    @Test
    public void testNoArgsConstructor(){
        assertNotNull(privilege);
        assertNull(privilege.getOperation());
        assertNull(privilege.getOwner());
        assertNull(privilege.getTarget());
        assertEquals(0, privilege.getId());
    }

    @Test
    public void testArgsConstructor(){
        privilege = new Privilege(ID, operation, target, owner);
        assertEquals(ID, privilege.getId());
        assertEquals(operation, privilege.getOperation());
        assertEquals(target, privilege.getTarget());
        assertEquals(owner, privilege.getOwner());
    }

    @Test
    public void setId(){
        privilege.setId(ID);
        assertEquals(ID, privilege.getId());
    }

    @Test
    public void setOperation(){
        privilege.setOperation(operation);
        assertEquals(operation, privilege.getOperation());
    }

    @Test
    public void setTarget(){
        privilege.setTarget(target);
        assertEquals(target, privilege.getTarget());
    }

    @Test
    public void setOwner(){
        privilege.setOwner(owner);
        assertEquals(owner, privilege.getOwner());
    }

    @Test
    public void testToString(){
        privilege = new Privilege(ID, operation, target, owner);
        assertEquals("Privilege(id=5, operation=CREATE, target=APPLICATION, owner=SELF)",
                privilege.toString());
    }

    @Test
    public void testEquals(){
        Privilege privilegeA = new Privilege(ID, operation, target, owner);
        Privilege privilegeB = new Privilege(ID, operation, target, owner);
        assertEquals(privilegeA, privilegeB);
    }

    @Test
    public void testEqualsDifferentID(){
        Privilege privilegeA = new Privilege(ID, operation, target, owner);
        Privilege privilegeB = new Privilege(ID + 42, operation, target, owner);
        assertEquals(privilegeA, privilegeB);
    }

    @Test
    public void testNotEquals(){
        Privilege privilegeA = new Privilege(ID, operation, target, owner);
        assertNotEquals(privilegeA, privilege);
    }
}