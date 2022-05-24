package edapb.algorithms.assignment1;

public class Percolation {
    private final int closed = 0;
    private final int open = 1;
    private final int width;
    private final int height;

    private int[][] grid;
    public Percolation(final int n) {
        grid = new int[n][n];
        width = n;
        height = n;
    }

    public void open(int row, int col) {
        grid[row][col] = open;
    }
}
