package coding_problems_set.ds_algorithm.dynamic_programming;

/**
 * Given two strings 's1' and 's2', find the length of the longest subsequence which is common in both the strings.
 * <br>public int findLCSLength(String s1, String s2)</br>
 */
public class Nov_7_2024_4_Longest_Common_Subsequence {

    public static void main(String[] args) {
        Nov_7_2024_4_Longest_Common_Subsequence lcs = new Nov_7_2024_4_Longest_Common_Subsequence();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));
        System.out.println(lcs.findLCSLength_1("abdca", "cbda"));
        System.out.println(lcs.findLCSLength_1("passport", "ppsspt"));
    }

    public int findLCSLength_1(String s1, String s2) {
        int[][] dp = new int[2][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i%2][j] = 1 + dp[(i-1)%2][j-1];
                else
                    dp[i%2][j] = Math.max(dp[(i-1)%2][j], dp[i%2][j-1]);

                maxLength = Math.max(maxLength, dp[i%2][j]);
            }
        }
        return maxLength;
    }

    public int findLCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLength = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }
}
