/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/18/14
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Percolation {

    int grid[][];
    // create N-by-N grid, with all sites blocked
    int size;
    WeightedQuickUnionUF field;

    private int getID(int i, int j)
     // assume 'i' is row, 'j' is column, first ID is 0
    {
        return (i-1)*size + j;
    }


    public Percolation(int N)
    {
        size = N;
        grid = new int[N+1][N+1];
        field = new WeightedQuickUnionUF(size*size+2);
        // now lets union all open cells on top row with virtual cell #0
        for (int j=1; j<=size; j++)
//            if (isOpen(1,j))
                field.union(0,getID(1,j));

        // now lets union all open cells on bottom row with virtual cell #(size^2+1)
        for (int j=1; j<=size; j++)
//            if (isOpen(size,j))
                field.union(size*size+1,getID(size,j));

        for (int i=1;i<=N;i++)
            for (int j=1;j<=N;j++)
                // assume 0 means 'locked', 1 means 'opened'
                grid[i][j] = 0;
    }

    public void open(int i, int j)         // open site (row i, column j) if it is not already
    {
        if (i>size || j>size || i<1 || j<1)
            throw new IndexOutOfBoundsException() ;
        grid[i][j] = 1;
        if (i<size && isOpen(i+1,j))
            field.union(getID(i,j), getID(i+1,j));
        if (i>1 && isOpen(i-1,j))
            field.union(getID(i,j), getID(i-1,j));
        if (j<size && isOpen(i,j+1))
            field.union(getID(i,j), getID(i,j+1));
        if (j>1 && isOpen(i,j-1))
            field.union(getID(i,j), getID(i,j-1));
    }

    public boolean isOpen(int i, int j)    // is site (row i, column j) open?
    {
        if (i>size || j>size || i<1 || j<1)
            throw new IndexOutOfBoundsException() ;
        return grid[i][j] == 1;
    }

    public boolean isFull(int i, int j)    // is site (row i, column j) full?
    {
        return isOpen(i,j) && field.connected(getID(i,j), 0);
    }

    public boolean percolates()            // does the system percolate?
    {
        return field.connected(0, size*size+1);
    }
}
