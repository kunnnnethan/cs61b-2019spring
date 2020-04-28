package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    private int capacity;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        if (last == capacity) {
            last = 0;
            rb[last] = x;
            last += 1;
        } else {
            rb[last] = x;
            last += 1;
        }
        fillCount += 1;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T temp = null;
        if (first == capacity) {
            first = 0;
            temp = rb[first];
            rb[first] = null;
            first += 1;
        } else {
            temp = rb[first];
            rb[first] = null;
            first += 1;
        }
        fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (first == capacity) {
            return rb[0];
        }
        if (isEmpty()) {
            throw new RuntimeException("Nothing inside this asshole");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    private class ARBIterator implements Iterator<T> {
        private int pos;
        private int count;

        public ARBIterator() {
            pos = first;
            count = 0;
        }

        @Override
        public boolean hasNext() { //  這裡有東西嗎？ 有的話回傳有 hasNext永遠先判定
            return count < fillCount;
        }

        @Override
        public T next() { //  上面回傳有之後，就輸出當下的值 然後往後移一個
            T currentItem = rb[pos];
            pos = (pos + 1) % capacity();
            count += 1;
            return currentItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        Iterator<T> thisItem = this.iterator();
        Iterator<T> otherItem = other.iterator();
        while (thisItem.hasNext() && otherItem.hasNext()) {
            if (thisItem.next() != otherItem.next()) {
                return false;
            }
        }
        return true;
    }

    /**
     * return size of the buffer
     */
    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
}
