package sysc4806.graduateAdmissions.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.val;
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
        val interest = Interest.builder().id(42)
                .department(Department.SYSC).keyword("spring").build();

        assertEquals(Utility.toJson(interest),
                "{"+
                "\"id\":42," +
                "\"department\":\"SYSC\"," +
                "\"keyword\":\"spring\"" +
                "}");
    }

    @Test
    void generateRandom128CharacterString() {
        val randomString1 = Utility.generateRandom128CharacterString();
        val randomString2 = Utility.generateRandom128CharacterString();

        assertEquals(128, randomString1.length());
        assertEquals(128, randomString2.length());
        assertNotEquals(randomString1, randomString2);
    }
}