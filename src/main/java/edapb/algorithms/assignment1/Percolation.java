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

        // Spawn top and bottom nodes beyond border.
        sideLength = n;
        topNode = 0;
        status[topNode] = open;
        bottomNode = n*n + 1;
        status[bottomNode] = open;
    }

    private boolean inRange(int row, int col) {
        return row > 0 && row <= sideLength && col > 0 && col <= sideLength;
    }

    private void enforceRange(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException();
        }
    }
    private int toIndex(int row, int col) {
        // Lets us internally access the top and bottom node.
        if (row == 0) {
            return topNode;
        }

        if (row == sideLength + 1) {
            return bottomNode;
        }

        // First element is at (1, 1) -> arr[1]
        // We do -1 to maintain ourselves on the first row when row 1 is asked for.
        return (row - 1) * sideLength + col; // separate
    }
    public void open(int row, int col) {
        enforceRange(row, col);
        // Don't have to worry about going up or down since we'll hit top or bottom node no matter what.
        int[] indexes = new int[5];
        indexes[0] = toIndex(row, col);
        indexes[1] = toIndex(row + 1, col);
        indexes[2] = toIndex(row - 1, col);
        indexes[3] = inRange(row, col + 1) ? toIndex(row, col + 1) : -1;
        indexes[4] = inRange(row, col - 1) ? toIndex(row, col - 1) : -1;
        status[indexes[0]] = open;
        for (int i : indexes) {
            if (i != -1 && status[i] == open) {
                idsUF.union(indexes[0], i);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        enforceRange(row, col);
        return status[toIndex(row, col)] == open;
    }

    public boolean isFull(int row, int col) {
        enforceRange(row, col);
        return status[toIndex(row, col)] == full;
    }

    public boolean percolates() {
        return idsUF.find(topNode) == idsUF.find(bottomNode);
    }
}
