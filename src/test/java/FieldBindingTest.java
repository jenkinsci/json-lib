import static org.junit.jupiter.api.Assertions.assertEquals;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * @author Kohsuke Kawaguchi
 */
class FieldBindingTest {
    public static final class Foo {
        public int x;
        public String y;
    }

    @Test
    void test1() {
        Foo f = new Foo();
        f.x = 5;
        f.y = "test";
        JSONObject o = JSONObject.fromObject(f);
        assertEquals(5, o.getInt("x"));
        assertEquals("test", o.getString("y"));
        assertEquals(2, o.size());
    }

    public static final class Bar {
        public int x;
        public String y;
    }

    @Test
    void test2() {
        Bar f = new Bar();
        f.x = 5;
        f.y = "test";
        JSONObject o = JSONObject.fromObject(f);
        assertEquals(5, o.getInt("x"));
        assertEquals("test", o.getString("y"));
        assertEquals(2, o.size());
    }
}
