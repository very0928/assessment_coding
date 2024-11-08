package ds_algorithm.dynamic_programming;

/**
 * Given the weights and profits of 'N' items, we are asked to put these items in a knapsack that has a capacity 'C'.
 * The goal is to get the maximum profit from the items in the knapsack.
 * Difference: we are allowed to use an unlimited quantity of an item.
 */
public class Nov_7_2024_7_Unbounded_Knapsack {
    public static void main(String[] args) {
        Nov_7_2024_7_Unbounded_Knapsack sol = new Nov_7_2024_7_Unbounded_Knapsack();
        System.out.println(sol.solveKnapsack(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50));

    }

    /**
     * The only difference between the '0/1 knapsack' problem and this one is that, after including the item,
     * we are recursively call to process all the items (including the current item).
     * In '0/1 Knapsack' however, we recursively call to process the remaining items.
     */
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || weights.length == 0 || profits.length != weights.length) {
            return 0;
        }
        int[][] dp = new int[weights.length][capacity + 1];
        for (int i = 0; i < weights.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < weights.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i] <= j) {
                    dp[i][j] = profits[i] + dp[i][j - weights[i]];
                }
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        return dp[weights.length - 1][capacity];
    }
}
