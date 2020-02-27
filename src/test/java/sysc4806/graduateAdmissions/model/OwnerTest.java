package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the operation enumeration. These tests check that an enum instance can
 * be successfully created, and that the descriptions for each enum are correct.
 *
 * @author luke
 */
class OwnerTest {
    @Test
    public void createEnumSuccessfully(){
        Owner owner = Owner.SELF;
        assertNotNull(owner);
        assertSame(owner, Owner.SELF);
    }

    @Test
    public void checkDescriptions(){
        assertEquals(Owner.SELF.getDescription(), "self");
        assertEquals(Owner.ALL_PROFS.getDescription(), "all professors");
        assertEquals(Owner.ALL_STUDENTS.getDescription(), "all students");
    }
}