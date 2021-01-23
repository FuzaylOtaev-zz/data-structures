package com.company.tests;

import com.company.UnionFind;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUnionFind {

    private UnionFind unionFind = new UnionFind(10);

    @Test
    public void testUnifyAndFind() {
        unionFind.unify(0, 1);
        assertEquals(1, unionFind.find(0));
        assertEquals(1, unionFind.find(1));

        unionFind.unify(2, 3);
        assertEquals(3, unionFind.find(2));
        assertEquals(3, unionFind.find(3));

        unionFind.unify(4, 5);
        assertEquals(5, unionFind.find(4));
        assertEquals(5, unionFind.find(5));

        unionFind.unify(6, 7);
        assertEquals(7, unionFind.find(6));
        assertEquals(7, unionFind.find(7));

        unionFind.unify(8, 9);
        assertEquals(9, unionFind.find(8));
        assertEquals(9, unionFind.find(9));

        unionFind.unify(0, 9);
        assertEquals(9, unionFind.find(0));
        assertEquals(9, unionFind.find(1));
        assertEquals(9, unionFind.find(8));
        assertEquals(9, unionFind.find(9));

        unionFind.unify(3, 4);
        assertEquals(5, unionFind.find(2));
        assertEquals(5, unionFind.find(3));
        assertEquals(5, unionFind.find(4));
        assertEquals(5, unionFind.find(5));

        unionFind.unify(1, 7);
        assertEquals(9, unionFind.find(0));
        assertEquals(9, unionFind.find(1));
        assertEquals(9, unionFind.find(6));
        assertEquals(9, unionFind.find(7));
        assertEquals(9, unionFind.find(8));
        assertEquals(9, unionFind.find(9));

        unionFind.unify(4, 6);
        assertEquals(9, unionFind.find(0));
        assertEquals(9, unionFind.find(1));
        assertEquals(9, unionFind.find(2));
        assertEquals(9, unionFind.find(3));
        assertEquals(9, unionFind.find(4));
        assertEquals(9, unionFind.find(5));
        assertEquals(9, unionFind.find(6));
        assertEquals(9, unionFind.find(7));
        assertEquals(9, unionFind.find(8));
        assertEquals(9, unionFind.find(9));
    }

    @Test
    public void testConnected() {
        assertFalse(unionFind.connected(0, 1));
        assertFalse(unionFind.connected(0, 4));
        assertFalse(unionFind.connected(8, 6));
        assertFalse(unionFind.connected(2, 5));
        assertFalse(unionFind.connected(4, 7));

        unionFind.unify(4, 8);
        assertTrue(unionFind.connected(4, 8));

        unionFind.unify(5, 9);
        assertTrue(unionFind.connected(5, 9));

        unionFind.unify(8, 9);
        assertTrue(unionFind.connected(8, 9));
        assertTrue(unionFind.connected(5, 4));
        assertTrue(unionFind.connected(9, 5));
        assertTrue(unionFind.connected(5, 8));
        assertTrue(unionFind.connected(4, 9));

        unionFind.unify(9, 0);
        assertTrue(unionFind.connected(0, 9));
        assertTrue(unionFind.connected(0, 8));
        assertTrue(unionFind.connected(0, 5));
        assertTrue(unionFind.connected(0, 4));

        unionFind.unify(2, 3);
        assertTrue(unionFind.connected(2, 3));

        unionFind.unify(3, 8);
        assertTrue(unionFind.connected(2, 8));
        assertTrue(unionFind.connected(2, 0));
        assertTrue(unionFind.connected(2, 4));
        assertTrue(unionFind.connected(2, 5));
        assertTrue(unionFind.connected(3, 8));
        assertTrue(unionFind.connected(3, 0));
        assertTrue(unionFind.connected(3, 4));
        assertTrue(unionFind.connected(3, 5));
    }

    @Test
    public void testComponentSize() {
        unionFind.unify(0, 1);
        assertEquals(2, unionFind.componentSize(0));
        assertEquals(2, unionFind.componentSize(1));

        unionFind.unify(2, 3);
        assertEquals(2, unionFind.componentSize(2));
        assertEquals(2, unionFind.componentSize(3));

        unionFind.unify(4, 5);
        assertEquals(2, unionFind.componentSize(4));
        assertEquals(2, unionFind.componentSize(5));

        unionFind.unify(0, 3);
        assertEquals(4, unionFind.componentSize(0));
        assertEquals(4, unionFind.componentSize(1));
        assertEquals(4, unionFind.componentSize(2));
        assertEquals(4, unionFind.componentSize(3));

        unionFind.unify(2, 5);
        assertEquals(6, unionFind.componentSize(0));
        assertEquals(6, unionFind.componentSize(1));
        assertEquals(6, unionFind.componentSize(2));
        assertEquals(6, unionFind.componentSize(3));
        assertEquals(6, unionFind.componentSize(4));
        assertEquals(6, unionFind.componentSize(5));

        unionFind.unify(5, 9);
        assertEquals(7, unionFind.componentSize(0));
        assertEquals(7, unionFind.componentSize(1));
        assertEquals(7, unionFind.componentSize(2));
        assertEquals(7, unionFind.componentSize(3));
        assertEquals(7, unionFind.componentSize(4));
        assertEquals(7, unionFind.componentSize(5));
        assertEquals(7, unionFind.componentSize(9));

        unionFind.unify(3, 8);
        assertEquals(8, unionFind.componentSize(0));
        assertEquals(8, unionFind.componentSize(1));
        assertEquals(8, unionFind.componentSize(2));
        assertEquals(8, unionFind.componentSize(3));
        assertEquals(8, unionFind.componentSize(4));
        assertEquals(8, unionFind.componentSize(5));
        assertEquals(8, unionFind.componentSize(9));
        assertEquals(8, unionFind.componentSize(8));

    }
}
