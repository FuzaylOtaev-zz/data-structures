package com.company;

public class AVLTreeRecursive <T extends Comparable<T>> {

    public class Node {
        public int bf;
        public T value;
        public int height;
        public Node right, left;

        public Node(T value) {
            this.value = value;
        }
    }

    public Node root;
    private int nodeCount = 0;

    public int height() {
        return root.height;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node node, T value) {
        if (node == null) {
            return false;
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            return contains(node.left, value);
        }
        if (cmp > 0) {
            return contains(node.right, value);
        }

        return true;
    }

    public boolean insert(T value) {
        if (value == null)
            return false;

        if (!contains(value)) {
            root = insert(root, value);
            nodeCount++;
            return true;
        }
        return false;
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        update(node);
        return balance(node);
    }

    private void update(Node node) {
        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.bf = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        if (node.bf == -2) {
            if (node.left.bf <= 0) {
                return leftLeftCase(node);
            } else {
                return leftRightCase(node);
            }
        } else if (node.bf == 2) {
            if (node.right.bf >= 0) {
                return rightRightCase(node);
            } else {
                return rightLeftCase(node);
            }
        }

        return node;
    }

    private Node leftLeftCase(Node node) {
        return rightRotation(node);
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightRightCase(Node node) {
        return leftRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;
    }

    public boolean remove(T value) {
        if (value == null) {
            return false;
        }

        if (contains(root, value)) {
            root = remove(root, value);
            nodeCount--;
            return true;
        }

        return false;
    }

    private Node remove(Node node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = remove(node.left, value);
        } else if (cmp > 0) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T successorValue = findMax(node.left);
                    node.value = successorValue;

                    node.left = remove(node.left, successorValue);
                } else {
                    T successorValue = findMin(node.right);
                    node.value = successorValue;

                    node.right = remove(node.right, successorValue);
                }
            }
        }

        update(node);
        return balance(node);
    }

    private T findMin(Node node) {
        while (node.left != null)
            node = node.left;

        return node.value;
    }

    private T findMax(Node node) {
        while (node.right != null)
            node = node.right;

        return node.value;
    }
}