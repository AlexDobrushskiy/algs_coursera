import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/5/14
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */

public class Deque<Item> implements Iterable<Item>{


    private class ListNode{
        public Item value;
        ListNode prev, next;
        public ListNode(Item val){
            value = val;
            prev = null;
            next = null;
        }

        public void insertAfter(Item value){
            ListNode new_item = new ListNode(value);
            if (next != null)
            {
                new_item.next = next;
                next.prev = new_item;
            }
            this.next = new_item;
            new_item.prev = this;
        }

        public void insertBefore(Item value){
            ListNode new_item = new ListNode(value);

            if (prev != null)
            {
                new_item.prev = prev;
                prev.next = new_item;
            }
            this.prev = new_item;
            new_item.next = this;
        }


    }

    private class DequeIterator implements Iterator<Item>
    {
        private ListNode current = first;
        public void remove() {
        /* not supported */
            throw new UnsupportedOperationException();
        }
        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            Item item = current.value;
            current = current.next;
            return item;
        }
    }

    private ListNode first, last;
    private int size; // number of elements

    public Deque()                           // construct an empty deque
    {
        size = 0;
        first = new ListNode(null);
        last = first;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return size == 0;
    }
    public int size()                        // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item)          // insert the item at the front
    {
        if (item == null)
            throw new NullPointerException();
        first.insertBefore(item);
        first = first.prev;
        if (size == 0)
            last = first;
        size++;
    }
    public void addLast(Item item)           // insert the item at the end
    {
        if (item == null)
            throw new NullPointerException();
        last.insertAfter(item);
        last = last.next;
        if (size == 0)
            first = last;
        size++;
    }
    public Item removeFirst()                // delete and return the item at the front
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item result = first.value;
        first = first.next;
        first.prev = null;
        size--;
        return result;
    }

    public Item removeLast()                 // delete and return the item at the end
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item result = last.value;
        last = last.prev;
        last.next = null;
        size--;
        return result;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }
    public static void main(String[] args)   // unit testing
    {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(3);
        d.addFirst(4); d.addFirst(6); d.addFirst(7);
        d.addLast(10);
        StdOut.println(d.removeLast());
    }
}
