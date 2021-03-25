package com.company.tests;

import com.company.AVLTreeRecursive;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAVLTree {

    @Test
    public void testLeftLeftCase() {
        AVLTreeRecursive<Integer> tree = new AVLTreeRecursive<>();
        tree.insert(9);
        tree.insert(7);
        tree.insert(5);

        assertEquals(7, (int) tree.root.value);
        assertEquals(5, (int) tree.root.left.value);
        assertEquals(9, (int) tree.root.right.value);
    }

    @Test
    public void testLeftRightCase() {
        AVLTreeRecursive<Integer> tree = new AVLTreeRecursive<>();
        tree.insert(8);
        tree.insert(5);
        tree.insert(7);

        assertEquals(7, (int) tree.root.value);
        assertEquals(5, (int) tree.root.left.value);
        assertEquals(8, (int) tree.root.right.value);
    }

    @Test
    public void testRightRightCase() {
        AVLTreeRecursive<Integer> tree = new AVLTreeRecursive<>();
        tree.insert(5);
        tree.insert(9);
        tree.insert(12);

        assertEquals(9, (int) tree.root.value);
        assertEquals(5, (int) tree.root.left.value);
        assertEquals(12, (int) tree.root.right.value);
    }

    @Test
    public void testRightLeftCase() {
        AVLTreeRecursive<Integer> tree = new AVLTreeRecursive<>();
        tree.insert(5);
        tree.insert(9);
        tree.insert(6);

        assertEquals(6, (int) tree.root.value);
        assertEquals(5, (int) tree.root.left.value);
        assertEquals(9, (int) tree.root.right.value);
    }

    @Test
    public void testRemove() {
        AVLTreeRecursive<Integer> tree = new AVLTreeRecursive<>();
        tree.insert(21);
        tree.insert(11);
        tree.insert(30);
        tree.insert(8);
        tree.insert(14);
        tree.insert(26);
        tree.insert(36);
        tree.insert(6);
        tree.insert(10);
        tree.insert(12);
        tree.insert(16);
        tree.insert(23);
        tree.insert(27);

        boolean isRemoved = tree.remove(12);
        assertTrue(isRemoved);
        assertNull(tree.root.left.right.left);

        isRemoved = tree.remove(14);
        assertTrue(isRemoved);
        assertEquals(16, (int) tree.root.left.right.value);

        isRemoved = tree.remove(21);
        assertTrue(isRemoved);
        assertEquals(23, (int) tree.root.value);
        assertNull(tree.root.right.left.left);

        isRemoved = tree.remove(23);
        assertTrue(isRemoved);
        assertEquals(26, (int) tree.root.value);
        assertEquals(27, (int) tree.root.right.left.value);
        assertNull(tree.root.right.left.right);

        isRemoved = tree.remove(11);
        assertTrue(isRemoved);
        assertEquals(10, (int) tree.root.left.value);

        isRemoved = tree.remove(10);
        assertTrue(isRemoved);
        assertEquals(8, (int) tree.root.left.value);
        assertEquals(6, (int) tree.root.left.left.value);
    }
}
