package edapb.algorithms.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int full = 0;
    private final int open = 1;
    private final int width;
    private final int height;

    private WeightedQuickUnionUF ids;
    private int[] status;

    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Using one dimensional array as grid.
        final int size = n*n;
        ids = new WeightedQuickUnionUF(size);
        status = new int[size];
        width = n;
        height = n;
    }

    private int toIndex(int row, int col) {
        if (row <= 0 || row > height || col <= 0 || col >= width) {
            throw new IllegalArgumentException();
        }
        // First element is at (1, 1).
        // We do -1 to maintain ourselves on the first row when row 1 is asked for.
        return (row - 1) * width + col;
    }
    public void open(int row, int col) {
        status[toIndex(row, col)] = open;
    }

    public boolean isOpen(int row, int col) {
        return status[toIndex(row, col)] == open;
    }

    public boolean isFull(int row, int col) {
        return status[toIndex(row, col)] == full;
    }
}
