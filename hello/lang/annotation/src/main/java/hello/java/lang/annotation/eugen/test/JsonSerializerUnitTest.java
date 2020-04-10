package hello.java.lang.annotation.eugen.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.java.lang.annotation.eugen.customannotations.JsonSerializationException;
import hello.java.lang.annotation.eugen.customannotations.ObjectToJsonConverter;
import hello.java.lang.annotation.eugen.customannotations.Person;
import org.junit.jupiter.api.Test;

public class JsonSerializerUnitTest {

    @Test
    public void givenObjectNotSerializedThenExceptionThrown() throws JsonSerializationException {
        Object object = new Object();
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        assertThrows(JsonSerializationException.class, () -> {
            serializer.convertToJson(object);
        });
    }

    @Test
    public void givenObjectSerializedThenTrueReturned() throws JsonSerializationException {
        Person person = new Person("soufiane", "cheouati", "34");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        assertEquals("{\"personAge\":\"34\",\"firstName\":\"Soufiane\",\"lastName\":\"Cheouati\"}", jsonString);
    }
}