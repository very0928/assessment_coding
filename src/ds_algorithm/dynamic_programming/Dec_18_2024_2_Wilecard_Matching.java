package ds_algorithm.dynamic_programming;

public class Dec_18_2024_2_Wilecard_Matching {
    public static void main(String[] args) {
        Dec_18_2024_2_Wilecard_Matching sol = new Dec_18_2024_2_Wilecard_Matching();
        System.out.println(sol.isMatch_1("acdcb", "a*b?b"));
    }
    public boolean isMatch_2(String s, String p) {
        int m = s.length();
        int n = p.length();

        // 使用一维数组替代二维数组
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 空字符串与空模式匹配

        // 初始化 dp 数组
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[j] = dp[j - 1];
            } else {
                break;
            }
        }

        // 填充 dp 数组
        for (int i = 1; i <= m; i++) {
            boolean prev = dp[0]; // 记录 dp[i-1][j-1]
            dp[0] = false; // 当前行第一个位置为 false
            for (int j = 1; j <= n; j++) {
                boolean temp = dp[j];
                if (p.charAt(j - 1) == '*') {
                    dp[j] = dp[j - 1] || dp[j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[j] = prev;
                } else {
                    dp[j] = false;
                }
                prev = temp;
            }
        }

        return dp[n];
    }


    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            } else break;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?'
                || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public boolean isMatch_1(String s, String p) {
        return matchHelper(s, p, 0, 0, new Boolean[s.length() + 1][p.length() + 1]);
    }

    public boolean matchHelper(String s, String p, int i, int j, Boolean[][] memo) {
        // Check if result is already computed
        if (memo[i][j] != null) return memo[i][j];

        // Base case: both string and pattern are fully matched
        if (i == s.length() && j == p.length()) {
            return memo[i][j] = true;
        }

        // Base case: pattern is fully traversed, but string is not
        if (j == p.length()) {
            return memo[i][j] = false;
        }

        // Case: current pattern character is '*'
        if (p.charAt(j) == '*') {
            // Match zero or more characters
            boolean match = (i < s.length() && matchHelper(s, p, i + 1, j, memo)) || matchHelper(s, p, i, j + 1, memo);
            return memo[i][j] = match;
        }

        // Case: current pattern character is '?' or matches the string character
        if (i < s.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
            return memo[i][j] = matchHelper(s, p, i + 1, j + 1, memo);
        }

        // If no cases match, return false
        return memo[i][j] = false;
    }

}
