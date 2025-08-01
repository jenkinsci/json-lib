package net.sf.json.bugs;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.jupiter.api.Test;

class TestBug2843623 {
    @Test
    void testSample() {
        Container orig = new Container();
        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        orig.setMyList(strings);

        JSONObject jobj = JSONObject.fromObject(orig);
        Container root = new Container();
        Container res = (Container) JSONObject.toBean(jobj, root, new JsonConfig());
        assertFalse(res.getMyList().isEmpty());
        assertFalse(root.getMyList().isEmpty());
    }

    public static class Container {
        private final List<String> myList = new ArrayList<>();

        public List<String> getMyList() {
            return myList;
        }

        public void setMyList(List<String> strings) {
            this.myList.addAll(strings);
        }
    }
}
