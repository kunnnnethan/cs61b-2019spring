import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testTask1(){
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        int actual;
        int expected;

        for (int i = 0; i < 100; i++) {
            int random = StdRandom.uniform(100);
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                ads.addLast(random);
                sad.addLast(random);
                actual = ads.get(0);
                expected = sad.get(0);
                System.out.println("addLast(" + random + ")");
                assertEquals("addLast(" + random + ")", expected, actual);
                numberBetweenZeroAndOne = StdRandom.uniform();
                if (numberBetweenZeroAndOne < 0.5) {
                    Integer a = ads.removeLast();
                    Integer b = sad.removeLast();
                    actual = a;
                    expected = b;
                    System.out.println("removeLast(" + a + ")" + " and " + "removeLast(" + b + ")");
                    assertEquals("removeLast(" + a + ")" + " and " + "removeLast(" + b + ")", expected, actual);
                } else {
                    Integer c = ads.removeFirst();
                    Integer d = sad.removeFirst();
                    actual = c;
                    expected = d;
                    System.out.println("removeFirst(" + c + ")" + " and " + "removeFirst(" + d + ")");
                    assertEquals("removeFirst(" + c + ")" + " and " + "removeFirst(" + d + ")", expected, actual);
                }
            } else {
                ads.addFirst(random);
                sad.addFirst(random);
                actual = ads.get(0);
                expected = sad.get(0);
                System.out.println("addFirst(" + random + ")");
                assertEquals("addFirst(" + random + ")", expected, actual);
                numberBetweenZeroAndOne = StdRandom.uniform();
                if (numberBetweenZeroAndOne < 0.5) {
                    Integer a = ads.removeLast();
                    Integer b = sad.removeLast();
                    actual = a;
                    expected = b;
                    System.out.println("removeLast(" + a + ")" + " and " + "removeLast(" + b + ")");
                    assertEquals("removeLast(" + a + ")" + " and " + "removeLast(" + b + ")", expected, actual);
                } else {
                    Integer c = ads.removeFirst();
                    Integer d = sad.removeFirst();
                    actual = c;
                    expected = d;
                    System.out.println("removeFirst(" + c + ")" + " and " + "removeFirst(" + d + ")");
                    assertEquals("removeFirst(" + c + ")" + " and " + "removeFirst(" + d + ")", expected, actual);
                }
            }
        }
    }
    private static final int nCall = 1000; // How many to call methods randomly
    private static String message = ""; // Store failure sequence

    /** Given uniformly distributed random double between 0 and 1,
     * randomly adds Integer to deque.
     * */
    private void randomAdd(double random, Integer i, StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads) {
        if (random < 0.5) {
            sad.addFirst(i);
            ads.addFirst(i);
            message += "\naddFirst(" + i + ")";
        } else {
            sad.addLast(i);
            ads.addLast(i);
            message += "\naddLast(" + i + ")";
        }
    }

    /** Given uniformly distributed random double between 0 and 1,
     * randomly adds Integer to deque.
     * */
    private void randomRemove(double random, Integer i, StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads) {
        Integer expected;
        Integer actual;
        if (random < 0.5) {
            expected = ads.removeFirst();
            actual = sad.removeFirst();
            message += "\nremoveFirst()";
        } else {
            expected = ads.removeLast();
            actual = sad.removeLast();
            message += "\nremoveLast()";
        }
        assertEquals(message, expected, actual);
    }

    @Test
    public void testRandomized() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        for (Integer i = 0; i < nCall; i += 1) {
            if (sad.isEmpty() || ads.isEmpty()) {
                double random = StdRandom.uniform();
                randomAdd(random, i, sad, ads);
            } else {
                double random1 = StdRandom.uniform();
                double random2 = StdRandom.uniform();
                if (random1 < 0.5) {
                    randomAdd(random2, i, sad, ads);
                } else {
                    randomRemove(random2, i, sad, ads);
                }
            }
        }
    }
}
