package com.foursking.algorithms.data_structures.interfaces;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface IList<T> {

    public boolean add(T value);

    public boolean remove(T value);

    public void clear();

    public boolean contains(T value);

    public int size();

    public boolean validate();

    public java.util.List<T> toList();

    public java.util.Collection<T> toCollection();
}
