package coding_problems_set.ds_algorithm.dynamic_programming;

/**
 * Given an infinite supply of 'n' coin denominations and a total money amount,
 * we are asked to find the total ways to distinct ways to make up that amount.
 *
 * <br>public int countChange(int[] denominations, int total) </br>
 */
public class Nov_7_2024_8_Coin_Change {
    public static void main(String[] args) {
        Nov_7_2024_8_Coin_Change cc = new Nov_7_2024_8_Coin_Change();
        int[] denominations = {1, 2, 5};
        System.out.println(cc.countChange(denominations, 5));
        // 11111 1112 122 113 23
    }

    public int countChange(int[] denominations, int total) {
//        if (total == 0) {
//            return 1;
//        }
//        if (denominations.length == 0) {
//            return 0;
//        }
        int l = denominations.length;
        int[][] dp = new int[l][total + 1];
        for (int i = 0; i < l; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < l; i ++) {
            for (int j = 1; j <= total; j++) {
                if (denominations[i] <= j) {
                    dp[i][j] += dp[i][j - denominations[i]];
                }
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }
        return dp[l - 1][total];
    }
}
