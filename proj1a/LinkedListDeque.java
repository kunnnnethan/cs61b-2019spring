// 不知道為什麼一直跑出警告 但是用run的方法可以跑出來 應該是<T>在某個地方沒有加到 應該忽略就好
@SuppressWarnings("unchecked")

public class LinkedListDeque<T> {
    /** Give a list that help build up non naked list*/
    // circular List
    public class LinkedNode<T> {
        public T item;
        public LinkedNode next;
        public LinkedNode prev;

        public LinkedNode(T i, LinkedNode p, LinkedNode n) {
            item = i;
            next = n;
            prev = p;
        }
    }
    /** provide a sentinel list that prevent the first box to be non*/
    private LinkedNode sentinel;
    /**private LinkedNode last;*/
    private int size;

    /** 環形List 當沒有任何List時 使prev 何 next 都指向sentinel*/
    public LinkedListDeque(){
        sentinel = new LinkedNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    // Deep copy LinkedListDeque 意義不明
    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new LinkedNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for (int i = 1; i < other.size(); i = i+1){
            addLast((T) other.get(i));
        }
    }

    /** add a list to the front*/
    public void addFirst(T i) {
        LinkedNode<T> newFirst = new LinkedNode<T>(i, sentinel, sentinel.next);
        sentinel.next.prev = newFirst;
        sentinel.next = newFirst;
        size = size + 1;
    }
    /** add a list to the end of the list*/
    public void addLast(T i) {
        LinkedNode<T> newLast = new LinkedNode<T>(i, sentinel.prev, sentinel);
        sentinel.prev.next = newLast;
        sentinel.prev = newLast;
        size = size + 1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    // 輸出每個list的值
    public void printDeque(){
        LinkedNode M = sentinel.next;
        while(M != sentinel){
        System.out.print(M.item);
        System.out.print(" ");
        M = M.next;
        }
        System.out.println(" ");
    }

    /** remove first iter */
    public T removeFirst(){
        if(sentinel.next == null){
            return null;
        }else{
            T M = (T) sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size = size - 1;
            return M;
        }
    }

    /** remove last item */
    public T removeLast(){
        if(sentinel.prev == null){
            return null;
        }else{
            T M = (T) sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size = size - 1;
            return M;
        }
    }

    /** get specific item */
    public T get(int index){
        if (size == 0 || index > size){
            return null;
        }
        int i = 1;
        LinkedNode M = sentinel.next;
        T N;
        while(i < index){
            M = M.next;
        }
        return (T) M.item;
    }

    /** get specific item using recursive skill */
    public T getRecursive(int index){
        if (size == 0 || index > size){
            return null;
        }
        return (T) getRecursiveHelper(sentinel.next, index);
    }

    /** Helper method for getRecursive */
    public T getRecursiveHelper(LinkedNode<T> K, int index){
        if (index == 1){
            return (T) K.item;
        }
        return (T) getRecursiveHelper(K.next, index -1);
    }

    /** main constructor */
    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<Integer>();
        L.isEmpty();
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(4);
        System.out.println(L.size());
        L.printDeque();
    }
}
