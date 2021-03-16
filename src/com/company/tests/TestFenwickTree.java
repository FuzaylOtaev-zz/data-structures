package com.company.tests;

import com.company.FenwickTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFenwickTree {

    private FenwickTree tree = null;


    @Before
    public void setUp() {
        long[] numbers = new long[] {0, 2, 4, 6, 9, 5, 7, 4, -6, -2, 6, 8, 1};
        tree = new FenwickTree(numbers);
    }

    @Test
    public void testPrefixSum() {
        assertEquals(tree.prefixSum(6), 33);
        assertEquals(tree.prefixSum(9), 29);
        assertEquals(tree.prefixSum(11), 43);
        assertEquals(tree.prefixSum(4), 21);
        assertEquals(tree.prefixSum(12), 44);
    }

    @Test
    public void testSum() {
        assertEquals(25, tree.sum(4, 7));
        assertEquals(37, tree.sum(3, 11));
        assertEquals(19, tree.sum(4, 8));
        assertEquals(12, tree.sum(1, 3));
        assertEquals(2, tree.sum(7, 10));
    }

    @Test
    public void testAdd() {
        tree.add(1, 2);
        assertEquals(4, tree.get(1));
        assertEquals(8, tree.get(2));
        assertEquals(23, tree.get(4));
        assertEquals(33, tree.get(8));

        tree.add(10, 5);
        assertEquals(9, tree.get(10));
        assertEquals(18, tree.get(12));
    }

    @Test
    public void testSet() {
        tree.set(5, 7);
        assertEquals(7, tree.get(5));
        assertEquals(14, tree.get(6));
        assertEquals(33, tree.get(8));

        tree.set(9, -4);
        assertEquals(-4, tree.get(9));
        assertEquals(2, tree.get(10));
    }
}
