public class PracticeALList {
    int[] arr;
    int size = 0;
    public PracticeALList(){
        arr = new int[1];
    }

    public void addLast(int item){
        if(arr.length == size){
            int[] i = new int[arr.length+1];
            System.arraycopy(arr, 0, i, 0, arr.length);
            i[size] = item;
            arr = i;
        }else{
            arr[size] = item;
        }
        size += 1;
    }

    public void printDeque(){
        int i = 0;
        while (i < arr.length){
            System.out.print(arr[i]+" ");
            System.out.println();
            i += 1;
        }
    }

    public static void printDequeA(int[] X){
        int i = 0;
        while (i < X.length){
            System.out.print(X[i]+" ");
            System.out.println();
            i += 1;
        }
    }

    public static int[] insert(int[] att, int item, int position){
        int[] i = new int[att.length+1];
        int z = 0;
        for(int j = 0; j < att.length+1; j++){
            if(j == position){
                i[j] = item;
                continue;
            }
            i[j] = att[z];
            z += 1;
        }
        return i;
    }

    public static int[] reverse(int[] att){
        for(int j = 0; j < att.length*0.5; j++){
            int temp = att[j];
            att[j] = att[att.length - 1 - j];
            att[att.length - 1 - j] = temp;
        }
        return att;
    }

    public static int[] replicate(int[] att){
        int total = 0, size = 0, based = 0;
        for(int j = 0; j < att.length; j++){
            total = total + att[j];
        }
        int[] i = new int[total];
        for(int j = 0; j < att.length; j++){
            size = 0;
            while (size < att[j]){
                i[based] = att[j];
                size += 1;
                based += 1;
            }
        }
        return i;
    }

    public static void main(String[] args){
        PracticeALList L = new PracticeALList();
        L.addLast(3);
        L.addLast(2);
        L.addLast(1);
        L.printDeque();
        int[] A = new int[]{1, 2, 3, 4, 5, 6};
        printDequeA(insert(A, 10, 1));
        printDequeA(reverse(A));
        /** 這裡不知道為什麼A會被上面的影響 */
        A = new int[]{1, 2, 3, 4, 5, 6};
        printDequeA(replicate(A));
    }
}
