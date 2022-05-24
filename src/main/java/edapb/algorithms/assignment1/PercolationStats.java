package edapb.algorithms.assignment1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean;
    private double stddev;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        double[] thresholds = new double[trials];
        for (int t = 0; t < trials; ++t) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n), StdRandom.uniform(1, n));
            }
            thresholds[t] = (double)perc.numberOfOpenSites() / n;
        }

        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        System.out.println(mean + "\n" + stddev);
    }
}
