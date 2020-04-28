import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private TreeNode TreeRoot;
    private int size;

    public class TreeNode<K, V> {
        private K key;
        private V value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    @Override
    public void clear() {
        TreeRoot = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        V temp = get(key);
        if (temp != null) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(TreeRoot, key);
    }

    private V get(TreeNode T, K key) {
        if (T == null) {
            return null;
        }
        int cmp = key.compareTo((K) T.key);
        if (cmp < 0) {
            return (V) get(T.left, key);
        } else if (cmp > 0){
            return (V) get(T.right, key);
        } else {
            return (V) T.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        TreeRoot = put(TreeRoot, key, value);
    }

    private TreeNode put(TreeNode T, K key, V value) {
        if (T == null) {
            size = size + 1;
            return new TreeNode(key, value);
        }
        int cmp = key.compareTo((K) T.key);
        if (cmp < 0) {
            T.left = put(T.left, key, value);
        } else if (cmp > 0){
            T.right = put(T.right, key, value);
        } else {
            T.value = value;
        }
        return T;
    }

    public void printInOrder() {
        if (size() == 0) {
            System.out.println("Nothing in here");
            return;
        } else {
            printInOrder(TreeRoot);
            return;
        }
    }

    private void printInOrder(TreeNode T) {
        if (size == 0) {
            return;
        }
        if (T.left != null) {
            printInOrder(T.left);
        }
        System.out.println("Key: " + T.key + " Value: " + T.value);
        if (T.right != null) {
            printInOrder(T.right);
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V temp = get(key);
        TreeRoot = deleteRecursive(TreeRoot, key);
        return temp;
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        if (!get(key).equals(value)) {
            return null;
        }
        TreeRoot = deleteRecursive(TreeRoot, key);
        return value;
    }

    private TreeNode deleteRecursive(TreeNode current, K key) {
        if (current == null) {
            return null;
        }
        int cmp = key.compareTo((K) current.key);
        if (cmp < 0) {
            current.left = deleteRecursive(current.left, key);
        } else if (cmp > 0) {
            current.right = deleteRecursive(current.right, key);
        } else {
            if (current.left == null && current.right == null) {
                size = size - 1;
                return null;
            }
            if (current.left == null) {
                size = size - 1;
                return current.right;
            }
            if (current.right == null) {
                size = size - 1;
                return current.left;
            }
            TreeNode smallest = findSmallest(current.right);
            current.key = smallest.key;
            current.value = smallest.value;
            current.right = deleteRecursive(current.right, (K) smallest.key);
        }
        return current;
    }

    private TreeNode findSmallest(TreeNode T) {
        if (T.left == null) {
            return T;
        }
        return findSmallest(T.left);
    }


    @Override // 不會
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
    public class bSTMapIterator implements Iterator<K> {
        private TreeNode Tree;

        public bSTMapIterator() {
            Tree = TreeRoot;
        }

        public boolean hasNext() {
            if (Tree == null || size == 0) {
                return false;
            } else {
                return hasNext(Tree);
            }
        }

        private boolean hasNext(TreeNode T) {
            if (T == null) {
                return false;
            }
            if (T.left != null) {
                return hasNext(T.left);
            }
            if (T.right == null) {
                return hasNext(T.right);
            }
            return true;
        }

        public K next() {
            return (K) Tree.key;
        }
    }*/

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 3; i < 7; i++) {
            b.put("hi" + i, i);
        }
        b.put("hi" + 2, 2);
        b.put("hi" + 9, 9);
        b.put("hi" + 8, 8);
        b.printInOrder();
    }
}
