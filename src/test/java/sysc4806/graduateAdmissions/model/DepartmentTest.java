package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Department enumeration. These tests check that an enum instance can
 * be successfully created, and that the descriptions for each enum are correct.
 *
 * @author luke
 */
class DepartmentTest {
    /**Test that enum assignment works as expected*/
    @Test
    public void createEnumSuccessfully(){
        Department department = Department.SYSC;
        assertNotNull(department);
        assertSame(department, Department.SYSC);
    }

    /**Test that the descriptions on each enum value are as expected*/
    @Test
    public void checkDescriptions(){
        assertEquals(Department.SYSC.getDescription(), "systems and computer engineering");
        assertEquals(Department.SREE.getDescription(), "sustainable and renewable energy engineering");
        assertEquals(Department.MAAE.getDescription(), "mechanical engineering");
        assertEquals(Department.AERO.getDescription(), "aerospace engineering");
        assertEquals(Department.ELEC.getDescription(), "electrical engineering");
    }
}