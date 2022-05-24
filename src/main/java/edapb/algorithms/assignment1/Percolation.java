package edapb.algorithms.assignment1;

public class Percolation {
    private final int full = 0;
    private final int open = 1;
    private final int width;
    private final int height;

    private int[][] grid;
    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new int[n][n];
        width = n;
        height = n;
    }

    private void enforceRange(int row, int col) {
        if (row <= 0 || row > height || col <= 0 || col >= width) {
            throw new IllegalArgumentException();
        }
    }

    public void open(int row, int col) {
        enforceRange(row, col);
        grid[row][col] = open;
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col] == open;
    }


}
