import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.

    //不懂為什麼要特別宣告一個Constructor
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("Racecar"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeExtra(){
        assertFalse(palindrome.isPalindrome("121"));
    }

    @Test
    public void testIsPalindromeOverload(){
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("zyzy", offByOne));
        assertTrue(palindrome.isPalindrome("yyxz", offByOne));
        assertTrue(palindrome.isPalindrome("yyyxz", offByOne));
        assertFalse(palindrome.isPalindrome("aa", offByOne));
    }

    @Test
    public void testIsPalindromeOverloadExtra(){
        CharacterComparator offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("121", offByOne));
    }
}