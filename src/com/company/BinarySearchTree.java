package com.company;

public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;
    private Node root;

    public class Node {
        public T data;
        public Node left, right;
        public Node(Node left, Node right, T data) {
            this.data = data;
            this.right = right;
            this.left = left;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean add(T element) {
        if (contains(element))
            return false;

        root = add(root, element);
        nodeCount++;
        return true;
    }

    private Node add(Node node, T element) {
        if (node == null) {
            node = new Node(null, null, element);
        } else {
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }

        return node;

    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node node, T element) {
        if (node == null)
            return false;

        if (element.compareTo(node.data) > 0) {
            return contains(node.right, element);
        }
        if (element.compareTo(node.data) < 0) {
            return contains(node.left, element);
        }

        return true;
    }

    public boolean remove(T element) {
        if (contains(element)) {
            remove(root, element);
            nodeCount--;
            return true;
        }

        return false;
    }

    private Node remove(Node node, T element) {
        if (node == null)
            return null;

        int cmp = element.compareTo(node.data);
        if (cmp > 0) {
            node.right = remove(node.right, element);
        } else if (cmp < 0) {
            node.left = remove(node.left, element);
        } else {
            if (node.left == null) {
                Node rightChild = node.right;
                node.data = null;
                node = null;

                return rightChild;
            } else if (node.right == null) {
                Node leftChild = node.left;
                node.data = null;
                node = null;

                return leftChild;
            } else {
                Node tmp = findMax(node.left);
                node.data = tmp.data;
                node.left = remove(node.left, tmp.data);
            }
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    public Node find(T element) {
        return find(root, element);
    }

    private Node find(Node node, T element) {
        if (node.data == element)
            return node;

        if (node.data.compareTo(element) < 1) {
            return find(node.left, element);
        } else {
            return find(node.right, element);
        }

    }
}
