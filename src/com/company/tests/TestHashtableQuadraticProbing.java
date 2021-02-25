package com.company.tests;

import com.company.HashtableQuadraticProbing;
import com.company.HashtableSeparateChaining;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestHashtableQuadraticProbing {

    @Test
    public void testPutAndGet() {
        HashtableQuadraticProbing<String, String> hashtable = new HashtableQuadraticProbing<>();

        hashtable.put("Tashkent", "Uzbekistan");
        assertEquals("Uzbekistan", hashtable.get("Tashkent"));

        hashtable.put("Rome", "Italy");
        assertEquals("Italy", hashtable.get("Rome"));

        hashtable.put("Moscow", "Russia");
        assertEquals("Russia", hashtable.get("Moscow"));

        hashtable.put("Madrid", "Spain");
        assertEquals("Spain", hashtable.get("Madrid"));

        hashtable.put("London", "UK");
        assertEquals("UK", hashtable.get("London"));
    }

    @Test
    public void testRemoveAndGet() {
        HashtableQuadraticProbing<String, String> hashtable = new HashtableQuadraticProbing<>();
        hashtable.put("Tashkent", "Uzbekistan");
        hashtable.put("Rome", "Italy");
        hashtable.put("Moscow", "Russia");
        hashtable.put("Madrid", "Spain");
        hashtable.put("London", "UK");

        assertTrue(hashtable.containsKey("Moscow"));
        assertEquals(5, hashtable.size());
        hashtable.remove("Moscow");
        assertFalse(hashtable.containsKey("Moscow"));
        assertEquals(4, hashtable.size());

        assertTrue(hashtable.containsKey("London"));
        assertEquals(4, hashtable.size());
        hashtable.remove("London");
        assertFalse(hashtable.containsKey("London"));
        assertEquals(3, hashtable.size());

        assertTrue(hashtable.containsKey("Tashkent"));
        assertEquals(3, hashtable.size());
        hashtable.remove("Tashkent");
        assertFalse(hashtable.containsKey("Tashkent"));
        assertEquals(2, hashtable.size());
    }

    @Test
    public void testKeysAndValues() {
        HashtableQuadraticProbing<String, String> hashtable = new HashtableQuadraticProbing<>();
        hashtable.put("Tashkent", "Uzbekistan");
        hashtable.put("Rome", "Italy");
        hashtable.put("Moscow", "Russia");
        hashtable.put("Madrid", "Spain");
        hashtable.put("London", "UK");

        List<String> keys = hashtable.keys();
        assertTrue(keys.contains("Tashkent"));
        assertTrue(keys.contains("Rome"));
        assertTrue(keys.contains("Moscow"));
        assertTrue(keys.contains("Madrid"));
        assertTrue(keys.contains("London"));
        assertEquals(5, keys.size());

        List<String> values = hashtable.values();
        assertTrue(values.contains("Uzbekistan"));
        assertTrue(values.contains("Italy"));
        assertTrue(values.contains("Russia"));
        assertTrue(values.contains("Spain"));
        assertTrue(values.contains("UK"));
        assertEquals(5, values.size());
    }
}
