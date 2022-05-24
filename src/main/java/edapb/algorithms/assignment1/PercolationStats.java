package edapb.algorithms.assignment1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        double[] thresholds = new double[trials];
        for (int t = 0; t < trials; ++t) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }

            thresholds[t] = (double)perc.numberOfOpenSites() / (n * n);
        }

        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        final double zScore = 1.96; // 95% confidence interval
        final double modifier = (zScore * stddev) / Math.sqrt(trials);
        confidenceHi = mean + modifier;
        confidenceLo = mean - modifier;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }
}
