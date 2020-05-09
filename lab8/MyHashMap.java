import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private int size = 0;
    public Node[] table;
    public HashSet<K> allKey;

    private class Node<K, V> {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        table = new Node[initialSize];
        allKey = new HashSet<>();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        table = new Node[initialSize];
    }


    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        table = new Node[initialSize];
    }

    @Override
    public void clear() {
        int tableLength = table.length;
        table = new Node[tableLength];
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index = index + table.length;
        }
        Node currNode = table[index];
        while (currNode != null) {
            if (currNode.key.equals(key)) {
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index = index + table.length;
        }
        Node currNode = table[index];
        while (currNode != null) {
            if (currNode.key.equals(key)) {
                return currNode.value;
            }
            currNode = currNode.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Object key, Object value) {

        if (size() / table.length >= 0.75) {
            reSize();
        }

        int index = key.hashCode() % table.length;

        if (index < 0) {
            index = index + table.length;
        }

        if (containsKey(key)) {
            Node currNode = table[index];
            while (currNode != null) {
                if (currNode.key.equals(key)) {
                    currNode.value = value;
                }
                currNode = currNode.next;
            }
            return;
        }

        if (table[index] != null) {
            Node currNode = table[index];
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = new Node(key, value, null);
            allKey.add((K) key);
            size += 1;
            return;
        }

        Node newnode = new Node(key, value, null);
        table[index] = newnode;
        allKey.add((K) key);
        size += 1;
    }

    private Node[] reSize() {
        Node[] temp = new Node[table.length * 2];

        for (Node i : table) {
            if (i == null) {
                continue;
            }

            int index = i.key.hashCode() % (table.length * 2);

            if (index < 0) {
                index = index + table.length * 2;
            }

            if (temp[index] != null) {
                Node currNode = temp[index];
                while (currNode.next != null) {
                    currNode = currNode.next;
                }
                currNode.next = i;
                continue;
            }

            temp[index] = i;
        }

        table = temp;
        return table;
    }

    @Override
    public Set keySet() {
        return allKey;
    }

    @Override
    public Object remove(Object key) {
        Object tempValue = null;

        if (!containsKey(key)) {
            return null;
        }

        int index = key.hashCode() % table.length;
        Node currNode = table[index];

        // currNode只有一個的時候
        if (currNode.next == null) {
            tempValue = currNode.value;
            table[index] = null;
            return tempValue;
        }

        // currNode多於一個的時候
        if (currNode.next != null) {
            // currNode.key是在第一個的時候
            if (currNode.key == key) {
                tempValue = currNode.value;
                Node temp = currNode.next;
                currNode = null;
                table[index] = temp;
            }

            // currNode.key不是在第一個的時候
            while (currNode.next != null) {
                if (currNode.next.key == key) {
                    tempValue = currNode.next.value;
                    Node temp = currNode.next.next;
                    currNode.next = null;
                    currNode.next = temp;
                    break;
                }
                currNode = currNode.next;
            }
        }
        return tempValue;
    }

    @Override
    public Object remove(Object key, Object value) {
        if (!containsKey(key)) {
            return null;
        }

        int index = key.hashCode() % table.length;
        Node currNode = table[index];

        // currNode只有一個的時候
        if (currNode.next == null) {
            currNode = null;
        }

        // currNode多於一個的時候
        if (currNode.next != null) {
            // currNode.key是在第一個的時候
            if (currNode.key == key) {
                Node temp = currNode.next;
                currNode = null;
                table[index] = temp;
            }

            // currNode.key不是在第一個的時候
            while (currNode.next != null) {
                if (currNode.next.key == key) {
                    Node temp = currNode.next.next;
                    currNode.next = null;
                    currNode.next = temp;
                    break;
                }
                currNode = currNode.next;
            }
        }
        return value;
    }

    @Override
    public Iterator iterator() {
        return new hashMapIterator();
    }

    private class hashMapIterator implements Iterator<K>{
        Iterator<K> itr = allKey.iterator();

        @Override
        public boolean hasNext() { // 應該是這樣吧？！
            return itr.hasNext();
        }

        @Override
        public K next() { // 應該是這樣吧？！
            return itr.next();
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        b.put("waterYouDoingHere", 0);
    }
}


