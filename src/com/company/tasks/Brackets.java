package com.company.tasks;

import com.company.Stack;

public class Brackets {

    public static boolean is_valid(String brackets) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);
            if (isLeftBracket(bracket)) {
                stack.push(bracket);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            char reversed_bracket = getReverseBracket(bracket);
            if (reversed_bracket != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    private static boolean isLeftBracket(char c) {
        return c == '[' || c == '{' || c == '(';
    }

    private static char getReverseBracket(char c) {
        if (c == ']')
            return '[';

        if (c == '}')
            return '{';

        if (c == ')')
            return '(';

        throw new RuntimeException("invalid bracket");
    }
}
