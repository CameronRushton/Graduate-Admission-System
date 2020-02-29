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
    /**Test that enum assignment works as expected*/
    @Test
    public void createEnumSuccessfully(){
        Owner owner = Owner.SELF;
        assertNotNull(owner);
        assertSame(owner, Owner.SELF);
    }

    /**Test that the descriptions on each enum value are as expected*/
    @Test
    public void checkDescriptions(){
        assertEquals(Owner.SELF.getDescription(), "self");
        assertEquals(Owner.ALL_PROFS.getDescription(), "all professors");
        assertEquals(Owner.ALL_STUDENTS.getDescription(), "all students");
    }
}