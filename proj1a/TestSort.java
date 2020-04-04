import org.junit.Test;
import static org.junit.Assert.*;

public class TestSort {
    /** Tests the sort method of the Sort class. */
    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input, 0);
        assertArrayEquals(expected, input);
        //for (int i = 0; i < input.length; i += 1) {
        //    if (!input[i].equals(expected[i])) {
        //        System.out.println("Mismatch in position " + i + ", expected: " + expected[i] + ", but got: " + input[i] + ".");
        //        break;
        //    }
        //}
    }

    @Test
    public void testFindSmallest(){
        String[] input ={"i", "have", "an", "egg"};
        int expected = 2;
        int actual = Sort.findSmallest(input, 0);
        org.junit.Assert.assertEquals(expected, actual);
        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 1;
        int actual2 = Sort.findSmallest(input2, 0);
        assertEquals(expected2, actual2);
    }

    @Test
    public void testSwap(){
        String[] input ={"i", "have", "an", "egg"};
        String[] expected = {"an", "have", "i", "egg"};
        int a = 0;
        int b = 2;
        Sort.swap(input, a, b);
        assertArrayEquals(expected, input);
    }
}