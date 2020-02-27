package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Target enumeration. These tests check that an enum instance can
 * be successfully created, and that the descriptions for each enum are correct.
 *
 * @author luke
 */
class TargetTest {
    @Test
    public void createEnumSuccessfully(){
        Target target = Target.APPLICATION;
        assertNotNull(target);
        assertSame(target, Target.APPLICATION);
    }

    @Test
    public void checkDescriptions(){
        assertEquals(Target.APPLICATION.getDescription(), "application");
        assertEquals(Target.INTEREST.getDescription(), "interest");
        assertEquals(Target.TERM.getDescription(), "term");
        assertEquals(Target.USER.getDescription(), "user");
        assertEquals(Target.ROLE.getDescription(), "role");
    }
}