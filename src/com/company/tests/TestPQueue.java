package com.company.tests;

import com.company.PQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPQueue {

    private PQueue<Integer> queue = new PQueue<>();

    @Before
    public void setUp() {
        queue.add(2);
        queue.add(5);
        queue.add(3);
        queue.add(7);
        queue.add(8);
        queue.add(22);
        queue.add(36);
    }

    @Test
    public void testAdd() {
        queue.add(4);
        assertEquals(4, (long) queue.get(1));

        queue.add(0);
        assertEquals(0, (long) queue.get(0));

        queue.add(6);
        assertEquals(6, (long) queue.get(4));

        queue.add(1);
        assertEquals(1, (long) queue.get(1));

        queue.add(11);
        assertEquals(11, (long) queue.get(5));

        queue.add(40);
        assertEquals(40, (long) queue.get(12));

        queue.add(2);
        assertEquals(2, (long) queue.get(2));

        queue.add(1);
        assertEquals(1, (long) queue.get(2));
    }

    @Test
    public void testRemoveUsingHeap() {
        queue.removeUsingHeap(5);
        assertEquals(36, (long) queue.get(3));
        assertEquals(6, (long) queue.size());

        queue.removeUsingHeap(2);
        assertEquals(22, (long) queue.get(2));
        assertEquals(5, (long) queue.size());

        queue.removeUsingHeap(36);
        assertEquals(8, (long) queue.get(3));
        assertEquals(4, (long) queue.size());

        queue.removeUsingHeap(3);
        assertEquals(8, (long) queue.get(1));
        assertEquals(3, (long) queue.size());
    }

    @Test
    public void testRemoveUsingMap() {
        queue.removeUsingMap(5);
        assertEquals(36, (long) queue.get(3));
        assertEquals(6, (long) queue.size());

        queue.removeUsingMap(2);
        assertEquals(22, (long) queue.get(2));
        assertEquals(5, (long) queue.size());

        queue.removeUsingMap(36);
        assertEquals(8, (long) queue.get(3));
        assertEquals(4, (long) queue.size());

        queue.removeUsingMap(3);
        assertEquals(8, (long) queue.get(1));
        assertEquals(3, (long) queue.size());
    }
}
