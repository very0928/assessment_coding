package coding_problems_set.ds_algorithm.dynamic_programming;

/**
 * Given a string, find the length of its Longest Palindromic Substring (LPS).
 * <br>public int findLPSLength(String st)</br>
 */
public class Nov_7_2024_2_Longest_Palindromic_Substring {
    public static void main(String[] args) {
        Nov_7_2024_2_Longest_Palindromic_Substring sol = new Nov_7_2024_2_Longest_Palindromic_Substring();
        System.out.println(sol.findLPSLength("aa"));
    }

    public int findLPSLength(String st) {
        int l = st.length();
        boolean[][] dp = new boolean[l][l];
        for (int i = 0; i < l; i++) {
            dp[i][i] = true;
        }
        int max = 1;
        for (int i = l - 1; i >= 0; i--) {
            for (int j = i + 1; j < l; j++) {
                if (st.charAt(i) == st.charAt(j) && (j - i == 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    max = Math.max(max, (j - i + 1));
                }
            }
        }

        return max;
    }
}
