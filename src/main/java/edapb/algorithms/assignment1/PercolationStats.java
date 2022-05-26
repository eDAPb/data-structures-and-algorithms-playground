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

        // Do trials
        double[] thresholds = new double[trials];
        for (int t = 0; t < trials; ++t) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n + 1),
                            StdRandom.uniform(1, n + 1));
            }
            System.out.println("----------------");

            thresholds[t] = (double)perc.numberOfOpenSites() / (n * n);
        }

        // Calculate stats
        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        // 1.96 -> zScore for 95% confidence interval
        final double modifier = (1.96 * stddev) / Math.sqrt(trials);
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

    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }

        int n;
        int trials;
        try {
            n = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        } catch (NumberFormatException err) {
            System.out.println("Please pass integers to the program.");
            return;
        }

        PercolationStats percStats = new PercolationStats(n, trials);
        System.out.printf("%-24s= %f%n", "mean", percStats.mean());
        System.out.printf("%-24s= %f%n", "stddev", percStats.stddev());
        System.out.printf("%-24s= [%f, %f]%n", "95% confidence interval",
                percStats.confidenceLo(), percStats.confidenceHi());
    }
}
