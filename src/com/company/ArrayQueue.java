package com.company;

@SuppressWarnings("unchecked")
public class ArrayQueue<T> {

    private T array[];
    private int capacity;
    private int size = 0;

    public ArrayQueue() {

    }

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }
        return array[0];
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }

        T data = array[0];
        if (size() == 1) {
            --size;
            array[0] = null;
            return data;
        }

        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        --size;

        return data;
    }

    public void offer(T element) {
        if (size + 1 >= capacity) {
            throw new RuntimeException("empty queue");
        }

        array[size] = element;
        size += 1;
    }
}
