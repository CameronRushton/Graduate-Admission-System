package sysc4806.graduateAdmissions.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }
}
