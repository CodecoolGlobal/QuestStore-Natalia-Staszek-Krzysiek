package helpers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    public void parseFormData() {
        Map<String, String> expected = new HashMap<>();
        expected.put("login", "mark");
        expected.put("password", "123456");
        String path = "login=mark&password=123456";
        Map<String, String> result = Parser.parseFormData(path);

        assertEquals(expected, result);
    }

    @Test
    public void parseFormDataNotEquals() {
        Map<String, String> expected = new HashMap<>();
        expected.put("login", "mark");
        String path = "login=mark&password=123456";
        Map<String, String> result = Parser.parseFormData(path);

        assertNotEquals(expected, result);
    }
}