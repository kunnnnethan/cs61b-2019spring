public class Sort {
    /** Sorts strings destructively. */
    public static void sort(String[] x, int start) {
        // find the smallest item
        // move it to the front
        // selection sort the rest (using recursion?)
        if (start == x.length){
            return;
        }
        int smallestIndex = findSmallest(x, start);
        swap(x, start, smallestIndex);
        sort(x, start + 1);
    }

    public static int findSmallest(String[] x, int start){
        //String smallest = x[0];
        int smallestIndex = start;
        for (int i = start; i < x.length; i++){
            int cmp = x[i].compareTo(x[smallestIndex]);
            if (cmp < 0){
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public static void swap(String[] x, int a, int b){
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}
