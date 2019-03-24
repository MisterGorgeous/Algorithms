import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation percolation;
    private int numberOfOpenSites[];
    private int n;
    private int trials;


    public PercolationStats(int n, int trials) {
        validate(n, trials);
        this.n = n;
        this.trials = trials;
        numberOfOpenSites = new int[trials];

        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            numberOfOpenSites[i] = percolation.numberOfOpenSites();
        }

    }// perform trials independent experiments on an n-by-n grid


    public double mean() {
        return StdStats.mean(numberOfOpenSites);
    }/// sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(numberOfOpenSites);
    }/// sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - (1.96 * Math.sqrt(stddev()) / Math.sqrt(trials));
    }/// low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + (1.96 * Math.sqrt(stddev()) / Math.sqrt(trials));
    }/// high endpoint of 95% confidence interval


    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);// input file
        int T = Integer.valueOf(args[1]);// input file

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
