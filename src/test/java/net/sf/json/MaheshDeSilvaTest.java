package net.sf.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * This came via e-mail.
 *
 * <p>
 * We are using JSON-lib in our application which is used by a lot of customers.
 * A recent issue came up where user submitted string value 'null' is being stored
 * into the data base as '"null"'. Basically double quotes are being added at the start and end of the String.
 *
 * <p>
 * Investigation revealed that JSON-lib doesn't seem to handle the 'null' string value correctly.
 * This is exhibited by the following test methods.
 *
 * <p>
 * basically when browser sends {@code ["null", "aValue"]} to server, JSON-lib changes it to
 * {@code ["\"null\"", "aValue"]}. Also from the server side we are unable to construct a JSON
 * formatted String like {@code ["null", "b"]} using {@link JSONArray}. JSON-lib does not seem to
 * handle these two basic scenarios properly.
 *
 * <p>
 * The test class is attached for your reference.
 *
 * <p>
 * Appreciate if you could provide feedback as to how to handle this issue using JSON-lib.
 *
 * @author Mahesh De Silva
 */
class MaheshDeSilvaTest {
    @Test
    void testShouldHandleNullStringInJsonFormattedString() {
        String jsonTest = "[\"null\",\"aValue\"]";
        assertTrue(jsonTest.contains("\"null\""));
        assertFalse(jsonTest.contains("\"\\\"null\\\"\""));
        String convertedBack = JSONSerializer.toJSON(jsonTest).toString();
        assertFalse(convertedBack.contains("\"\\\"null\\\"\""));
    }

    @Test
    void testShouldHandleNullStringLiteral() {
        JSONArray jsonArray1 = JSONArray.fromObject(Arrays.asList(null, "b"));
        JSONArray jsonArray2 = JSONArray.fromObject(Arrays.asList(JSONNull.getInstance(), "b"));
        JSONArray jsonArray3 = JSONArray.fromObject(Arrays.asList("null", "b"));
        assertEquals("[null,\"b\"]", jsonArray1.toString());
        assertEquals("[null,\"b\"]", jsonArray2.toString());
        assertEquals("[\"null\",\"b\"]", jsonArray3.toString());
    }
}
