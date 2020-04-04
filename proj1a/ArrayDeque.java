@SuppressWarnings("unchecked")

public class ArrayDeque<T> {
    T[] arr;
    int size;
    int nextLast;
    int nextFirst;

    /** 有迴圈性質的array */
    public ArrayDeque(){
        arr = (T[]) new Object[8];
        size = 0;
        nextFirst = arr.length-1;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other){
        size = 0;
        nextFirst = arr.length-1;
        nextLast = 1;
        for(int i = 0; i < other.size(); i++){
            addLast((T) other.get(i));
        }
    }

    private int minusOne(int index){
        if (index-1 < 0){
            return arr.length-1;
        }else{
            return index - 1;
        }
    }

    private int plusOne(int index){
        if (index+1 == arr.length){
            return 0;
        }else{
            return index + 1;
        }
    }

    /** 當size超過array長度時 將array的長度平方（平方可以節省大量的計算時間，因為array在增加長度的計算時間是以指數型在成長）*/
    private void resize(int volume){
        T[] i = (T[]) new Object[volume];
        for(int j = 0; j < size; j++) {
            /** 避免重新寫入array的時候將null也寫入 遇到null時自動跳開 */
            if (arr[plusOne(nextFirst)] == null){
                nextFirst = plusOne(nextFirst);;
            }
            i[j] = arr[plusOne(nextFirst)];
            nextFirst = plusOne(nextFirst);
        }
        arr = i;
        nextFirst = arr.length-1;
        nextLast = size;
    }

    public void addFirst(T item){
        if (size == arr.length || nextFirst == nextLast){
            resize(size*2);
        }
        arr[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item){
        if (size == arr.length || nextFirst == nextLast){
            resize(size*2);
        }
        arr[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int i = 0;
        while (i < arr.length){
            System.out.print(arr[i]+" ");
            System.out.println();
            i += 1;
        }
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T x = arr[plusOne(nextFirst)];
        arr[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;

        /** 判斷array長度 為了節省記憶體 當size除以總長度小於四分之一時 將總長度砍半 */
        if(size < 0.25 * arr.length){
            resize(arr.length/2);
        }
        return (T) x;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        T x = arr[minusOne(nextLast)];
        arr[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;

        /** 判斷array長度 為了節省記憶體 當size除以總長度小於四分之一時 將總長度砍半 */
        if(size < 0.25 * arr.length){
            resize(arr.length/2);
        }
        return (T) x;
    }

    public T get(int index){
        if (index >= arr.length || size == 0){
            return null;
        }
        T x = arr[index];
        return (T) x;
    }

    public static void main(String[] args){
        ArrayDeque<Integer> L = new ArrayDeque<>();
        int N = 8;
        for (int i = 0; i < N; i += 1) {
            L.addLast(i);
        }
        L.addFirst(6);
        L.addFirst(5);
        L.addLast(10);
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.printDeque();

        System.out.println("DONE");
    }
}
