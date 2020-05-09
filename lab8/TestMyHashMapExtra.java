import static org.junit.Assert.*;
import org.junit.Test;

/** Tests of optional parts of lab 9. */
public class TestMyHashMapExtra {

    /* Remove Test 
     */
    @Test
    public void testRemove() {
        MyHashMap<String, String> q = new MyHashMap<String, String>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a"); // a b c d e
        assertTrue(null != q.remove("c"));
        assertFalse(q.containsKey("c"));
        assertTrue(q.containsKey("a"));     
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        assertTrue(q.containsKey("e"));
    }
    
    /* Remove Test 2 
     * test the 3 different cases of remove
     */
    @Test
    public void testRemoveThreeCases() {
        MyHashMap<String, String> q = new MyHashMap<String, String>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a");                         // a b c d e
        assertTrue(null != q.remove("e"));      // a b c d
        assertTrue(q.containsKey("a"));     
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("c"));
        assertTrue(q.containsKey("d"));
        assertTrue(null != q.remove("c"));      // a b d
        assertTrue(q.containsKey("a"));     
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        q.put("f", "a");                         // a b d f
        assertTrue(null != q.remove("d"));      // a b f
        assertTrue(q.containsKey("a"));     
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("f"));
    }

    @Test
    public void testRemoveMultipleCases() {
        MyHashMap<String, String> q = new MyHashMap<String, String>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a");                         // a b c d e
        q.put("f", "a");
        q.put("g", "a");
        q.put("h", "a");
        q.put("i", "a");
        q.put("j", "a");
        q.put("k", "a");
        q.put("l", "a");
        q.put("m", "a");
        q.put("n", "a");
        q.put("o", "a");
        q.put("p", "a");
        q.put("q", "a");
        q.put("r", "a");
        q.put("s", "a");
        q.put("t", "a");
        q.put("u", "a");
        q.put("v", "a");
        q.put("w", "a");
        q.put("x", "a");
        q.put("y", "a");
        for (int i = 0; i < 100; i += 1) {
            q.put("y" + i, "a");
        }
        for (int i = 0; i < 100; i += 1) {
            q.remove("y" + i);
            assertFalse(q.containsKey("y" + i));
        }
        q.put("z", "a");
        for (int i = 0; i < 200; i += 1) {
            q.put("z" + i, "a");
        }
        assertTrue(null != q.remove("c"));
        assertTrue(null != q.remove("d"));
        assertTrue(null != q.remove("e"));
        assertTrue(null != q.remove("f"));
        assertTrue(null != q.remove("g"));
        assertTrue(null != q.remove("h"));
        assertTrue(null != q.remove("i"));
        assertTrue(null != q.remove("j"));
        assertTrue(null != q.remove("k"));
        assertTrue(null != q.remove("t"));
        assertTrue(null != q.remove("u"));
        assertTrue(null != q.remove("v"));
        assertTrue(null != q.remove("w"));
        assertTrue(null != q.remove("x"));
        assertTrue(null != q.remove("y"));
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("l"));
        assertTrue(q.containsKey("m"));
        assertTrue(q.containsKey("n"));
        assertTrue(q.containsKey("o"));
        assertTrue(q.containsKey("p"));
        assertTrue(q.containsKey("q"));
        assertTrue(q.containsKey("r"));
        assertTrue(q.containsKey("s"));
        assertFalse(q.containsKey("f"));
        assertFalse(q.containsKey("t"));
        assertFalse(q.containsKey("v"));
        assertFalse(q.containsKey("w"));
        assertFalse(q.containsKey("x"));
        assertFalse(q.containsKey("y"));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMyHashMapExtra.class);
    }
}
