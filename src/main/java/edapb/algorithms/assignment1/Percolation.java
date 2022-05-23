package edapb.algorithms.assignment1;

public class Percolation {
    private final int closed = 0;
    private final int open = 1;

    private int[][] grid;
    public Percolation(final int n) {
        grid = new int[n][n];
    }

    public void open(int row, int col) {
    }
}
