public class UnionFind {
    private int arr[];

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        arr = new int[n];
        for (int i = 0; i < n; i += 1) {
            arr[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= arr.length || vertex < 0) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return arr[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) {
            arr[v1] = find(v1);
            arr[v2] = find(v2);
            return;
        }
        int a1 = find(v1);
        int a2 = find(v2);
        if (sizeOf(a1) > sizeOf(a2)) {
            arr[a1] = -(sizeOf(a1) + sizeOf(a2));
            arr[a2] = find(v1);
        } else if (sizeOf(a1) <= sizeOf(a2)) {
            arr[a2] = -(sizeOf(a1) + sizeOf(a2));
            arr[a1] = find(v2);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int temp = vertex;
        while (parent(vertex) > 0) {
            vertex = arr[vertex];
        }
        // Path-compression
        while (parent(temp) > 0) {
            arr[temp] = vertex;
            temp = parent(temp);
        }

        return vertex;
    }

    public static void main(String[] args) {
        UnionFind test = new UnionFind(10);
        test.union(2,3);
        test.union(1,2);
        test.union(5,7);
        test.union(8,4);
        test.union(2,7);
        test.union(0,6);
        test.union(6,4);
        test.union(6,3);
        System.out.println(test.find(0));
        test.union(8,7);
    }
}
