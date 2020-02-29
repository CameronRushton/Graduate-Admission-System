package sysc4806.graduateAdmissions.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Operation enumeration. These tests check that an enum instance can
 * be successfully created, and that the descriptions for each enum are correct.
 *
 * @author Madelyn
 */
class StatusTest {
    /**Test that enum assignment works as expected*/
    @Test
    public void createEnumSuccessfully(){
        Status status = Status.INCOMPLETE;
        assertNotNull(status);
        assertSame(status, status.INCOMPLETE);
    }

    /**Test that the descriptions on each enum value are as expected*/
    @Test
    public void checkDescriptions(){
        assertEquals(Status.INCOMPLETE.getDescription(), "incomplete");
        assertEquals(Status.SUBMITTED.getDescription(), "submitted to administration");
        assertEquals(Status.PENDINGAPROVAL.getDescription(), "pending professor's approval");
        assertEquals(Status.PENDINGREVIEW.getDescription(), "pending administrative review");
        assertEquals(Status.APPROVEDFUNDING.getDescription(), "approved with funding");
        assertEquals(Status.APPROVEDNOFUNDING.getDescription(), "approved with no funding");
        assertEquals(Status.DENIED.getDescription(), "denied");
    }
}