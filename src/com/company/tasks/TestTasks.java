package com.company.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestTasks {

    @Test
    public void testBrackets() {
        assertTrue(Brackets.is_valid("[][]{}()(({}))"));
        assertTrue(Brackets.is_valid("[[[{}(())]]]"));
        assertTrue(Brackets.is_valid("[[(){}{}]]()"));

        assertFalse(Brackets.is_valid("[[(}]]"));
        assertFalse(Brackets.is_valid("{}[[))"));
        assertFalse(Brackets.is_valid("[]{}((])"));
    }
}
