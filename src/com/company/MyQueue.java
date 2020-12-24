package com.company;

import java.util.Iterator;

public class MyQueue<T> implements Iterable<T> {
    private DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public MyQueue() {

    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }
        return list.getFirst();
    }

    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }
        return list.removeFirst();
    }

    public void offer(T element) {
        list.addLast(element);
    }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
