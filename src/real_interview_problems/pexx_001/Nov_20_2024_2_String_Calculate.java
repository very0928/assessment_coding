package real_interview_problems.pexx_001;

import java.io.UnsupportedEncodingException;
import java.util.Stack;

public class Nov_20_2024_2_String_Calculate {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "我a爱中华abc";
        //String str = "我ABC汉";
        int num = trimGBK(str.getBytes("GBK"), 7);
        System.out.println("截取后的结果是： " + str.substring(0, num));
    }

    public static int trimGBK(byte[] buf, int n)
    {
        int num = 0;
        boolean bChineseFirstHalf = false;
        for(int i = 0; i < n; i++)
        {
            System.out.println("字节大小："+buf[i]);//判断输出单个字节数的大小
            if(buf[i] < 0 && !bChineseFirstHalf)
            {
                bChineseFirstHalf = true;
            }
            else
            {
                num++;
                bChineseFirstHalf = false;
            }
        }
        return num;
    }
    /**
     * 3+6*2/8-7
     */
    double calculate(String str) {
        Stack<Double> numberStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        int idx = 0;
        while (idx < str.length()) {
            char c = str.charAt(idx);
            if (Character.isDigit(c)) {
                numberStack.push(Double.parseDouble(String.valueOf(c)));
                idx++;
            } else if (c == '+' || c == '-') {
                opStack.push(c);
                idx++;
            } else {
                double op1 = numberStack.pop();
                double op2 = Double.parseDouble(String.valueOf(str.charAt(idx + 1)));
                if (c == '*') {
                    numberStack.push(op1 * op2);
                } else {
                    numberStack.push(op1 / op2);
                }
                idx += 2;
            }

        }
        if (numberStack.size() - opStack.size() != 1) {
            return -1;
        }
        Stack<Double> reverseNumStack = new Stack<>();
        while (!numberStack.isEmpty()) {
            reverseNumStack.add(numberStack.pop());
        }
        Stack<Character> reverseOpStack = new Stack<>();
        while (!opStack.isEmpty()) {
            reverseOpStack.add(opStack.pop());
        }
        while (!reverseOpStack.isEmpty()) {
            double op1 = reverseNumStack.pop();
            double op2 = reverseNumStack.pop();
            char operator = reverseOpStack.pop();
            if (operator == '+') {
                reverseNumStack.push(op1 + op2); // (3- 2)- 1
            } else {
                reverseNumStack.push(op1 - op2);
            }
        }

        return reverseNumStack.pop();
    }
}
