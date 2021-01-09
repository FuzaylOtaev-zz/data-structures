package com.company.tests;

import com.company.BinaryHeap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBinaryHeap {

    private BinaryHeap<Integer> heap = new BinaryHeap<>();

    @Before
    public void setUp() {
        heap.add(2);
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(8);
        heap.add(22);
        heap.add(36);
    }

    @Test
    public void testAdd() {
        heap.add(4);
        assertEquals(4, (long) heap.get(1));

        heap.add(0);
        assertEquals(0, (long) heap.get(0));

        heap.add(6);
        assertEquals(6, (long) heap.get(4));

        heap.add(1);
        assertEquals(1, (long) heap.get(1));

        heap.add(11);
        assertEquals(11, (long) heap.get(5));

        heap.add(40);
        assertEquals(40, (long) heap.get(12));

        heap.add(2);
        assertEquals(2, (long) heap.get(2));

        heap.add(1);
        assertEquals(1, (long) heap.get(2));
    }

    @Test
    public void testRemove() {
        heap.remove(5);
        assertEquals(36, (long) heap.get(3));
        assertEquals(6, (long) heap.size());

        heap.remove(2);
        assertEquals(22, (long) heap.get(2));
        assertEquals(5, (long) heap.size());

        heap.remove(36);
        assertEquals(8, (long) heap.get(3));
        assertEquals(4, (long) heap.size());

        heap.remove(3);
        assertEquals(8, (long) heap.get(1));
        assertEquals(3, (long) heap.size());
    }

    @Test
    public void testIsMinHeap() {
        boolean isMin = heap.isMinHeap(0);
        assertTrue(isMin);
    }
}
