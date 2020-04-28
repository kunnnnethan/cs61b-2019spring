package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation extends WeightedQuickUnionUF {
    private boolean[][] grid;
    private int size;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom; // to avoid backwash
    private int top;
    private int bottom;

    public Percolation(int N) { // create N-by-N grid, with all sites initially blocked
        super(N * N + 2);
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
        size = N;
        top = N * N; // virtual top node
        bottom = N * N + 1; // virtual bottom node
    }

    private int xyTo1D(int r, int c) {
        return r * size + c;
    }

    public void open(int row, int col) { // open the site (row, col) if it is not open already
        if (row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        int left = row - 1;
        int right = row + 1;
        int up = col + 1;
        int down = col - 1;
        if (left >= 0 && left < size && isOpen(left, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(left, col));
            ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(left, col));
        }
        if (right >= 0 && right < size && isOpen(right, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(right, col));
            ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(right, col));
        }
        if (up >= 0 && up < size && isOpen(row, up)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, up));
            ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(row, up));
        }
        if (down >= 0 && down < size && isOpen(row, down)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, down));
            ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(row, down));
        }
        if (row == 0) {
            uf.union(top, xyTo1D(row, col));
            ufExcludeBottom.union(top, xyTo1D(row, col));
        }
        if (row == size - 1) {
            uf.union(bottom, xyTo1D(row, col));
        }
        numberOfOpenSites += 1;
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        if (row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        if (row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return ufExcludeBottom.connected(top, xyTo1D(row, col));
    }

    public int numberOfOpenSites() { // number of open sites
        return numberOfOpenSites;
    }

    public boolean percolates() { // does the system percolate?
        /* 設定top and bottom兩個參數可以省去很多不必要的回圈，想像在一半以上的點都連接top，一半以下都連接bottom
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (uf.connected(xyTo1D(0, i), xyTo1D(size - 1, j))) {
                    return true;
                }
            }
        }*/
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) { // use for unit testing (not required, but keep this here for the autograder)
    }
}
