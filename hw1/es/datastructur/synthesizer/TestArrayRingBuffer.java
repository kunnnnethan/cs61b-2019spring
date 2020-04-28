package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        double actual;
        double expected;
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(33.1);
        int size = arb.fillCount();
        int truesize = 1;
        assertEquals(truesize, size);
        arb.enqueue(44.8);
        actual = (double) arb.peek();
        expected = 33.1;
        assertEquals(expected, actual, 0.01);
        arb.enqueue(62.3);
        arb.enqueue(-3.4);
        boolean trueornot = arb.isFull();
        boolean answer = true;
        assertEquals(answer, trueornot);
        actual = (double) arb.dequeue();
        expected = 33.1;
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void test() {
        double actual;
        double expected;
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(33.1);
        arb.enqueue(56.8);
        arb.dequeue();
        arb.enqueue(33.1);
        arb.dequeue();
        arb.enqueue(33.1);
        arb.dequeue();
        arb.enqueue(33.1);
        arb.dequeue();
        arb.enqueue(33.1);
        arb.dequeue();
        expected = (double) arb.peek();
        actual = 33.1;
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        for (Object i : arb) {
            System.out.println(i);
        }
    }

    @Test
    public void testEqual() {
        ArrayRingBuffer abc = new ArrayRingBuffer(4);
        abc.enqueue(1);
        abc.enqueue(2);
        abc.enqueue(3);
        abc.enqueue(4);
        ArrayRingBuffer def = new ArrayRingBuffer(4);
        def.enqueue(1);
        def.enqueue(2);
        def.enqueue(3);
        def.enqueue(4);

        boolean trueornot = def.equals(abc);
        assertEquals(true, trueornot);
    }

    @Test
    public void testError() {
        ArrayRingBuffer abc = new ArrayRingBuffer(4);
        abc.enqueue(1);
        abc.enqueue(2);
        abc.enqueue(3);
        abc.enqueue(4);
        try {
            abc.enqueue(5);
        } catch (RuntimeException e) {
            System.out.println("I caught it!");
        }
    }

    @Test
    public void testError2() {
        ArrayRingBuffer def = new ArrayRingBuffer(4);
        try {
            def.peek();
        } catch (RuntimeException e) {
            System.out.println("I caught it!");
        }
    }

    @Test
    public void testError3() {
        ArrayRingBuffer def = new ArrayRingBuffer(4);
        try {
            def.dequeue();
        } catch (RuntimeException e) {
            System.out.println("I caught it!");
        }
    }
}
