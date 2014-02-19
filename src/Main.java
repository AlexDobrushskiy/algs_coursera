public class Main {

    public static void main(String[] args) {
	// write your code here
//        int N = StdIn.readInt();
//        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
//        while (!StdIn.isEmpty()) {
//            int p, q;
//            try
//            {
//                p = StdIn.readInt();
//                q = StdIn.readInt();
//            }
//            catch (Throwable ex)
//            {
//                break;
//            }
//            if (uf.connected(p, q)) continue;
//            uf.union(p, q);
//            StdOut.println(p + " " + q);
//        }
//        StdOut.println(uf.count() + " components");

        Percolation p = new Percolation(10);
        p.open(1,1);
        if (p.isOpen(1,1))
            StdOut.println("1,1 opened");
        p.open(1,2);
        if (!p.isOpen(1,2))
            StdOut.println("1,2 not opened");

    }
}
