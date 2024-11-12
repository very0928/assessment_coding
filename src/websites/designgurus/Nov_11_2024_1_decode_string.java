package websites.designgurus;

import java.util.Stack;

public class Nov_11_2024_1_decode_string {
    public static void main(String[] args) {

    }

    public static String decodeStringStack(String s) {
        Stack<String> stack = new Stack<>();
        Stack<Integer> repeatStack = new Stack<>();
        StringBuilder currentStr = new StringBuilder();
        int repeatCount = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                repeatCount = repeatCount * 10 + (c - '0');
            } else if (c == '[') {
                repeatStack.push(repeatCount);
                stack.push(currentStr.toString());
                repeatCount = 0;
                currentStr = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder(stack.pop());
                int repeat = repeatStack.pop();
                temp.append(currentStr.toString().repeat(repeat));
                currentStr = temp;
            } else {
                currentStr.append(c);
            }
        }

        return currentStr.toString();
    }

}
