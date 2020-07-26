package cn.johnzzu.ch1.execise;

import cn.johnzzu.ch1.stack.ArrayStack;

/**
 * @author john
 * @version 1.0
 * @description 练习1.3.4
 * @date 2020/7/26 3:19 下午
 */
public class E_1_3_4 {
    public static void main(String[] args) {
        String a = "[()]{}{[()()]()}";
        String b = "[(])";
        System.out.println(isParenthesesPairing(a));
        System.out.println(isParenthesesPairing(b));
    }

    private static boolean isParenthesesPairing(String s) {
        ArrayStack<String> stack = new ArrayStack<>();
        String[] ss = s.split("");
        for (String s1 : ss) {
            if (stack.isEmpty()) {
                stack.push(s1);
            } else {
                String peek = stack.peek();
                boolean isPeekPair = (peek.equals("{") && s1.equals("}"))
                        || (peek.equals("[") && s1.equals("]"))
                        || (peek.equals("(") && s1.equals(")"));
                if (isPeekPair) {
                    stack.pop();
                } else {
                    stack.push(s1);
                }
            }
        }
        return stack.isEmpty();
    }
}
