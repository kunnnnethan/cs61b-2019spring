public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    private IntNode sentinel;
    private IntNode last;
    private int size;

    /** 這行不知道為什麼要存在*/
    public SLList(){
        sentinel = new IntNode(3, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(3, null);
        last = new IntNode(3, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size = size + 1;
    }
    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }

    public int size(){
        return size;
    }


    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(4);
        int x = L.getFirst();
        System.out.println(L.size());
    }
}