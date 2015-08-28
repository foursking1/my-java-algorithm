package com.foursking.algorithms.data_structures.interfaces;

/**
 * Created by Administrator on 2015/8/26.
 */
public interface IStack<T> {

    /**
     * Push value on top of stack
     * @param value
     * @return
     */
    public boolean push(T value);

    /**
     * Pop the value from top of stack.
     * @return
     */
    public T pop();

    /**
     * Peek the value from the top of stack
     * @return
     */
    public T peek();

    /**
     * Remove value from stack
     * @param value
     * @return
     */
    public boolean remove(T value);

    /**
     * Clear the entire stack.
     */
    public void clear();


    /**
     * Does stack contain object
     * @param value
     * @return
     */
    public boolean contains(T value);

    /**
     * Size of the stack.
     * @return
     */
    public int size();

    /**
     * Validate the stack according to the invariants
     * @return
     */
    public boolean validate();

    /**
     * Get the Stack as a java compatible Queue
     * @return
     */
    public java.util.Queue<T> toLifoQueue();

    /**
     * Get this Stack as Java compatible Collection
     * @return
     */
    public java.util.Collection<T> toCollection();
}
