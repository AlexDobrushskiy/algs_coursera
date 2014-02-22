/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/20/14
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class PercolationStats {
    private Percolation p;
    private double results[];
    private int perc_size;

    private void openRandomCell()
    {
        int i,j;
        do {
            i = StdRandom.uniform(1, perc_size+1);
            j = StdRandom.uniform(1, perc_size+1);
        }
        while (p.isOpen(i, j));
        p.open(i,j);
    }

    private int performExperiment()
    {
        int count = 0;
        do {
            openRandomCell();
            count++;
        }
        while (!p.percolates());
        return count;
    }

    public PercolationStats(int N, int T)
    // perform T independent computational experiments on an N-by-N grid
    {
        results = new double[T];
        perc_size = N;
        for (int num=0;num<T;++num)
        {
            p = new Percolation(N);
            results[num] = performExperiment()*1./(N*N);
        }
    }

    public double mean()
    // sample mean of percolation threshold
    {
        return StdStats.mean(results);
    }

    public double stddev()
    // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(results);
    }

    public double confidenceLo()
    // returns lower bound of the 95% confidence interval
    {
        return mean() - 1.96*stddev()/Math.sqrt(results.length);
    }

    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return mean() + 1.96*stddev()/Math.sqrt(results.length);
    }

    public static void main(String[] args)   // test client, described below
    {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats a = new PercolationStats(N,T);
        StdOut.print("mean                    = ");
        StdOut.println(a.mean());
        StdOut.print("stddev                  = ");
        StdOut.println(a.stddev());
        StdOut.print("95% confidence interval = ");
        StdOut.print(a.confidenceLo());
        StdOut.print(", ");
        StdOut.print(a.confidenceHi());
    }
}