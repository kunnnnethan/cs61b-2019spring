package DataStructure;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> {

    Node[] root;
    HashMap<T, Integer> collection;
    int size;

    private class Node implements Comparable<Node> {
        private T item;
        private double priority;

        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        double getPriority() {
            return this.priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), o.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            } else {
                return this.item.equals(((Node) o).item);
            }
        }
    }

    public ArrayHeapMinPQ() {
        root = new  ArrayHeapMinPQ.Node[2];
        collection = new HashMap();
        size = 0;
    }

    public void add(T item, double priority) {
        // resizing
        if (size + 1 >= root.length) {
            Node[] temp = root;
            root = new ArrayHeapMinPQ.Node[root.length * 2];
            System.arraycopy(temp, 0, root, 0, temp.length);
        }

        size += 1;
        root[size] = new Node(item, priority);
        collection.put(item, size);
        swim(size);
    }

    public boolean contains(T item) {
        return collection.containsKey(item);
    }

    public T getSmallest() {
        return root[1].item;
    }

    public T removeSmallest() {
        Node temp = root[1];
        root[1] = root[size];
        collection.remove(temp); // 之前忘記把Map裡面的值也remove了
        collection.put(root[1].item, 1); // 忘記這行一直出現 NullPointerException
        root[size] = null;
        size -= 1;
        sink(1);
        return temp.item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int i = collection.get(item);
        double temp = root[i].priority;
        root[i].setPriority(priority);
        if (priority <= temp) {
            swim(i);
        } else {
            sink(i);
        }
    }

    // Helper Method
    private int leftChild(int k) {
        return k * 2;
    }

    private int rightChild(int k) {
        return k * 2 + 1;
    }

    private int parent(int k) {
        return k / 2;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (leftChild(k) <= size) {
            int smallest = k;
            if (greater(k, leftChild(k))) {
                smallest = leftChild(k);
            }
            if (greater(leftChild(k), rightChild(k)) && rightChild(k) <= size) {
                smallest = rightChild(k);
            }
            if (smallest == k) {
                break;
            }
            exch(k, smallest);
            k = smallest;
        }
    }

    private void exch(int i, int j) {
        Node temp = root[i];
        root[i] = root[j];
        root[j] = temp;
        collection.put(root[i].item, i);
        collection.put(root[j].item, j);
    }

    private boolean greater(int i, int j) {
        return root[i].compareTo(root[j]) > 0;
    }
}
