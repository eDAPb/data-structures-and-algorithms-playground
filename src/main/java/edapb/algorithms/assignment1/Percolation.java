package edapb.algorithms.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final byte CLOSE = 0b0000; // 0
    private static final byte OPEN = 0b0001; // 1
    private static final byte TOP_CONN = 0b0010; // 2
    private static final byte BOTTOM_CONN = 0b0100; // 4
    private static final byte PERCOLATION = 0b0111; // 7
    private final int sideLength;
    private final int topNode;

    private final WeightedQuickUnionUF idsUF;
    private final byte[] status;
    private int numOfOpenSites;
    private boolean percolates;

    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Using one dimensional array as grid.
        final int size = n * n + 1;
        sideLength = n;
        idsUF = new WeightedQuickUnionUF(size);
        status = new byte[size];
        // We will track opened bottom nodes and check if they are full to
        // confirm for percolation later. First index reserved for counting.
        numOfOpenSites = 0;

        // Spawn top virtual node.
        topNode = 0;
        status[topNode] = TOP_CONN;
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
        // Lets us internally access the top node.
        if (row == 0) {
            return topNode;
        }

        if (!inRange(row, col)) {
            return -1;
        }

        // First element is at (1, 1) -> arr[1]
        // We do -1 to maintain ourselves on the first row when row 1 is asked for.
        return (row - 1) * sideLength + col; // separate
    }
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        int[] indexes = new int[5];
        indexes[0] = toIndex(row, col);
        final int bottomRow = (sideLength - 1) * sideLength;
        if (indexes[0] <= sideLength) {
            status[indexes[0]] = TOP_CONN;
        } else if (indexes[0] > bottomRow) {
            status[indexes[0]] = BOTTOM_CONN;
        } else {
            status[indexes[0]] = OPEN;
        }
        ++numOfOpenSites;

        // Check for adjacent nodes, toIndex returns -1 if not found.
        // Up
        indexes[1] = toIndex(row - 1, col);
        // Down
        indexes[2] = toIndex(row + 1, col);
        // Right
        indexes[3] = toIndex(row, col + 1);
        // Left
        indexes[4] = toIndex(row, col - 1);

        byte statusGen = CLOSE;
        for (int i : indexes) {
            if (i != -1) {
                statusGen = (byte) (statusGen | status[i]);
                System.out.println(statusGen);
            }
        }

        for (int i : indexes) {
            if (i != -1 && status[i] != CLOSE) {
                status[i] = statusGen;
                idsUF.union(i, indexes[0]);
            }
        }

        if (status[indexes[0]] == PERCOLATION) {
            percolates = true;
        }
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean isOpen(int row, int col) {
        enforceRange(row, col);
        return status[toIndex(row, col)] >= OPEN;
    }

    public boolean isFull(int row, int col) {
        enforceRange(row, col);
        int i = toIndex(row, col);
        boolean isFull = (status[i] >= OPEN) &&
                (idsUF.find(topNode) == idsUF.find(i));
        if (isFull && status[i] == BOTTOM_CONN) {
            status[i] = PERCOLATION;
        }

        return isFull;
    }

    public boolean percolates() {
        return percolates;
    }
}
