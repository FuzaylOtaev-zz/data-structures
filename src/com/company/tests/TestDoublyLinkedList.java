package com.company.tests;

import com.company.DoublyLinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDoublyLinkedList {

    private DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    @Before
    public void setUp() {
        list.addLast(2);
        list.addLast(4);
        list.addLast(3);
        list.addLast(7);
        list.addLast(8);
        list.addLast(22);
        list.addLast(36);
    }

    @Test
    public void testGet() {
        assertEquals(2, (long) list.get(0));
        assertEquals(4, (long) list.get(1));
        assertEquals(3, (long) list.get(2));
        assertEquals(7, (long) list.get(3));
        assertEquals(8, (long) list.get(4));
        assertEquals(22, (long) list.get(5));
        assertEquals(36, (long) list.get(6));
    }

    @Test
    public void testAddFirst() {
        list.addFirst(100);
        assertEquals(100, (long) list.get(0));

        list.addFirst(12);
        assertEquals(12, (long) list.get(0));
    }

    @Test
    public void testAddLast() {
        list.addLast(23);
        assertEquals(23, (long) list.get(list.size() - 1));

        list.addLast(89);
        assertEquals(89, (long) list.get(list.size() - 1));
    }

    @Test
    public void testToString() {
        String to_string = "[2, 4, 3, 7, 8, 22, 36]";
        assertEquals(to_string, list.toString());
    }

    @Test
    public void testContains() {
        assertTrue(list.contains(2));
        assertTrue(list.contains(4));
        assertTrue(list.contains(3));
        assertTrue(list.contains(7));
        assertTrue(list.contains(8));
        assertTrue(list.contains(22));
        assertTrue(list.contains(36));

        assertFalse(list.contains(45));
        assertFalse(list.contains(100));
        assertFalse(list.contains(50));
    }

    @Test
    public void testRemoveFirst() {
        int element = list.removeFirst();
        assertEquals(2, element);
        assertFalse(list.contains(2));
        assertEquals(4, (long) list.getFirst());

        element = list.removeFirst();
        assertEquals(4, element);
        assertFalse(list.contains(4));
        assertEquals(3, (long) list.getFirst());

        element = list.removeFirst();
        assertEquals(3, element);
        assertFalse(list.contains(3));
        assertEquals(7, (long) list.getFirst());
    }

    @Test
    public void testRemoveLast() {
        int element = list.removeLast();
        assertEquals(36, element);
        assertFalse(list.contains(36));
        assertEquals(22, (long) list.getLast());

        element = list.removeLast();
        assertEquals(22, element);
        assertFalse(list.contains(22));
        assertEquals(8, (long) list.getLast());

        element = list.removeLast();
        assertEquals(8, element);
        assertFalse(list.contains(8));
        assertEquals(7, (long) list.getLast());
    }

    @Test
    public void testRemove() {
        boolean is_removed = list.remove(22);
        assertTrue(is_removed);
        assertFalse(list.contains(22));
        assertEquals("[2, 4, 3, 7, 8, 36]", list.toString());

        is_removed = list.remove(3);
        assertTrue(is_removed);
        assertFalse(list.contains(22));
        assertEquals("[2, 4, 7, 8, 36]", list.toString());

        is_removed = list.remove(2);
        assertTrue(is_removed);
        assertFalse(list.contains(2));
        assertEquals("[4, 7, 8, 36]", list.toString());

        is_removed = list.remove(8);
        assertTrue(is_removed);
        assertFalse(list.contains(8));
        assertEquals("[4, 7, 36]", list.toString());

        is_removed = list.remove(36);
        assertTrue(is_removed);
        assertFalse(list.contains(36));
        assertEquals("[4, 7]", list.toString());

        is_removed = list.remove(4);
        assertTrue(is_removed);
        assertFalse(list.contains(4));
        assertEquals("[7]", list.toString());

        is_removed = list.remove(7);
        assertTrue(is_removed);
        assertFalse(list.contains(7));
        assertEquals("[]", list.toString());

    }

    @Test
    public void testRemoveAt() {
        boolean is_removed = list.removeAt(2);
        assertTrue(is_removed);
        assertFalse(list.contains(3));
        assertEquals(6, list.size());
        assertEquals("[2, 4, 7, 8, 22, 36]", list.toString());

        is_removed = list.removeAt(4);
        assertTrue(is_removed);
        assertFalse(list.contains(22));
        assertEquals(5, list.size());
        assertEquals("[2, 4, 7, 8, 36]", list.toString());

        is_removed = list.removeAt(0);
        assertTrue(is_removed);
        assertFalse(list.contains(2));
        assertEquals(4, list.size());
        assertEquals("[4, 7, 8, 36]", list.toString());

        is_removed = list.removeAt(2);
        assertTrue(is_removed);
        assertFalse(list.contains(8));
        assertEquals(3, list.size());
        assertEquals("[4, 7, 36]", list.toString());

        is_removed = list.removeAt(2);
        assertTrue(is_removed);
        assertFalse(list.contains(36));
        assertEquals(2, list.size());
        assertEquals("[4, 7]", list.toString());

        is_removed = list.removeAt(0);
        assertTrue(is_removed);
        assertFalse(list.contains(4));
        assertEquals(1, list.size());
        assertEquals("[7]", list.toString());

        is_removed = list.removeAt(0);
        assertTrue(is_removed);
        assertFalse(list.contains(7));
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
    }

    @Test
    public void testSet() {
        boolean is_set = list.set(0, 100);
        assertTrue(is_set);
        assertEquals(100, (long) list.get(0));

        is_set = list.set(list.size() - 1, 150);
        assertTrue(is_set);
        assertEquals(150, (long) list.get(list.size() - 1));

        is_set = list.set(2, 500);
        assertTrue(is_set);
        assertEquals(500, (long) list.get(2));

        is_set = list.set(4, 820);
        assertTrue(is_set);
        assertEquals(820, (long) list.get(4));

        is_set = list.set(3, 555);
        assertTrue(is_set);
        assertEquals(555, (long) list.get(3));
    }

    @Test
    public void testClear() {
        list.clear();
        assertEquals(0, list.size());
    }
}
