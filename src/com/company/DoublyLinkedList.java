package com.company;

public class DoublyLinkedList<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public String toString() {
            return data.toString();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            head.prev = new Node<>(element, null, head);
            head = head.prev;
        }

        size += 1;
    }

    public void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            tail.next = new Node<>(element, tail, null);
            tail = tail.next;
        }

        size += 1;
    }

    public T get(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.data;
        }
        if (index == size() - 1) {
            return tail.data;
        }

        Node<T> trav = head;
        for (int i = 1; i < size(); i++) {
            trav = trav.next;
            if (i == index)
                return trav.data;
        }

        return null;
    }

    public T getFirst() {
        if (isEmpty())
            throw new RuntimeException("empty list");

        return head.data;
    }

    public T getLast() {
        if (isEmpty())
            throw new RuntimeException("empty list");

        return tail.data;
    }


    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        if (size() == 1) {
            return "[" + head.data + "]";
        }

        Node<T> trav = new Node<>(null, null, head);
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size(); i++) {
            trav = trav.next;
            if (i != 0)
                builder.append(", ");

            builder.append(trav.data);
        }
        builder.append("]");

        return builder.toString();
    }


    public T removeFirst() {
        if (isEmpty())
            throw new RuntimeException("empty list");

        T data = head.data;
        if (size() == 1) {
            head = tail = null;
            size = 0;
            return data;
        }

        head = head.next;
        head.prev = null;

        --size;

        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("empty list");
        }

        T data = tail.data;
        if (size() == 1) {
            head = tail = null;
            size = 0;
            return data;
        }

        tail = tail.prev;
        tail.next = null;

        --size;

        return data;
    }

    public boolean remove(T element) {
        if (isEmpty()) {
            throw new RuntimeException("empty list");
        }
        if (head.data.equals(element)) {
            removeFirst();
            return true;
        }
        if (tail.data.equals(element)) {
            removeLast();
            return true;
        }

        Node<T> trav = head;
        for (int i = 1; i < size() - 1; i++) {
            trav = trav.next;
            T data = trav.data;
            if (data.equals(element)) {
                trav.prev.next = trav.next;
                trav.next.prev = trav.prev;
                --size;
                return true;

            }
        }

        return false;
    }

    public boolean contains(T element) {
        if (size() == 0) {
            return false;
        }
        if (head.data == element) {
            return true;
        }

        Node<T> trav = head;
        for (int i = 1; i < size(); i++) {
            trav = trav.next;
            T data = trav.data;
            if (data.equals(element))
                return true;
        }

        return false;
    }

}
