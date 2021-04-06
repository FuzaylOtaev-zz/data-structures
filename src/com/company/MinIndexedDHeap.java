package com.company;

import java.util.NoSuchElementException;

public class MinIndexedDHeap<T extends Comparable<T>> {
    private int sz;
    private final int N;
    private final int D;
    private final int[] child, parent;
    public final int pm[];
    public final int im[];
    public final Object[] values;

    public MinIndexedDHeap(int degree, int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }

        this.D = Math.max(degree, 2);
        this.N = Math.max(D + 1, maxSize);

        this.im = new int[N];
        this.pm = new int[N];
        this.child = new int[N];
        this.parent = new int[N];
        this.values = new Object[N];

        for (int i = 0; i < N; i++) {
            parent[i] = (i - 1) / D;
            child[i] = (i * D) + 1;
            pm[i] = im[i] = -1;
        }
    }

    public boolean contains(int ki) {
        keyInBoundsOrThrow(ki);
        return pm[ki] != -1;
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void keyInBoundsOrThrow(int ki) {
        if (ki < 0 || ki >= N) {
            String message = "Key index out of bounds; received: " + ki;
            throw new IllegalArgumentException(message);
        }
    }

    private void valueNotNullOrThrow(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
    }

    public void insert(int ki, T value) {
        if (contains(ki)) {
            throw new IllegalArgumentException("index already exists; received: " + ki);
        }
        valueNotNullOrThrow(value);
        values[ki] = value;
        pm[ki] = sz;
        im[sz] = ki;
        swim(sz++);
    }

    @SuppressWarnings("unchecked")
    public T update(int ki, T value) {
        keyExistsAndValueNotNullOrThrow(ki, value);
        final int i = pm[ki];
        T oldValue = (T) values[ki];
        values[ki] = value;
        sink(i);
        swim(i);

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T delete(int ki) {
        keyInBoundsOrThrow(ki);
        final int i = pm[ki];
        swap(i, --sz);
        sink(i);
        swim(i);
        final T value = (T) values[ki];
        values[ki] = null;
        pm[ki] = -1;
        im[sz] = -1;

        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        isNotEmptyOrThrow();
        return (T) values[im[0]];
    }

    public T poll() {
        T value = peek();
        delete(im[0]);

        return value;
    }

    private void sink(int i) {
        for (int j = minChild(i); j != -1;) {
            swap(i, j);
            i = j;
            j = minChild(i);
        }
    }

    private int minChild(int i) {
        int index = -1;
        int from = child[i];
        int to = Math.min(sz, from + D);

        for (int j = from; j < to; j++) {
            if (less(j, i)) {
                index = i = j;
            }
        }

        return index;
    }

    private void swim(int i) {
        while (less(i, parent[i])) {
            swap(i, parent[i]);
            i = parent[i];
        }
    }

    @SuppressWarnings("unchecked")
    private boolean less(int i, int j) {
        return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
    }

    private void swap(int i, int j) {
        pm[im[j]] = i;
        pm[im[i]] = j;
        int tmp = im[i];
        im[i] = im[j];
        im[j] = tmp;
    }

    private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
        keyInBoundsOrThrow(ki);
        valueNotNullOrThrow(value);
    }

    private void isNotEmptyOrThrow() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
    }
}
