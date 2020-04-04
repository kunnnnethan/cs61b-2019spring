public class PracticeSLList {
    private class IntNode{
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next){
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public PracticeSLList(){
        first = new IntNode(3, null);
    }

    public void addFirst(int x){
        first = new IntNode(x, first);
    }

    public void insert(int item, int position){
        if (position == 0 || first == null){
            addFirst(item);
            return;
        }
        IntNode X = first, Y;
        while(position > 1 && X.next != null){
            X = X.next;
            position -= 1;
        }
        Y = new IntNode(item, X.next);
        X.next = Y;
    }

    public void reverseIterative(){
        if (first == null || first.next == null){
            return;
        }

        IntNode ptr = first.next;
        first.next = null;

        while(ptr != null){
            IntNode temp = ptr.next;
            ptr.next = first;
            first = ptr;
            ptr = temp;
        }
    }

    public void reverseRecursive(){
        if (first == null || first.next == null){
            return;
        }
        IntNode ptr = first.next;
        first.next = null;
        reverseRecursiveHelperMethod(ptr);
    }

    public IntNode reverseRecursiveHelperMethod(IntNode T){
        if (T == null){
            return null;
        }
        IntNode temp = T.next;
        T.next = first;
        first = T;
        T = temp;
        return reverseRecursiveHelperMethod(T);
    }

    public void reverseOtherSolution(){
        first = reverseOtherSolutionHelper(first);
    }

    private IntNode reverseOtherSolutionHelper(IntNode lst){
        if (lst == null || lst.next == null){
            return lst;
        }else{
            IntNode endOfReversed = lst.next;
            IntNode reversed = reverseOtherSolutionHelper(lst.next);
            endOfReversed.next = lst;
            lst.next = null;
            return reversed;
        }
    }

    public static void main(String[] args){
        PracticeSLList L = new PracticeSLList();
        L.addFirst(2);
        L.addFirst(1);
        //L.addFirst(10);
        //L.insert(10, 3);
        //L.reverseIterative();
        //L.reverseRecursive();
        L.reverseOtherSolution();
    }
}
