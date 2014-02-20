/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/20/14
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class PercolationStats {
    Percolation p;
    double results[];

    private void openRandomCell()
    {
        int i,j;
        do {
            i = StdRandom.uniform(1, p.size+1);
            j = StdRandom.uniform(1, p.size+1);
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

        for (int num=0;num<T;++num)
        {
            p = new Percolation(N);
            results[num] = performExperiment()*1./(N*N);
            StdOut.println(results[num]*1./(N*N));
        }
    }

    public double mean()
    // sample mean of percolation threshold
    {

        return 0;
    }

    public double stddev()
    // sample standard deviation of percolation threshold
    {
        return 0;
    }

    public double confidenceLo()
    // returns lower bound of the 95% confidence interval
    {
        return 0;
    }

    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return 0;
    }

    public static void main(String[] args)   // test client, described below
    {
        PercolationStats a = new PercolationStats(20,10);
    }
}