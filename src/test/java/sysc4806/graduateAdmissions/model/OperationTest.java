package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Operation enumeration. These tests check that an enum instance can
 * be successfully created, and that the descriptions for each enum are correct.
 *
 * @author luke
 */
class OperationTest {
    @Test
    public void createEnumSuccessfully(){
        Operation operation = Operation.CREATE;
        assertNotNull(operation);
        assertSame(operation, Operation.CREATE);
    }

    @Test
    public void checkDescriptions(){
        assertEquals(Operation.CREATE.getDescription(), "create");
        assertEquals(Operation.READ.getDescription(), "read");
        assertEquals(Operation.UPDATE.getDescription(), "update");
        assertEquals(Operation.DELETE.getDescription(), "delete");
    }
}