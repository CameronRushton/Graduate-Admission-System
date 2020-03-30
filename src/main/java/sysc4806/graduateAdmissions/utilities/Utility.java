package sysc4806.graduateAdmissions.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.val;
import lombok.var;

import java.util.Random;
import java.text.SimpleDateFormat;

/**
 * class for utility functions that are useful for a number of
 * parts of the system, or useful for several test classes
 *
 * @author luke
 */
public class Utility {
    /**
     * helper method to convert objects into JSON format
     *
     * @param o the object to convert to JSON format
     * @return a string contain the JSON for Object o
     * @throws JsonProcessingException when JSON writing fails
     */
    public static String toJson(Object o) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        var ow = mapper.writer();
        return ow.writeValueAsString(o);
    }

    /**
     * generates a random alphanumeric string of 64 characters
     *
     * @return the generated string
     */
    public static String generateRandom64CharacterString(){
        val leftLimit = 48; // numeral '0'
        val rightLimit = 122; // letter 'z'
        val targetStringLength = 64;
        val random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
