package com.company;

import java.util.*;

public class PQueue<T extends Comparable<T>> {

    private int heapSize = 0;
    private int heapCapacity = 0;
    private List<T> heap = null;
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public PQueue() {
        this(1);
    }

    public PQueue(int size) {
        heap = new ArrayList<>(size);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public void clear() {
        for (int i = 0; i < heapCapacity; i++) {
            heap.set(i, null);
        }
        heapSize = 0;
        map.clear();
    }

    public T peek() {
        if (isEmpty())
            return null;

        return heap.get(0);
    }


    public boolean contains(T element) {
        if (element == null)
            return false;

        return map.containsKey(element);
    }

    public void add(T element) {
        if (element == null)
            throw new IllegalArgumentException();

        if (heapSize < heapCapacity) {
            heap.set(heapSize, element);
        } else {
            heap.add(element);
            ++heapCapacity;
        }

        mapAdd(element, heapSize);
        swim(heapSize);
        ++heapSize;
    }

    private void swim(int k) {
        if (size() == 1)
            return;

        int parent = (k - 1) / 2;
        while (k > 0 && less(k, parent)) {
            swap(parent, k);

            k = parent;
            parent = (k - 1) / 2;
        }
    }

    private void sink(int k) {
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;

            int smallest = left;

            if (right< size() && less(right, left))
                smallest = right;

            if (left >= size() || less(k, smallest))
                break;

            swap(smallest, k);
            k = smallest;

        }
    }

    private void swap(int parent_index, int child_index) {
        T parent_value = heap.get(parent_index);
        T child_value = heap.get(child_index);

        heap.set(parent_index, child_value);
        heap.set(child_index, parent_value);

        mapSwap(parent_value, child_value, parent_index, child_index);
    }

    private boolean less(int parent, int child) {
        T parentNode = heap.get(parent);
        T childNode = heap.get(child);

        return parentNode.compareTo(childNode) <= 0;
    }

    private void mapAdd(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);
        } else {
            set.add(index);
        }
    }

    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index);
        if (set.size() == 0)
            map.remove(value);
    }

    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null)
            return set.last();

        return null;
    }

    private void mapSwap(T val1, T val2, int val1_index, int val2_index) {
        TreeSet<Integer> set1 = map.get(val1);
        TreeSet<Integer> set2 = map.get(val2);

        set1.remove(val1_index);
        set1.add(val2_index);

        set2.remove(val2_index);
        set2.add(val1_index);
    }


    public T get(int index) {
        if (index > size())
            throw new IndexOutOfBoundsException();

        return heap.get(index);
    }

    public boolean removeUsingHeap(T element) {
        for (int i = 0; i < heapSize; i++) {
            if (heap.get(i) == element) {
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    public boolean removeUsingMap(T element) {
        Integer index = mapGet(element);
        if (index != null)
            removeAt(index);

        return index != null;
    }

    private T removeAt(int i) {

        T removed_data = heap.get(i);
        --heapSize;
        swap(i, heapSize);
        heap.set(heapSize, null);
        mapRemove(removed_data, i);

        if (i == heapSize)
            return removed_data;

        sink(i);

        return removed_data;
    }
}
