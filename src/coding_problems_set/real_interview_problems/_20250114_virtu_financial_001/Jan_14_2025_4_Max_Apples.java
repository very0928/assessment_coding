package coding_problems_set.real_interview_problems._20250114_virtu_financial_001;

import java.util.Arrays;

public class Jan_14_2025_4_Max_Apples {
    /**
     * Calculates the maximum number of apples that
     * can be added without exceeding the total capacity.
     *
     * @param arr An array where the first element is the already used weight, and the rest are apple weights.
     * @return The maximum number of apples that can be added.
     */
    public static int maxApples(int[] arr) {
        final int TOTAL_CAPACITY = 5000;

        // Validate input array length
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array must contain at least one element representing used weight.");
        }

        int usedWeight = arr[0];
        int remainingCapacity = TOTAL_CAPACITY - usedWeight;

        // If no remaining capacity, return 0
        if (remainingCapacity <= 0) {
            return 0;
        }

        // Extract apple weights (excluding the first element)
        int[] appleWeights = Arrays.copyOfRange(arr, 1, arr.length);

        // Sort the apple weights in ascending order
        Arrays.sort(appleWeights);

        int count = 0;

        for (int weight : appleWeights) {
            if (weight <= remainingCapacity) {
                count++;
                remainingCapacity -= weight;
            } else {
                // Since the array is sorted, no need to check further
                break;
            }
        }

        return count;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Test Case 1
        int[] testCase1 = {4650, 150, 150, 150};
        int result1 = maxApples(testCase1);
        System.out.println("Test Case 1 Output: " + result1); // Expected Output: 2

        // Test Case 2
        int[] testCase2 = {4860, 100, 30, 60, 100, 30, 110, 400};
        int result2 = maxApples(testCase2);
        System.out.println("Test Case 2 Output: " + result2); // Expected Output: 3

        // Additional Test Case 3
        int[] testCase3 = {0, 5000};
        int result3 = maxApples(testCase3);
        System.out.println("Test Case 3 Output: " + result3); // Expected Output: 1

        // Additional Test Case 4
        int[] testCase4 = {4990, 5, 5, 5, 5, 10};
        int result4 = maxApples(testCase4);
        System.out.println("Test Case 4 Output: " + result4); // Expected Output: 4

        // Additional Test Case 5 (No apples can be added)
        int[] testCase5 = {5000, 1, 2, 3};
        int result5 = maxApples(testCase5);
        System.out.println("Test Case 5 Output: " + result5); // Expected Output: 0
    }
}
