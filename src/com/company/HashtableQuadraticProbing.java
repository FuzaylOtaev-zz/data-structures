package com.company;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class HashtableQuadraticProbing<K, V> {

    private double loadFactor;
    private int capacity, threshold, modificationsCount;

    private int usedBuckets, keyCount = 0;

    private K[] keyTable;
    private V[] valueTable;

    private boolean containsFlag = false;
    private final K TOMBSTONE = (K) (new Object());

    private static final int DEFAULT_CAPACITY = 8;
    private static final double DEFAULT_LOAD_FACTOR = 0.45;

    public HashtableQuadraticProbing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashtableQuadraticProbing(int capacity, double loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("illegal capacity");
        }
        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("illegal loadFactor");
        }
        this.loadFactor = loadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, next2Power(capacity));
        threshold = (int) (this.capacity * this.loadFactor);

        keyTable = (K[]) new Object[this.capacity];
        valueTable = (V[]) new Object[this.capacity];
    }

    private static int next2Power(int n) {
        return Integer.highestOneBit(n) << 1;
    }

    private static int P(int x) {
        return ((x * x) + x) / 2;
    }

    private int normalizedIndex(int keyHash) {
        return (keyHash & 0X7FFFFFFF) % capacity;
    }

    public V put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("null key");

        if (usedBuckets >= threshold) {
            resizeTable();
        }

        final int hash = normalizedIndex(key.hashCode());
        int i = hash, j = -1, x = 1;

        do {
            if (keyTable[i] == TOMBSTONE) {
                if (j == -1) {
                    j = i;
                }
            } else if (keyTable[i] != null) {
                if (keyTable[i].equals(key)) {
                    V oldValue = valueTable[i];
                    if (j == -1) {
                        valueTable[i] = value;
                    } else {
                        keyTable[i] = TOMBSTONE;
                        valueTable[i] = null;
                        keyTable[j] = key;
                        valueTable[j] = value;
                    }
                    modificationsCount++;
                    return oldValue;
                }
            } else {
                if (j == -1) {
                    keyTable[i] = key;
                    valueTable[i] = value;
                    usedBuckets++;
                } else {
                    keyTable[j] = key;
                    valueTable[j] = value;
                }
                keyCount++;
                modificationsCount++;
                return null;
            }
            i = normalizedIndex(hash + P(x++));
        } while (true);
    }

    public V get(K key) {
        if (key == null) {
            throw  new IllegalArgumentException("null");
        }
        final int hash = normalizedIndex(key.hashCode());
        int i = hash, j = -1, x = 1;
        do {
            if (keyTable[i] == TOMBSTONE) {
                if (j == -1) {
                    j = i;
                }
            } else if (keyTable[i] != null) {
                if (keyTable[i].equals(key)) {
                    containsFlag = true;
                    if (j != -1) {
                        keyTable[j] = keyTable[i];
                        valueTable[j] = valueTable[i];
                        keyTable[i] = TOMBSTONE;
                        valueTable[i] = null;
                    }
                    return valueTable[i];
                }
            } else {
                containsFlag = false;
                return null;
            }
            i = normalizedIndex(hash + P(x++));
        } while (true);
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    private boolean hasKey(K key) {
        get(key);
        return containsFlag;
    }

    public V remove(K key) {
        if (key == null) {
            throw  new IllegalArgumentException("null");
        }
        int hash = normalizedIndex(key.hashCode());
        int i = hash, x = 1;
        do {
            if (keyTable[i] == TOMBSTONE)
                continue;

            if (keyTable[i] == null)
                return null;

            if (keyTable[i].equals(key)) {
                keyCount--;
                modificationsCount++;
                V oldValue = valueTable[i];
                keyTable[i] = TOMBSTONE;
                valueTable[i] = null;
                return oldValue;
            }

            i = normalizedIndex(hash + P(x++));
        } while (true);
    }

    public int size() {
        return keyCount;
    }

    public List<K> keys() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < keyTable.length; i++) {
            if (keyTable[i] != null && keyTable[i] != TOMBSTONE)
                keys.add(keyTable[i]);
        }
        return keys;
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < valueTable.length; i++) {
            if (keyTable[i] != null && keyTable[i] != TOMBSTONE)
                values.add(valueTable[i]);
        }
        return values;
    }

    public void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * loadFactor);

        K[] oldKeyTable = (K[]) new Object[capacity];
        V[] oldValueTable = (V[]) new Object[capacity];

        K[] keyTableTmp = keyTable;
        keyTable = oldKeyTable;
        oldKeyTable = keyTableTmp;

        V[] valueTableTmp = valueTable;
        valueTable = oldValueTable;
        oldValueTable = valueTableTmp;

        keyCount = usedBuckets = 0;
        for (int i = 0; i < oldKeyTable.length; i++) {
            if (oldKeyTable[i] != null && oldKeyTable[i] != TOMBSTONE) {
                put(oldKeyTable[i], oldValueTable[i]);
            }
            oldKeyTable[i] = null;
            oldValueTable[i] = null;
        }
    }
}
