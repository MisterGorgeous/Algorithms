package union;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double numberOfOpenSites[];
    private final int trials;


    public PercolationStats(int n, int trials) {
        validate(n, trials);
        this.trials = trials;
        numberOfOpenSites = new double[trials];
        double numberOfSites = n * n;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            numberOfOpenSites[i] = percolation.numberOfOpenSites() / numberOfSites;
        }

    }// perform trials independent experiments on an n-by-n grid


    public double mean() {
        return StdStats.mean(numberOfOpenSites);
    }/// sample mean of percolation threshold

    public double stddev() {

        return trials == 1 ? Double.NaN : StdStats.stddev(numberOfOpenSites);
    }/// sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }/// low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }/// high endpoint of 95% confidence interval


    public static void main(String[] args) {
        int n = new Integer(args[0]);// input file
        int T = new Integer(args[1]);// input file

        PercolationStats percolationStats = new PercolationStats(n, T);

        double mean = percolationStats.mean();
        double stddev = percolationStats.stddev();
        double confidenceLo = percolationStats.confidenceLo();
        double confidenceHi = percolationStats.confidenceHi();

        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = [" + confidenceLo + ", " + confidenceHi + "]");


    }// test client (described below)

    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
    }

}
