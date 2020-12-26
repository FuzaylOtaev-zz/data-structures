package com.company.tests;

import com.company.ArrayQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayQueue {

    private ArrayQueue<Integer> queue = new ArrayQueue<>(100);

    @Before
    public void setUp() {
        queue.offer(2);
        queue.offer(4);
        queue.offer(3);
        queue.offer(7);
        queue.offer(8);
        queue.offer(22);
        queue.offer(36);
    }

    @Test
    public void testPeek() {
        assertEquals(2, (long) queue.pop());
        assertEquals(4, (long) queue.pop());
        assertEquals(3, (long) queue.pop());
        assertEquals(7, (long) queue.pop());
        assertEquals(8, (long) queue.pop());
        assertEquals(22, (long) queue.pop());
        assertEquals(36, (long) queue.pop());
    }

    @Test
    public void testOffer() {
        queue.offer(111);
        assertEquals(8, queue.size());

        queue.offer(22);
        assertEquals(9, queue.size());
    }
}
