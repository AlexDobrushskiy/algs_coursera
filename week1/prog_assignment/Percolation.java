public class Percolation {
   
    int grid[][];
// create N-by-N grid, with all sites blocked 
    int size;
    
public Percolation(int N)
{
    size = N;
    grid = new int[N][N];
    for (int i=0;i<N;i++)
    for (int j=0;j<N;j++)
    // assume 0 means 'locked', 1 means 'opened'
    grid[i][j] = 0;
 }
 
public void open(int i, int j)         // open site (row i, column j) if it is not already
{
    if (i>=size || j>=size)
        throw new IndexOutOfBoundsException() ;
    grid[i][j] = 1;
}

public boolean isOpen(int i, int j)    // is site (row i, column j) open?
{

    return grid[i][j] == 1;
}
 // public boolean isFull(int i, int j)    // is site (row i, column j) full?
 // public boolean percolates()            // does the system percolate?
}