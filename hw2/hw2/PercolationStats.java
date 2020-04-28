package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private Percolation p;
    private double[] fraction;

    public PercolationStats(int N, int T, PercolationFactory pf) { // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T < 0) {
            throw new IllegalArgumentException();
        }
        fraction = new double[T];
        p = pf.make(N);
        for (int i = 0; i < T; i += 1) {
            int randomRow = StdRandom.uniform(N);
            int randomCol = StdRandom.uniform(N);
            if (p.isOpen(randomRow, randomCol)) {
                i -= 1;
            } else {
                p.open(randomRow, randomCol);
                fraction[i] = (double) p.numberOfOpenSites() / (N * N);
            }
        }
    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(fraction);
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(fraction);
    }

    public double confidenceLow() { // low endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(fraction.length);
    }

    public double confidenceHigh() { // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(fraction.length);
    }

    public static void main(String[] args) {
        Stopwatch watch = new Stopwatch();
        PercolationFactory pf = new PercolationFactory();
        PercolationStats test = new PercolationStats(20, 204, pf);
        System.out.println(test.mean()); // 這邊不太懂有沒有算對 照理來說機率不該只有四分之一而已
        System.out.println(test.stddev());
        System.out.println(test.confidenceLow());
        System.out.println(test.confidenceHigh());
        System.out.println("Time Elapsed: " + watch.elapsedTime()); // Prints: Time Elapsed: 2501
    }
}
