package com.company.tests;

import com.company.Stack;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestStack {

    private Stack<Integer> stack = new Stack<>();

    @Before
    public void setUp() {
        stack.push(2);
        stack.push(4);
        stack.push(3);
        stack.push(7);
        stack.push(8);
        stack.push(22);
        stack.push(36);
    }

    @Test
    public void testPop() {
        int element = stack.pop();
        assertEquals(36, element);
        assertEquals(6, stack.size());

        element = stack.pop();
        assertEquals(22, element);
        assertEquals(5, stack.size());

        element = stack.pop();
        assertEquals(8, element);
        assertEquals(4, stack.size());

        element = stack.pop();
        assertEquals(7, element);
        assertEquals(3, stack.size());

        element = stack.pop();
        assertEquals(3, element);
        assertEquals(2, stack.size());

        element = stack.pop();
        assertEquals(4, element);
        assertEquals(1, stack.size());

        element = stack.pop();
        assertEquals(2, element);
        assertEquals(0, stack.size());

        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void testPeek() {
        int element = stack.peek();
        assertEquals(36, element);

        element = 50;
        stack.push(element);
        assertEquals(element, (long) stack.peek());

        element = 55;
        stack.push(element);
        assertEquals(element, (long) stack.peek());

        element = 80;
        stack.push(element);
        assertEquals(element, (long) stack.peek());
    }
}
