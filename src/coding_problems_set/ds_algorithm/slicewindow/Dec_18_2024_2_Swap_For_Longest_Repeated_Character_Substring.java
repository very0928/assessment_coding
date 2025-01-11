package coding_problems_set.ds_algorithm.slicewindow;

public class Dec_18_2024_2_Swap_For_Longest_Repeated_Character_Substring {
    public static void main(String[] args) {
        Dec_18_2024_2_Swap_For_Longest_Repeated_Character_Substring sol = new Dec_18_2024_2_Swap_For_Longest_Repeated_Character_Substring();
        System.out.println(sol.maxReOpt1("ababa"));
        System.out.println(sol.maxReOpt1("aaabaaa"));

    }

    public int maxReOpt1(String text) {
        int[] count = new int[26];
        for (char c : text.toCharArray()) {
            count[c - 'a'] ++;
        }

        int maxLength = 0;
        int n = text.length();

        for (int i = 0; i < n;) {
            char currentChar = text.charAt(i);
            int j = i;

            while (j < n && text.charAt(j) == currentChar) j++;

            int currentLength = j - i;
            if (count[currentChar - 'a'] > currentLength) {
                currentLength++;
            }

            int k = j + 1;
            while (k < n && text.charAt(k) == currentChar) k++;

            int r = k - j - 1;
            maxLength = Math.max(maxLength,
                    Math.min(currentLength + r + 1, count[currentChar - 'a']));

            i = j;
        }

        return maxLength;
    }

}
