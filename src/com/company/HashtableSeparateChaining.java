package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Entry<K, V> {

    int hash;
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> other) {
        if (hash != other.hash)
            return false;

        return key.equals(other.key);
    }

    public String toString() {
        return key + " => " + value;
    }
}

@SuppressWarnings("unchecked")
public class HashtableSeparateChaining<K, V> {

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>> [] table;

    public HashtableSeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashtableSeparateChaining(int capacity, double maxLoadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity");
        }
        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)) {
            throw new IllegalArgumentException("Illegal maxLoadFactor");
        }
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    public int normalizeIndex(int hashKey) {
        return (hashKey & 0x7FFFFFFF) % capacity;
    }

    public V put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Null key");

        Entry<K, V> entry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(entry.hash);

        return bucketInsertEntry(bucketIndex, entry);
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) {
            table[bucketIndex] = bucket = new LinkedList<>();
        }
        Entry<K, V> existingEntry = bucketSeekEntry(bucketIndex, entry.key);
        if (existingEntry == null) {
            bucket.add(entry);
            if (++size > threshold) {
                resizeTable();
            }
            return null;
        } else {
            V existingValue = existingEntry.value;
            existingEntry.value = entry.value;
            return existingValue;
        }

    }

    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null)
            return null;

        for (Entry<K, V> entry : bucket) {
            if (key.equals(entry.key))
                return entry;
        }

        return null;
    }

    private void resizeTable() {
        capacity  = capacity * 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if (bucket == null)
                        newTable[bucketIndex] = bucket = new LinkedList<>();

                    bucket.add(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }

        table = newTable;
    }

    public V get(K key) {
        if (key == null)
            return null;

        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null)
            return entry.value;

        return null;
    }

    public V remove(K key) {
        if (key == null)
            return null;

        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    private V bucketRemoveEntry(int bucketIndex, K key) {
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry == null)
            return null;

        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        bucket.remove(entry);
        size--;

        return entry.value;
    }

    public boolean containsKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        return entry != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public List<K> keys() {
        List<K> keys = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    keys.add(entry.key);
                }
            }
        }

        return keys;
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    values.add(entry.value);
                }
            }
        }

        return values;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    builder.append(entry).append(", ");
                }
            }
        }
        builder.append("}");

        return builder.toString();
    }
}
