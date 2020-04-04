/** Array based list.
 *  @author Josh Hug
 */

public class AList<Item> {
    Item[] items;
    int size;

    /** Creates an empty list. */
    public AList() {
        items = (Item[]) new Object[5];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length){
            Item[] big = (Item[]) new Object[size*2];
            System.arraycopy(items, 0, big, 0, size);
            big[size] = x;
            items = big;
            size = size + 1;
        }else {
            items[size] = x;
            size += 1;
        }
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item x = items[size];
        items[size -1] = null;
        size = size - 1;
        return x;
    }

    public static void main(String[] args){
        AList<Integer> L = new AList<>();
        int N = 10;
        for (int i = 0; i < N; i += 1) {
            L.addLast(i);
        }

        for (int i = 0; i < N; i += 1) {
            L.addLast(L.get(i));
        }
        System.out.println("DONE");
    }
} 