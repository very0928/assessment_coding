package real_interview_problems.pexx002;

import java.util.*;

public class Nov_20_2024_1_LargeNumProduct {
    public static void main(String[] args) {
        Nov_20_2024_1_LargeNumProduct sol = new Nov_20_2024_1_LargeNumProduct();
        System.out.println(sol.multiply("1234789", "3496"));
        System.out.println("Hello world!");

    }

    /**
     * 1234789 3496
     * 1 2
     * 3 3
     *      3  6   --l1
     *   3  6   --l2
     * 1 2
     */
    public String multiply(String s1, String s2) {
        List<List<Character>> list = new ArrayList<>();
        for(int i = s2.length() - 1; i >= 0; i--) {
            int num2 = Integer.parseInt(String.valueOf(s2.charAt(i)));
            List<Character> temp = new ArrayList<>();
            int addition = 0;
            for (int j = s1.length() - 1; j >= 0; j--) {
                int num1 = Integer.parseInt(String.valueOf(s1.charAt(j)));
                int multiply = num1 * num2 + addition;
                temp.add(0, (char) (multiply % 10));     // n3 n2 n1
                addition = multiply / 10;
            }
            list.add(0, temp); // 3 6
        }
        int k = list.size() - 1;
        while (list.size() > 1) {
            List<Character> l1 = list.get(k);           // 1 2 9 0
            List<Character> l2 = list.get(k - 1);     // 1 2 1 1
            int idx1 = l1.size() - (list.size() - k + 1);
            int idx2 = l2.size() - (list.size() - k);

            List<Character> newList = new ArrayList<>();
            newList.add(0, l1.get(l1.size() - 1));

            int addition = 0;
            while (idx1 >= 0 && idx2 >= 0) {
                int sum = Integer.parseInt(String.valueOf(l1.get(idx1--)))
                        + Integer.parseInt(String.valueOf(l2.get(idx2--)));
                sum += addition;
                newList.add((char) (sum % 10));
                addition = sum / 10;
            }

            int last = Integer.parseInt(String.valueOf(l2.get(0))) + addition;
            while (last != 0) {
                newList.add((char) (last % 10));
                last = last / 10;
            }
            list.remove(k);
            list.set(k - 1, newList);

            k--;
        }

        return String.valueOf(list.get(0));
    }
}