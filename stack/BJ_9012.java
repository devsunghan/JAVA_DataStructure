package stack;

import java.util.Scanner;
import java.util.Stack;

public class BJ_9012 {

    public static void foo(String s) {
        Stack<Character> stack = new Stack<>();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i); // 문자열 하나씩 가져오기

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.size() < 1) {
                    System.out.println("No");
                    return;
                }
                stack.pop();
            }
            i++;
        }

        if (stack.size() > 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int i = 0; i < T; i++) {
            foo(scanner.next());
        }
    }

}
