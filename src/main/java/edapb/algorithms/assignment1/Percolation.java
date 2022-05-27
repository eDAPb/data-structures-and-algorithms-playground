package edapb.algorithms.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int OPEN = 0b100;
    private static final int TOP_CONN = 0b110;
    private static final int BOTTOM_CONN = 0b101;
    private static final int PERCOLATION = 0b111;

    private final int sideLength;
    private final int nullNode;

    private final WeightedQuickUnionUF idsUF;
    private final byte[] status;
    private boolean percolates;
    private int numOfOpenSites;

    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Using one dimensional array as grid. min (1, 1) max (n, n)
        final int size = n * n + 1;
        sideLength = n;
        nullNode = 0;           // Serves as dummy node for incorrect access
        idsUF = new WeightedQuickUnionUF(size);
        status = new byte[size]; // Keep nullNode status as CLOSED = 0b000
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
        if (!inRange(row, col)) {
            return nullNode;
        }

        // We subtract 1 to get the correct index, row 1 starts at index 0
        // ((row1 - 1) * n = 0) then add the column you want
        return (row - 1) * sideLength + col;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return; // No need to be opened already open
        }

        int[] indexes = new int[5];
        indexes[0] = toIndex(row, col);

        // Instead of using virtual nodes, status is defined by index
        final int bottomRow = (sideLength - 1) * sideLength + 1;
        status[indexes[0]] = OPEN;  // We use bitwise OR to avoid n = 1 bug
        if (indexes[0] <= sideLength) {
            status[indexes[0]] = (byte) (status[indexes[0]] | TOP_CONN);
        }

        if (indexes[0] >= bottomRow) {
            status[indexes[0]] = (byte) (status[indexes[0]] | BOTTOM_CONN);
        }
        ++numOfOpenSites;

        // Get adjacent nodes to compare status
        indexes[1] = toIndex(row - 1, col); // Up
        indexes[2] = toIndex(row + 1, col); // Down
        indexes[3] = toIndex(row, col + 1); // Right
        indexes[4] = toIndex(row, col - 1); // Left

        // Merge roots adjacent status with bitwise OR
        byte newStatus = status[indexes[0]];
        for (int i : indexes) {
            if (status[i] >= OPEN) { // Ignore CLOSED nodes (nullNode, etc.)
                newStatus = (byte) (newStatus | status[idsUF.find(i)]);
            }
        }

        // Creation of new tree from roots
        for (int i : indexes) {
            if (status[i] >= OPEN) {
                idsUF.union(i, indexes[0]);
            }
        }

        // Update only the new root because we created a new huge tree
        status[idsUF.find(indexes[0])] = newStatus;

        if (newStatus == PERCOLATION) { // TOP_CONN and BOTTOM_CONN merged
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
        return (status[idsUF.find(toIndex(row, col))] & TOP_CONN) == TOP_CONN;
    }

    public boolean percolates() {
        return percolates;
    }
}
