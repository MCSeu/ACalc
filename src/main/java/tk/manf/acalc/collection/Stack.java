package tk.manf.acalc.collection;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import tk.manf.acalc.api.Token;

/**
 * LiFo implementation using a LinkedList
 * @author Björn 'manf' Heinrichs
 */
public class Stack implements Iterable<Token> {
    private final Deque<Token> data;

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
    public Token remove() {
        return data.removeLast();
    }
    
    /**
     * Retrieves, but does not remove, the last inserted element
     * or returns {@code null} if this stack is empty.
     *
     * @return the last inserted element or {@code null} if this stack is empty
     */
    public Token top() {
        return data.peekLast();
    }
    
    public void add(Token t) {
        data.add(t);
    }
    
    public void clear() {
        data.clear();
    }

    @Override
    public Iterator<Token> iterator() {
        return data.iterator();
    }
}