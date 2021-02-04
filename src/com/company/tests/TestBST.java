package com.company.tests;

import com.company.BinarySearchTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBST {


    @Test
    public void testAdd() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(15);
        bst.add(8);
        bst.add(13);
        bst.add(6);
        bst.add(7);

        BinarySearchTree.Node node = bst.find(15);
        assertEquals(15, node.data);
        assertEquals(8, node.left.data);
        assertEquals(13, node.left.right.data);
        assertEquals(6, node.left.left.data);
        assertEquals(7, node.left.left.right.data);

        bst.add(20);
        bst.add(17);
        bst.add(19);
        bst.add(16);
        bst.add(25);
        bst.add(29);
        bst.add(24);

        node = bst.find(15);
        assertEquals(15, node.data);
        assertEquals(20, node.right.data);
        assertEquals(17, node.right.left.data);
        assertEquals(19, node.right.left.right.data);
        assertEquals(16, node.right.left.left.data);
        assertEquals(25, node.right.right.data);
        assertEquals(29, node.right.right.right.data);
        assertEquals(24, node.right.right.left.data);
    }

    @Test
    public void testContains() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(15);
        bst.add(20);
        bst.add(17);
        bst.add(16);
        bst.add(19);
        bst.add(25);
        bst.add(24);
        bst.add(29);

        assertTrue(bst.contains(15));
        assertTrue(bst.contains(20));
        assertTrue(bst.contains(17));
        assertTrue(bst.contains(16));
        assertTrue(bst.contains(19));
        assertTrue(bst.contains(25));
        assertTrue(bst.contains(24));
        assertTrue(bst.contains(29));

        assertFalse(bst.contains(100));
        assertFalse(bst.contains(200));
        assertFalse(bst.contains(300));
        assertFalse(bst.contains(400));
    }

    @Test
    public void testRemove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(15);
        bst.add(9);
        bst.add(7);
        bst.add(12);
        bst.add(4);
        bst.add(8);
        bst.add(6);
        bst.add(22);
        bst.add(20);
        bst.add(24);
        bst.add(19);
        bst.add(21);
        bst.add(24);
        bst.add(25);
        bst.add(26);

        boolean is_removed = bst.remove(25);
        assertTrue(is_removed);
        BinarySearchTree.Node node = bst.find(15);
        assertEquals(26, node.right.right.right.data);

        is_removed = bst.remove(4);
        assertTrue(is_removed);
        assertEquals(6, node.left.left.left.data);

        is_removed = bst.remove(20);
        assertTrue(is_removed);
        assertEquals(19, node.right.left.data);

        is_removed = bst.remove(7);
        assertTrue(is_removed);
        assertEquals(6, node.left.left.data);

        is_removed = bst.remove(15);
        assertTrue(is_removed);
        assertEquals(12, node.data);
    }

}
