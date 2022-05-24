package edapb.algorithms.assignment1;

public class PercolationDemo {
    public static void main(String[] args) {
        Percolation grid = new Percolation(20);
        for (int i = 1; i < 20; ++i) {
            grid.open(i, 10);
        }

        grid.open(2, 8);
        PercolationVisualizer.draw(grid, 20);

        System.out.println(grid.percolates());
    }
}
