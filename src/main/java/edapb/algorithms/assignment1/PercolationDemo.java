package edapb.algorithms.assignment1;

public class PercolationDemo {
    public static void main(String[] args) {
        Percolation grid = new Percolation(20);
        System.out.println(grid.percolates());
    }
}
