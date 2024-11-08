package ds_algorithm.dynamic_programming;

/**
 * Given the weights and profits of 'N' items, we are asked to put these items in a knapsack that has a capacity of 'C'.
 * The goal is to get the Maximum profit from the items in the knapsack.
 * Each item can only be selected by once, as we don't have multiple quantities of any item.
 * <br>public int solveKnapsack(int[] profits, int[] weights, int capacity)</br>
 */
public class Nov_7_2024_5_Knapsack_0_1 {
    public static void main(String[] args) {
        Nov_7_2024_5_Knapsack_0_1 sol = new Nov_7_2024_5_Knapsack_0_1();
        System.out.println(sol.solveKnapsack(new int[]{1,6,10,16}, new int[]{1,2,3,5}, 7));
        System.out.println(sol.solveKnapsack_1(new int[]{1,6,10,16}, new int[]{1,2,3,5}, 7));

    }

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        int[][] dp = new int[weights.length][capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            dp[0][i] = weights[0] <= i ? profits[0] : 0;
        }
        for (int i = 1; i < weights.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i] <= j) {
                    dp[i][j] = Math.max(profits[i] + dp[i - 1][j - weights[i]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weights.length - 1][capacity];
    }

    public int solveKnapsack_1(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        // populate the capacity=0 columns, with '0' capacity we have '0' profit
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        // if we have only one weight, we will take it if it is not more than the capacity
        for(int c=0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for(int i=1; i < n; i++) {
            for(int c=1; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i-1][c-weights[i]];
                // exclude the item
                profit2 = dp[i-1][c];
                // take maximum
                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        // maximum profit will be at the bottom-right corner.
        return dp[n-1][capacity];
    }
}
