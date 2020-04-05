import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testTask1(){
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        int actual;
        int expected;

        for (int i = 0; i < 3; i++) {
            int random = StdRandom.uniform(100);
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                ads.addLast(random);
                sad.addLast(random);
                actual = ads.get(i);
                expected = sad.get(i);
                System.out.println("addLast(" + random + ")");
                assertEquals("addLast(" + random + ")", expected, actual);
            } else {
                ads.addFirst(random);
                sad.addFirst(random);
                actual = ads.get(i);
                expected = sad.get(i);
                System.out.println("addFirst(" + random + ")");
                assertEquals("addFirst(" + random + ")", expected, actual);
            }
        }

        for (int i = 0; i < 3; i++) {
            int random = StdRandom.uniform(100);
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                Integer a = ads.removeLast();
                Integer b = sad.removeLast();
                actual = a;
                expected = b;
                System.out.println("removeFirst(" + a + ")" + " and " + "removeFirst(" + b + ")");
                assertEquals("removeFirst(" + a + ")" + " and " + "removeFirst(" + b + ")", expected, actual);
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

    @Test
    public void testArrayDeque2() {
        ArrayDequeSolution<Integer> ads2 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad2 = new StudentArrayDeque<>();
        int random = StdRandom.uniform(100);
        ads2.addFirst(random);
        sad2.addFirst(random);
        assertEquals("addFirst("+random+")", ads2.get(0), sad2.get(0));
        System.out.println("addFirst("+random+")");

        random = StdRandom.uniform(100);
        ads2.addLast(random);
        sad2.addLast(random);
        assertEquals("addLast("+random+")", ads2.get(1), sad2.get(1));
        System.out.println("addLast("+random+")");

        Integer actual2 = ads2.removeFirst();
        Integer expected2 = ads2.removeFirst();
        assertEquals("removeFirst()", actual2, expected2);
        System.out.println("removeFirst()");

        actual2 = ads2.removeLast();
        expected2 = sad2.removeLast();
        assertEquals("removeLast()", actual2, expected2);
        System.out.println("removeLast()");
    }

    /**
    @Test
    public void testTask3(){
        ArrayDequeSolution<Integer> ads3 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad3 = new StudentArrayDeque<>();
        int actual3;
        int expected3;
        int i = 0;
        while (i < 10) {
            int random = StdRandom.uniform(100);
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                ads3.addLast(random);
                sad3.addLast(random);
                actual3 = ads3.get(i);
                expected3 = sad3.get(i);
                System.out.println("addLast(" + random + ")");
                assertEquals("addLast(" + random + ")", expected3, actual3);
                i = i + 1;
            } else if (numberBetweenZeroAndOne < 0.5){
                ads3.addFirst(random);
                sad3.addFirst(random);
                actual3 = ads3.get(i);
                expected3 = sad3.get(i);
                System.out.println("addFirst(" + random + ")");
                assertEquals("addFirst(" + random + ")", expected3, actual3);
                i = i + 1;
            } else if (numberBetweenZeroAndOne < 0.75 || ads3.size() != 0 || sad3.size() != 0){
                Integer c = ads3.removeFirst();
                Integer d = sad3.removeFirst();
                actual3 = c;
                expected3 = d;
                System.out.println("removeFirst(" + c + ")" + " and " + "removeFirst(" + d + ")");
                assertEquals("removeFirst(" + c + ")" + " and " + "removeFirst(" + d + ")", expected3, actual3);
                i = i - 1;
            } else if (ads3.size() != 0 || sad3.size() != 0){
                Integer a = ads3.removeLast();
                Integer b = sad3.removeLast();
                actual3 = a;
                expected3 = b;
                System.out.println("removeFirst(" + a + ")" + " and " + "removeFirst(" + b + ")");
                assertEquals("removeFirst(" + a + ")" + " and " + "removeFirst(" + b + ")", expected3, actual3);
                i = i + 1;
            }
        }
    }*/
}
