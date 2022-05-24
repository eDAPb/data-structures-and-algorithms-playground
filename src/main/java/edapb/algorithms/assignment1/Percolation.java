package edapb.algorithms.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int full = 0;
    private final int open = 1;
    private final int sideLength;
    private final int topNode;
    private final int bottomNode;

    private WeightedQuickUnionUF idsUF;
    private int[] status;

    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Using one dimensional array as grid.
        final int size = n*n;
        idsUF = new WeightedQuickUnionUF(size);
        status = new int[size];
        sideLength = n;
        // Keep top bottom opened by default.
        topNode = 0;
        status[topNode] = open;
        bottomNode = sideLength + 1;
        status[bottomNode] = open;
    }

    private int toIndex(int row, int col) {
        if (row <= 0 || row > sideLength || col <= 0 || col >= sideLength) {
            throw new IllegalArgumentException();
        }
        // First element is at (1, 1) -> arr[1]
        // We do -1 to maintain ourselves on the first row when row 1 is asked for.
        return (row - 1) * sideLength + col;
    }
    public void open(int row, int col) {
        int i = toIndex(row, col);
        if (status[i] == open) {
            return;
        }

        status[i] = open;
        // Implement unification of surrounding already opened nodes.

    }

    public boolean isOpen(int row, int col) {
        return status[toIndex(row, col)] == open;
    }

    public boolean isFull(int row, int col) {
        return status[toIndex(row, col)] == full;
    }

    public boolean percolates() {
        return idsUF.find(topNode) == idsUF.find(bottomNode);
    }
}
