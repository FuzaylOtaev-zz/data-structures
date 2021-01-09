package com.company;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private int heapSize = 0;
    private int heapCapacity = 0;
    private List<T> heap = null;

    public BinaryHeap() {
        this(1);
    }

    public BinaryHeap(int sz) {
        heap = new ArrayList<>(sz);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for (int i = 0; i < heapSize; i++)
            heap.set(i, null);
        heapSize = 0;
    }

    public int size() {
        return heapSize;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        return removeAt(0);
    }

    public T peek() {
        if (isEmpty())
            return null;

        return heap.get(0);
    }

    public boolean contains(T element) {
        for (int i = 0; i < size(); i++) {
            if (heap.get(i) == element) {
                return true;
            }
        }

        return false;
    }

    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (heapSize < heapCapacity) {
            heap.set(heapSize, element);
        } else {
            heap.add(element);
            ++heapCapacity;
        }

        swim(heapSize);
        ++heapSize;
    }

    private void swim(int k) {
        int parent = (k - 1) / 2;
        while (k > 0 && less(k, parent)) {
            swap(parent, k);
            k = parent;

            parent = (k - 1) / 2;
        }

    }

    private void swap(int parent_index, int child_index) {
        T parent_value = heap.get(parent_index);
        T child_value = heap.get(child_index);

        heap.set(child_index, parent_value);
        heap.set(parent_index, child_value);
    }

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        return node1.compareTo(node2) <= 0;
    }

    public boolean remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (heap.get(i).equals(element)) {
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    private T removeAt(int index) {
        if (isEmpty())
            return null;

        --heapSize;

        T removed_data = heap.get(index);
        swap(index, heapSize);

        heap.set(heapSize, null);

        if (index == heapSize)
            return removed_data;

        sink(index);

        return removed_data;
    }

    private void sink(int k) {
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left;

            if (right < size() && less(right, left))
                smallest = right;

            if (left >= size() || less(k, smallest))
                break;

            swap(smallest, k);
            k = smallest;
        }
    }

    public T get(int index) {
        return heap.get(index);
    }

    public boolean isMinHeap(int k) {
        if (k >= size())
            return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        if (left < heapSize && !less(k, left))
            return false;

        if (right < heapSize && !less(k, right))
            return false;

        return isMinHeap(left) && isMinHeap(left);
    }

    public String toString() {
        return heap.toString();
    }
}
