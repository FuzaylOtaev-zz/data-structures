package com.company.tests;

import com.company.MyArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMyArrayList {

    private MyArrayList<Integer> list = new MyArrayList<>();

    @Before
    public void setUp() {
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(7);
        list.add(8);
        list.add(22);
        list.add(36);
    }

    @Test
    public void testSize() {
        assertEquals( 7, list.size());

        list.add(45);
        list.add(22);
        assertEquals(9, list.size());

        list.add(48);
        list.add(45);
        list.add(33);
        assertEquals(12, list.size());

        list.removeAt(4);
        list.removeAt(5);
        assertEquals(10, list.size());

        list.removeAt(0);
        list.removeAt(1);
        assertEquals(8, list.size());

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

    @After
    public void tearDown() {
        list = null;
    }
}
