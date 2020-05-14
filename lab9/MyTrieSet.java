import java.util.*;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 128;
    private Node root;
    private int size;

    public MyTrieSet() {
        root = new Node( '\0', false);
        size = 0;
    }

    private class Node {
        private char ch;
        private boolean isKey;
        private Map<Character, Node> map;

        private Node(char ch, boolean isKey) {
            this.ch = ch;
            this.isKey = isKey;
            map = new HashMap<>();
        }
    }

    /**
     *     public class DataIndexedCharMap<V> {
     *         private V[] items;
     *
     *         public DataIndexedCharMap(int R) {
     *             items = (V[]) new Object[R];
     *         }
     *     }
     */

    @Override
    public void clear() {
        root = new Node( '\0', false);
        size = 0;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1 || root.map.isEmpty()) {
            return false;
        }
        boolean trueOrFalse = false;
        Node curr = root;

        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr = curr.map.get(c);
                i -= 1;
                trueOrFalse = false;
                continue;
            }
            curr = curr.map.get(c);
            trueOrFalse = curr.isKey;
        }
        return trueOrFalse;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() < 1 || root.map.isEmpty()) {
            return null;
        }
        Node curr = root;

        for (int i = 0; i < prefix.length(); i += 1) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr = curr.map.get(c);
                i -= 1;
                continue;
            }
            curr = curr.map.get(c);
        }

        List<String> allKeys = new ArrayList<>();

        if (curr.isKey == true) { // If prefix itself is a key, add it to the result.
            allKeys.add(prefix);
        }

        for (char c : curr.map.keySet()) {
            collectHelper(prefix + c, allKeys, curr.map.get(c));
        }

        return allKeys;
    }

    private void collectHelper(String prefix, List<String> allKeys, Node curr) {
        if (curr.isKey == true) {
            allKeys.add(prefix);
        }
        for (char c : curr.map.keySet()) {
            collectHelper(prefix + c, allKeys, curr.map.get(c));
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
