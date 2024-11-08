package ds_algorithm.dynamic_programming;

/**
 * Given two strings 's1' and 's2', find the length of the longest substring which is common in both the strings.
 * <br> public int findLCSLength(String s1, String s2)</br>
 * Note: Starting with a substring of zero lengths, if any of the string has zero length, then the common substring will be zero length.
 */
public class Nov_7_2024_3_Longest_Common_Substring {
    public static void main(String[] args) {
        Nov_7_2024_3_Longest_Common_Substring sol = new Nov_7_2024_3_Longest_Common_Substring();
        System.out.println(sol.findLCSLength("abdca", "cbda"));
    }

    public int findLCSLength(String s1, String s2) {
        int l1 = s1.length() + 1;
        int l2 = s2.length() + 1;
        int[][] dp = new int[l1][l2];
        int max = 0;
        for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}
