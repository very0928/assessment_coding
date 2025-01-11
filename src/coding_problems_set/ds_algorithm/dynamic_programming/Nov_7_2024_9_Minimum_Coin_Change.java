package coding_problems_set.ds_algorithm.dynamic_programming;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount,
 * we are asked to find the minimum number of coins needed to make up that amount.
 */
public class Nov_7_2024_9_Minimum_Coin_Change {
    public static void main(String[] args) {
        Nov_7_2024_9_Minimum_Coin_Change cc = new Nov_7_2024_9_Minimum_Coin_Change();
        int[] denominations = {3,5};
        System.out.println(cc.countChange(denominations, 7));
    }

    public int countChange(int[] denominations, int total) {
        int l = denominations.length;
        int[][] dp = new int[l][total + 1];

        for(int i=0; i < l; i++)
            for(int j=0; j <= total; j++)
                dp[i][j] = Integer.MAX_VALUE;

        for (int i = 0; i < l; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < l; i++) {
            for (int j = 1; j <= total; j++) {
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (denominations[i] <= j) {
                    if (dp[i][j - denominations[i]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - denominations[i]]);
                    }
                }
            }
        }

        return dp[l - 1][total] == Integer.MAX_VALUE ? -1 : dp[l - 1][total];
    }
}
