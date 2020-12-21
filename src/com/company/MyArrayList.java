package com.company;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class MyArrayList<T> implements Iterable<T> {

    private T[] array;
    private int length;
    private int capacity;

    public MyArrayList() {
        this(8);
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity " + capacity);
        }
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length != 0;
    }

    public T get(int index) {
        return array[index];
    }

    public void set(int index, T element) {
        array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < length; i++) {
            array[i] = null;
        }
        length = 0;
    }

    public void add(T element) {
        if (length + 1 >= capacity ) {
            capacity = capacity * 2;
            T[] newArray = (T[]) new Object[capacity];
            for (int i = 0; i < length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }

        array[length] = element;
        length += 1;
    }

    public T removeAt(int index) {
        if (index < 0 || index > length - 1) {
            throw new IndexOutOfBoundsException();
        }
        T[] newArray = (T[]) new Object[capacity];
        T element = array[index];
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (i == index)
                continue;

            newArray[count++] = array[i];
        }
        array = newArray;
        length -= 1;

        return element;
    }

    public boolean remove(T element) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(element)) {
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    public int indexOf(T obj) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(obj))
                return i;
        }

        return -1;
    }

    public boolean contains(T obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < length; i++) {
            if (i != 0)
                builder.append(", ");

            builder.append(array[i]);
        }
        builder.append("]");
        return builder.toString();
    }
}
