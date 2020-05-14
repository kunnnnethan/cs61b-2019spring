package bearmaps;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    @Test
    public void addTest() {
        ArrayHeapMinPQ<String> temp = new ArrayHeapMinPQ<>();
        temp.add("apple", 6);
        temp.add("banana", 1);
        temp.add("watermalon", 3.5);
        temp.add("cat", 2.1);
        temp.add("dog", 5);
        temp.add("duck", 4.7);
        assertEquals("banana", temp.getSmallest());

        temp.removeSmallest();
        temp.removeSmallest();
        temp.removeSmallest();
        temp.removeSmallest();
        assertEquals("dog", temp.getSmallest());

    }

    @Test
    public void containTest() {
        ArrayHeapMinPQ<String> temp = new ArrayHeapMinPQ<>();
        PrintHeapDemo demo = new PrintHeapDemo();
        temp.add("apple", 6);
        temp.add("banana", 1);
        temp.add("watermalon", 3.5);
        temp.add("cat", 2.1);
        temp.add("dog", 5);
        temp.add("duck", 4.7);
        assertEquals(true, temp.contains("banana"));
        assertEquals(false, temp.contains("elephant"));

    }

    @Test
    public void sizeTest() {
        ArrayHeapMinPQ<String> temp = new ArrayHeapMinPQ<>();
        temp.add("apple", 6);
        temp.add("banana", 1);
        temp.add("watermalon", 3.5);
        temp.add("cat", 2.1);
        temp.add("dog", 5);
        temp.add("duck", 4.7);
        temp.removeSmallest();
        temp.removeSmallest();
        temp.removeSmallest();
        temp.removeSmallest();
        assertEquals(2, temp.size());

    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> temp = new ArrayHeapMinPQ<>();
        temp.add("apple", 6);
        temp.add("banana", 1);
        temp.add("watermalon", 3.5);
        temp.add("cat", 2.1);
        temp.add("dog", 5);
        temp.add("duck", 4.7);
        try {
            temp.changePriority("elephant", 5);
        } catch (NoSuchElementException e){
            System.out.println("No such element in this heap");
        }

        temp.changePriority("banana", 9);
        assertEquals("cat", temp.getSmallest());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 200000; i += 1) {
            minHeap.add(i, 100000 - i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 200000; j += 1) {
            minHeap.changePriority(j, j + 1);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");

        long start3 = System.currentTimeMillis();
        NaiveMinPQ<Integer> minHeap2 = new NaiveMinPQ<>();
        for (int i = 0; i < 200000; i += 1) {
            minHeap2.add(i, 100000 - i);
        }
        for (int j = 0; j < 200000; j += 1) {
            minHeap2.changePriority(j, j + 1);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end3 - start3) / 1000.0 +  " seconds.");
    }
}
