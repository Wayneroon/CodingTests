package com.company.bamin;

import java.util.Stack;

public class Seventh {

    public static void main(String[] args) {
        // System.out.println(solution("zyelleyz"));
        System.out.println(solution("browoanoommnaon"));
    }

    private static String solution(String cryptogram) {
        String result = null;

        Stack<Character> stack = new Stack<>();
        char[] chars = cryptogram.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (stack.empty()) {
                stack.push(aChar);
            } else {
                removeStack(stack, aChar);
            }
        }

        return stack.toString();
    }

    private static void removeStack(Stack<Character> stack, char aChar) {
        if (stack.size() > 0 && stack.peek().equals(aChar)) {
            stack.pop();
        } else {
            stack.push(aChar);
        }
    }
}
