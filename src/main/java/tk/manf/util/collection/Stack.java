package tk.manf.util.collection;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * LiFo implementation using a LinkedList
 * @author Björn 'manf' Heinrichs
 */
public class Stack<V> implements Iterable<V> {
    private final Deque<V> data;

    public Stack() {
        this.data = new LinkedList<>();
    }
 
    /**
     * Retrieves and removes the last element of this stack. This method 
     * throws an exception if this stack is empty.
     *
     * @return the tail of this deque
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public V remove() {
        return data.removeLast();
    }
    
    /**
     * Retrieves, but does not remove, the last inserted element
     * or returns {@code null} if this stack is empty.
     *
     * @return the last inserted element or {@code null} if this stack is empty
     */
    public V top() {
        return data.peekLast();
    }
    
    public int size() {
        return data.size();
    }
    
    public void add(V t) {
        data.add(t);
    }
    
    public void clear() {
        data.clear();
    }

    @Override
    public Iterator<V> iterator() {
        return data.iterator();
    }
}