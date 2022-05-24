package edapb.algorithms.assignment1;

public class PercolationStats {
    private double mean;
    private double stddev;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        double[]
        for (int t = 0; t < trials; ++t) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {

            }
        }

    }
}
