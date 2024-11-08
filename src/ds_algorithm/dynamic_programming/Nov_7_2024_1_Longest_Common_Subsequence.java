package ds_algorithm.dynamic_programming;

/**
 * Given a subsequence, find the length of its Longest Palindromic Subsequence(LPS).
 * Case 1: elements at the beginning and the end are the same
 * Case 2: skip one element either from the beginning or the end
 */
class Nov_7_2024_1_Longest_Common_Subsequence {
    public int findLPSLength(String st) {
        int l = st.length();
        int[][] dp = new int[l][l];
        for (int i = 0; i < l; i++) {
            dp[i][i] = 1;
        }
        int max = 0;
        for (int i = l - 1; i >= 0; i--) {
            for (int j = i + 1; j < l; j++) {
                if (st.charAt(i) == st.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }


    public static void main(String[] args) {
        Nov_7_2024_1_Longest_Common_Subsequence sol = new Nov_7_2024_1_Longest_Common_Subsequence();
        System.out.println(sol.findLPSLength("abdbca"));
    }

}