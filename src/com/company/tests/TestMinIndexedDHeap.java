package com.company.tests;

import com.company.MinIndexedDHeap;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMinIndexedDHeap {

    @Test
    public void testInsert() {
        MinIndexedDHeap<Integer> heap = new MinIndexedDHeap<>(2, 20);
        heap.insert(3, 8);
        heap.insert(4, 5);
        heap.insert(7, 6);
        heap.insert(2, 9);
        heap.insert(1, 4);
        heap.insert(0, 7);
        heap.insert(5, 2);
        heap.insert(6, 1);
        heap.insert(8, 10);

        assertEquals(8, heap.values[3]);
        assertEquals(5, heap.values[4]);
        assertEquals(6, heap.values[7]);
        assertEquals(9, heap.values[2]);
        assertEquals(4, heap.values[1]);
        assertEquals(7, heap.values[0]);
        assertEquals(2, heap.values[5]);
        assertEquals(1, heap.values[6]);
        assertEquals(10, heap.values[8]);

        assertEquals(0, heap.pm[6]);
        assertEquals(1, heap.pm[5]);
        assertEquals(2, heap.pm[1]);
        assertEquals(3, heap.pm[4]);
        assertEquals(4, heap.pm[3]);
        assertEquals(5, heap.pm[0]);
        assertEquals(6, heap.pm[7]);
        assertEquals(7, heap.pm[2]);
        assertEquals(8, heap.pm[8]);

        assertEquals(3, heap.im[4]);
        assertEquals(4, heap.im[3]);
        assertEquals(7, heap.im[6]);
        assertEquals(2, heap.im[7]);
        assertEquals(1, heap.im[2]);
        assertEquals(0, heap.im[5]);
        assertEquals(5, heap.im[1]);
        assertEquals(6, heap.im[0]);
        assertEquals(8, heap.im[8]);
    }

    @Test
    public void testUpdate() {
        MinIndexedDHeap<Integer> heap = new MinIndexedDHeap<>(2, 20);
        heap.insert(3, 8);
        heap.insert(4, 5);
        heap.insert(7, 6);
        heap.insert(2, 9);
        heap.insert(1, 4);
        heap.insert(0, 7);
        heap.insert(5, 2);
        heap.insert(6, 1);
        heap.insert(8, 10);

        heap.update(5, 15);
        assertEquals(1, heap.pm[4]);
        assertEquals(4, heap.im[1]);
        assertEquals(3, heap.pm[2]);
        assertEquals(2, heap.im[3]);
        assertEquals(7, heap.pm[5]);
        assertEquals(5, heap.im[7]);

        heap.update(7, -7);
        assertEquals(0, heap.pm[7]);
        assertEquals(7, heap.im[0]);
        assertEquals(2, heap.pm[6]);
        assertEquals(6, heap.im[2]);
        assertEquals(6, heap.pm[1]);
        assertEquals(1, heap.im[6]);
    }

    @Test
    public void testDelete() {
        MinIndexedDHeap<Integer> heap = new MinIndexedDHeap<>(2, 20);
        heap.insert(3, 8);
        heap.insert(4, 5);
        heap.insert(7, 6);
        heap.insert(2, 9);
        heap.insert(1, 4);
        heap.insert(0, 7);
        heap.insert(5, 2);
        heap.insert(6, 1);
        heap.insert(8, 10);

        heap.delete(1);
        assertEquals(2, heap.pm[7]);
        assertEquals(7, heap.im[2]);
        assertEquals(6, heap.pm[8]);
        assertEquals( 8, heap.im[6]);

        heap.delete(4);
        assertEquals(3, heap.pm[2]);
        assertEquals(2, heap.im[3]);
        assertEquals(-1, heap.pm[4]);
        assertEquals(-1, heap.im[7]);

        heap.delete(6);
        assertEquals(0, heap.pm[5]);
        assertEquals(5, heap.im[0]);
        assertEquals(1, heap.pm[3]);
        assertEquals(3, heap.im[1]);
    }

    @Test
    public void testPeek() {
        MinIndexedDHeap<Integer> heap = new MinIndexedDHeap<>(2, 20);
        heap.insert(3, 8);
        heap.insert(4, 5);
        heap.insert(7, 6);

        assertEquals(5, (long) heap.peek());

        heap.insert(5, 1);
        assertEquals(1, (long) heap.peek());
    }

    @Test
    public void testPoll() {
        MinIndexedDHeap<Integer> heap = new MinIndexedDHeap<>(2, 20);
        heap.insert(3, 8);
        heap.insert(4, 5);
        heap.insert(7, 6);

        assertEquals(5, (long) heap.poll());

        heap.insert(5, 1);
        assertEquals(1, (long) heap.poll());
    }

}
