package ds_algorithm.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, find all of its permutations preserving the character sequence but changing case.
 * <br>public List<String> findLetterCaseStringPermutations(String str) </br>
 */
public class Nov_5_2024_4_String_Permutations_by_Changing_Case {

    public List<String> findLetterCaseStringPermutations(String str) {
        List<String> result = new ArrayList<>();
        result.add(str);
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                continue;
            }
            int size = result.size();
            for (int i = 0; i < size; i++) {
                String temp = result.get(i);
                if (Character.isUpperCase(c)) {
                    temp = temp.replace(c, Character.toLowerCase(c));
                } else {
                    temp = temp.replace(c, Character.toUpperCase(c));
                }
                result.add(temp);
            }

        }
        return result;
    }

    public List<String> findLetterCaseStringPermutations_2(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null) return permutations;
        permutations.add(str);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isDigit(c)) continue;

            int size = permutations.size();
            for (int j = 0; j < size; j++) {
                char[] chs = permutations.get(j).toCharArray();
                chs[i] = Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
                permutations.add(String.valueOf(chs));
            }
        }
        return permutations;
    }


    public static void main(String[] args) {
        Nov_5_2024_4_String_Permutations_by_Changing_Case sol = new Nov_5_2024_4_String_Permutations_by_Changing_Case();
        System.out.println(sol.findLetterCaseStringPermutations("ab7c"));
    }
}
