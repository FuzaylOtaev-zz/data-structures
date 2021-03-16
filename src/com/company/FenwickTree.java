package com.company;

public class FenwickTree {
    private long[] tree;

    public FenwickTree(long[] values) {
        if (values == null) {
            throw new IllegalArgumentException("values can't be null.");
        }
        this.tree = values.clone();
        for (int i = 1; i < tree.length; i++) {
            int parent = i + lsb(i);
            if (parent < tree.length) {
                tree[parent] += tree[i];
            }
        }
    }

    private static int lsb(int i) {
        return i & -i;
    }

    public long prefixSum(int i) {
        long sum = 0L;
        while (i != 0) {
            sum += tree[i];
            i -= lsb(i);
        }
        return sum;
    }

    public long sum(int i, int j) {
        if (j < i) {
            throw new IllegalArgumentException("make sure j >= i");
        }

        return prefixSum(j) - prefixSum(i - 1);
    }

    public void add(int i, long k) {
        while (i != 0) {
            tree[i] += k;
            i = i + lsb(i);
            if (i > tree.length) {
                break;
            }
        }
    }

    public void set(int i, long k) {
        long value = tree[i];
        add(i, k - value);
    }

    public long get(int i) {
        return tree[i];
    }
}
