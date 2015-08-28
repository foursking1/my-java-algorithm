package com.foursking.algorithms.data_structures.interfaces;

/**
 * Created by Administrator on 2015/8/26.
 */
public interface IHeap<T> {

    /**
     * Add value to the heap
     * @param value
     * @return
     */
    public boolean add(T value);

    /**
     * Get the value of the head node from the heap.
     * @return
     */
    public T getHeadValue();

    /**
     * Remove the head node from the heap.
     * @return
     */
    public T removeHead();

    /**
     *
     * @param value
     * @return
     */
    public T remove(T value);
    /**
     * Clear the entire heap.
     */
    public void clear();

    /**
     *  Does the value exist in the heap. Warning this is a O(n) operation.
     * @param value
     * @return
     */
    public boolean contains(T value);

    /**
     * Get size of the heap.
     *
     * @return size of the heap.
     */
    public int size();

    /**
     * Validate the heap according to the invariants.
     *
     * @return True if the heap is valid.
     */
    public boolean validate();

    /**
     * Get this Heap as a Java compatible Collection
     *
     * @return Java compatible Collection
     */
    public java.util.Collection<T> toCollection();
}
