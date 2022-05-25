package edapb.algorithms.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int OPEN = 1;
    private final int sideLength;
    private final int topNode;

    private final WeightedQuickUnionUF idsUF;
    private final byte[] status;
    private final int[] openedLastRowNodes;
    private int numOfOpenSites;

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
        openedLastRowNodes = new int[n + 1];
        for (int i = 1; i < openedLastRowNodes.length; ++i) {
            openedLastRowNodes[i] = -1;
        }
        numOfOpenSites = 0;

        // Spawn top virtual node.
        topNode = 0;
        status[topNode] = OPEN;
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
        status[indexes[0]] = OPEN;
        ++numOfOpenSites;

        // Check top, bottom, left, right for OPEN nodes to connect to.
        // Makes sure they are in range by checking for -1
        // Up
        indexes[2] = toIndex(row - 1, col);
        // Down
        indexes[1] = toIndex(row + 1, col);
        // Right
        indexes[3] = toIndex(row, col + 1);
        // Left
        indexes[4] = toIndex(row, col - 1);

        final int lastRow = (sideLength - 1) * sideLength;
        final byte inArray = 2;
        for (int i : indexes) {
            if (i != -1 && status[i] >= OPEN) {
                idsUF.union(i, indexes[0]);
                if (status[i] != inArray && i > lastRow) {
                    status[i] = inArray;
                    openedLastRowNodes[++openedLastRowNodes[0]] = i;
                }
            }
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
        return idsUF.find(topNode) == idsUF.find(toIndex(row, col));
    }

    public boolean percolates() {
        boolean percolates = false;
        for (int i = 1; openedLastRowNodes[i] != -1; ++i) {
            if (idsUF.find(topNode) == idsUF.find(openedLastRowNodes[i])) {
                percolates = true;
                break;
            }
        }

        return percolates;
    }
}
