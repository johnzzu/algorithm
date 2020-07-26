package cn.johnzzu.ch1.execise;

import cn.johnzzu.ch1.stack.ArrayStack;

/**
 * @author john
 * @version 1.0
 * @description 练习1.3.9
 * @date 2020/7/26 3:52 下午
 */
public class E_1_3_9 {
    public static void main(String[] args) {
        String a = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        System.out.println(transform(a));
    }

    private static String transform(String str) {
        ArrayStack<String> result = new ArrayStack<>();
        ArrayStack<String> opt = new ArrayStack<>();
        String[] ss = str.split(" ");
        for (String s : ss) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                opt.push(s);
            } else if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
                result.push(s);
            } else if (s.equals("(") || s.equals(")")) {
                String r1 = result.pop();
                String r2 = result.pop();
                String op = opt.pop();
                result.push("( " + r2 + " " + op + " " + r1 + " )");
            }

        }
        return result.pop();
    }


}
