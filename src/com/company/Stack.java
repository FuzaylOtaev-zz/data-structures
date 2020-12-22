package com.company;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public Stack() {

    }

    public Stack(T firstElement) {
        push(firstElement);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(T element) {
        list.addLast(element);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return list.removeLast();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return list.getLast();
    }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
