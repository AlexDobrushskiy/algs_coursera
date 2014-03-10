import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/10/14
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
//public class RandomizedQueue{}
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue
    private int first = 0;       // index of first element of queue
    private int last  = 0;       // index of next available slot

    public RandomizedQueue() {
        // cast needed since no generic array creation in Java
        q = (Item[]) new Object[2];
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return N == 0;
    }
    public int size()                        // return the number of items on the queue
    {
        return N;
    }
    // resize the underlying array
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }

    public void enqueue(Item item) {
        // double size of array if necessary and recopy to front of array
        if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        N++;
    }

    private void switchItems(int n, int m)
    {
        Item tmp = q[n];
        q[n] = q[m];
        q[m] = tmp;
    }

    public Item dequeue()                    // delete and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        switchItems(first, StdRandom.uniform(first, last+1));
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return item;
    }
    public Item sample()                     // return (but do not delete) a random item
    {
        return q[StdRandom.uniform(first, last+1)];
    }

   private class RandQueueIterator<Item> implements Iterator<Item>
   {
       private int current = first;
       public void remove()
       {}

       public boolean hasNext()
       {
           return current <= last;
       }
       public Item next()
       {
           if (!hasNext())
               throw new java.util.NoSuchElementException();
           Item item = (Item)q[current];
           current++;
           return item;

       }
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
       return new RandQueueIterator<Item>();
   }
   public static void main(String[] args)   // unit testing
   {
       RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
       for (int i=0;i<10;i++)
           r.enqueue(i);
       for (int i=0;i<10;i++)
           StdOut.println(r.dequeue());
   }
}
