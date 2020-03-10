package sysc4806.graduateAdmissions.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the functions in the utility class
 *
 * @author luke
 *
 */
class UtilityTest {

    @Test
    void toJson() throws JsonProcessingException {
        Interest interest = Interest.builder().id(42)
                .department(Department.SYSC).keyword("spring").build();

        assertEquals(Utility.toJson(interest),
                "{"+
                "\"id\":42," +
                "\"department\":\"SYSC\"," +
                "\"keyword\":\"spring\"" +
                "}");
    }
}