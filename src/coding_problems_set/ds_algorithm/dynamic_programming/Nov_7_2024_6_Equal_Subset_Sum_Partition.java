package coding_problems_set.ds_algorithm.dynamic_programming;

import java.util.Arrays;

/**
 * Give a set of positive numbers, find if we can partition it into two subsets such that
 * the sum of elements in both subset is equal.
 * <br>public boolean canPartition(int[] num)</br>
 */
public class Nov_7_2024_6_Equal_Subset_Sum_Partition {
    public static void main(String[] args) {

    }

    public boolean canPartition(int[] num) {
        int sum = Arrays.stream(num).sum();
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean[][] dp = new boolean[num.length][sum + 1];
        for (int s = 0; s <= sum; s++) {
            dp[0][s] = num[0] == s;
        }
        for (int i = 0; i < num.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < num.length; i++) {
            for (int j = 1; j <= sum; j++) {
                // If we can get the sum 'j' without the number ar index 'i'
                if (dp[i - 1][j]) {
                    dp[i][j] = dp[i - 1][j];
                } else if (num[i] <= j) { // else if we can find a subset to get the remaining sum
                    dp[i][j] = dp[i - 1][j - num[i]];
                }
            }
        }
        return dp[num.length - 1][sum];
    }

}
