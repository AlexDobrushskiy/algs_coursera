/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/5/14
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */

public class Deque<Item> implements Iterable<Item>{
    public Deque()                           // construct an empty deque
    {

    }
    public boolean isEmpty()                 // is the deque empty?
    public int size()                        // return the number of items on the deque
    public void addFirst(Item item)          // insert the item at the front
    public void addLast(Item item)           // insert the item at the end
    public Item removeFirst()                // delete and return the item at the front
    public Item removeLast()                 // delete and return the item at the end
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    public static void main(String[] args)   // unit testing
}
