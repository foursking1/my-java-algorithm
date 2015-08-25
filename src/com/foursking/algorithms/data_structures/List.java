package com.foursking.algorithms.data_structures;

import java.util.Arrays;

import com.foursking.algorithms.data_structures.interfaces.IList;

/**
 * Created by Administrator on 2015/8/24.
 */
@SuppressWarnings("unchecked")
public interface List<T> extends IList<T> {

     /**
     * A dynamic array, growable array, resizable array, dynamic table, or array
     * list is a random access, variable-size list data structure that allows
     * elements to be added or removed.
     *
     * http://en.wikipedia.org/wiki/Dynamic_array
     *
     * @author Justin Wetherell <phishman3579@gmail.com>
     */

    public static class ArrayList<T> implements List<T> {

        private static final int MINIMUM_SIZE = 1024;

        private int size = 0;
        private T[] array = (T[]) new Object[MINIMUM_SIZE];

        @Override
        public boolean add(T value) { return add(size, value); }

        public boolean add(int index, T value) {
            if (size >= array.length)
                grow();
            if (index == size) {
                array[size++] = value;
            } else {
                System.arraycopy(array, index, array, index+1, size - index);
                array[index] = value;
            }
            return true;
        }

        @Override
        public boolean remove(T value) {
            for (int i=0; i < size; i++) {
                T obj = array[i];
                if (obj.equals(value)) {
                    if (remove(i) != null) return true;
                    return false;
                }
            }
            return false;
        }

        public T remove(int index) {
            if (index < 0 || index >= size) return null;

            T t = array[index];
            if (index != --size) {
                System.arraycopy(array, index+1, array, index, size-index);
            }

            array[size] = null;

            int shrinkSize = array.length >> 1;
            if (shrinkSize >= MINIMUM_SIZE && size < shrinkSize ) {
                shrink();
            }

            return t;
        }

        // Grow the array by 50%
        private void grow() {
            int growSize = size + (size << 1);
            array = Arrays.copyOf(array, growSize);
        }

        // Shrink by 50%
        private void shrink() {
            int shrinkSize = array.length >> 1;
            array = Arrays.copyOf(array, shrinkSize);
        }

        /**
         * set value at index
         *
         * @param index
         * @param value
         * @return previously at index
         */
        public T set(int index, T value) {
            if (index < 0 || index >= size ) return null;
            T t = array[index];
            array[index] = value;
            return t;
        }

        public T get(int index) {
            if (index < 0 || index >= size) return null;
            T t = array[index];
            return t;
        }

        @Override
        public void clear() {size = 0;}

        @Override
        public boolean contains(T value) {
            for ( int i = 0; i < size; i ++ ) {
                T obj = array[i];
                if (obj.equals(value)) return true;
            }
            return false;
        }

        @Override
        public int size() { return size;}

        @Override
        public boolean validate() {
            int localSize = 0;
            for (int i=0; i < array.length; i++) {
                T t = array[i];
                if (i < size) {
                    if ( t == null) return false;
                    localSize++;
                } else {
                    if ( t!= null) return false;
                }
            }
            return (localSize == size);
        }

        @Override
        public java.util.List<T> toList() {
            return (new JavaCompatibleArrayList<T>(this));
        }

        @Override
        public java.util.Collection<T> toCollection() {
            return (new JavaCompatibleArrayList<T>(this));
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i=0; i < size; i++) {
                builder.append(array[i]).append(", ");
            }
            return builder.toString();
        }

    }


    /**
     * Linked List (doubly link). A linked list is a data structure consisting
     * of a group of nodes which together represent a sequence.
     *
     * http://en.wikipedia.org/wiki/Linked_list
     *
     * @author Justin Wetherell <phishman3579@gmail.com>
     */

    public static class LinkedList<T> implements List<T> {

        private int size = 0;
        private Node<T> head = null;
        private Node<T> tail = null;

        @Override
        public boolean add(T value) { return add(new Node<T>(value)); }

        public boolean add(Node<T> node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                Node<T> prev = tail;
                prev.next = node;
                node.prev = prev;
                tail = node;
            }
            size ++;
            return true;
        }

        public boolean remove(T value) {
            // Find the node
            Node<T> node = head;
            while( node != null && ( !node.value.equals(value))) {
                node = node.next;
            }

            if (node == null) {
                return false;
            }

            // Update the tail, if needed
            if (node.equals(tail)) {
                tail = node.prev;
            }

            Node<T> prev = node.prev;
            Node<T> next = node.next;
            if (prev != null && next != null) {
                prev.next = next;
                next.prev = prev;
            } else if (prev != null && next == null) {
                prev.next = null;
            } else if (prev == null && next != null) {
                next.prev = null;
                head = next;
            } else {
                head = null;
            }
            size --;
            return true;
        }

        @Override
        public void clear() {
            head = null;
            size = 0;
        }

        @Override
        public boolean contains(T value) {
            Node<T> node = head;
            while ( node != null ) {
                if (node.value.equals(value))
                    return true;
                node = node.next;
            }
            return false;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean validate() {
            java.util.Set<T> keys = new java.util.HashSet<T>();
            Node<T> node = head;
            if (node!=null) {
                keys.add(node.value);
                if (node.prev!=null) return false;
                Node<T> child = node.next;
                while (child!=null) {
                    if(!validate(child, keys)) return false;
                    child = child.next;
                }
            }
            return (keys.size()==size);
        }

        public boolean validate(Node<T> node, java.util.Set<T> keys) {
            if (node.value==null) return false;
            keys.add(node.value);

            Node<T> child = node.next;
            if (child!=null) {
                if (!child.prev.equals(node)) return false;
            } else {
                if (!node.equals(tail)) return false;
            }
            return true;
        }

        @Override
        public java.util.List<T> toList() {
            return (new JavaCompatibleLinkedList<T>(this));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public java.util.Collection<T> toCollection() {
            return (new JavaCompatibleLinkedList<T>(this));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            Node<T> node = head;
            while (node != null) {
                builder.append(node.value).append(", ");
                node = node.next;
            }
            return builder.toString();
        }


        private static class Node<T> {

            private T value = null;
            private Node<T> prev = null;
            private Node<T> next = null;

            private Node() {}

            private Node(T value) { this.value = value;}

            @Override
            public String toString() {
                return "value=" + value + " previous=" + ((prev != null) ? prev.value : "NULL")
                        + " next=" + ((next != null) ? next.value : "NULL");
            }
        }

    }

    public static class JavaCompatibleArrayList<T> extends java.util.AbstractList<T> implements java.util.RandomAccess {

        private List.ArrayList<T> list = null;

        public JavaCompatibleArrayList(List.ArrayList<T> list) { this.list = list; }

        @Override
        public boolean add(T value) {return list.add(value); }

        @Override
        public boolean remove(Object value) { return list.remove((T) value); }

        @Override
        public boolean contains(Object value) { return list.contains((T) value); }

        @Override
        public int size() { return list.size; }

        @Override
        public void add(int index, T value) { list.add(index, value); }

        @Override
        public T remove(int index) { return list.remove(index); }

        @Override
        public T get(int index) {
            T t = list.get(index);
            if (t!=null) return t;
            throw new IndexOutOfBoundsException();
        }

        @Override
        public T set(int index, T value) {
            return list.set(index, value);
        }
    }

    public static class JavaCompatibleLinkedList<T> extends java.util.AbstractSequentialList<T> {

        private List.LinkedList<T> list = null;

        public JavaCompatibleLinkedList(List.LinkedList<T> list) { this.list = list;}

        @Override
        public boolean add(T value) {
            return list.add(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean remove(Object value) {
            return list.remove((T) value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(Object value) {
            return list.contains((T) value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {
            return list.size();
        }

        @Override
        public java.util.ListIterator<T> listIterator(int index) {
            return (new LinkedListListIterator<T>(list));
        }

        private static class LinkedListListIterator<T> implements java.util.ListIterator<T> {

            private int index = 0;

            private LinkedList<T> list = null;
            private LinkedList.Node<T> prev = null;
            private LinkedList.Node<T> next = null;
            private LinkedList.Node<T> last = null;

            private LinkedListListIterator(LinkedList<T> list) {
                this.list = list;
                this.next = list.head;
                if (this.next!=null) this.prev = next.prev;
            }

            @Override
            public void add(T value) {
                LinkedList.Node<T> node = new LinkedList.Node<T>(value);

                LinkedList.Node<T> n = this.next;

                if (this.prev!=null) this.prev.next = node;
                node.prev = prev;

                node.next = n;
                if (n!=null) n.prev = node;

                this.next = node;
                if (this.prev==null) list.head = node;
                list.size++;
            }

            @Override
            public void remove() {
                if (last==null) return;

                LinkedList.Node<T> p = last.prev;
                LinkedList.Node<T> n = last.next;
                if (p!=null) p.next = n;
                if (n!=null) n.prev = p;
                if (last.equals(list.head)) list.head = n;
                if (last.equals(list.tail)) list.tail = p;
                list.size --;
            }

            @Override
            public void set(T value) {
                if (last!=null) last.value = value;
            }

            @Override
            public boolean hasNext() { return (next!=null); }

            @Override
            public boolean hasPrevious() { return (prev!=null); }

            @Override
            public int nextIndex() { return index; }

            @Override
            public int previousIndex() { return index-1; }

            @Override
            public T next() {
                if (next == null) throw new java.util.NoSuchElementException();
                index++;
                last = next;
                prev = next;
                next = next.next;
                return last.value;
            }

            @Override
            public T previous() {
                if (prev == null) throw new java.util.NoSuchElementException();
                index--;
                last = prev;
                next = prev;
                prev = next.prev;

                return last.value;
            }
        }


    }

}
