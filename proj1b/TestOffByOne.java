import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    // Your tests go here.

    @Test
    public void testEqualCharsTrue(){
        assertTrue(offByOne.equalChars('a', 'b'));  // True
        assertTrue(offByOne.equalChars('r', 'q'));  // True
    }

    @Test
    public void testEqualCharsFalse(){
        assertFalse(offByOne.equalChars('a', 'e'));  // false
        assertFalse(offByOne.equalChars('z', 'a'));  // false
        assertFalse(offByOne.equalChars('a', 'a'));  // false
    }
}