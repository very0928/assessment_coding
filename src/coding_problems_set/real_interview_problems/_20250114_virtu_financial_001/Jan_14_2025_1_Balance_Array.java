package coding_problems_set.real_interview_problems._20250114_virtu_financial_001;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array arr, where:
 * The first element arr[0] represents the weight that has already been used
 * (i.e., the weight that has been taken up by the apples already in the container).
 * The rest of the elements represent the weights of individual apples.
 * The total weight capacity is 5000 grams.
 *
 * Your task is to determine how many more apples can be added into the container
 * without exceeding the 5000-gram limit.
 */
public class Jan_14_2025_1_Balance_Array {
    public static List<Integer> balanceList(List<Integer> num) {
        // Edge case: if list has less than 3 elements, return as is (no valid neighbors)
        if (num.size() < 3) return num;

        boolean changed = true;
        while (changed) {
            changed = false;
            // Create a new list to store the updated values for the next round
            List<Integer> nextRound = new ArrayList<>(num);

            // Traverse the list from index 1 to n-2 (since we can't adjust first and last elements)
            for (int i = 1; i < num.size() - 1; i++) {
                int left = num.get(i - 1);
                int right = num.get(i + 1);
                int current = num.get(i);

                // Apply the rules:
                if (current < left && current < right) {
                    nextRound.set(i, current - 1);
                    changed = true;
                } else if (current > left && current > right) {
                    nextRound.set(i, current + 1);
                    changed = true;
                }
            }

            // Update the original list for the next round
            num = nextRound;
        }

        return num;
    }

    public static void main(String[] args) {
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(5);
        num.add(3);
        num.add(4);
        num.add(5);
        num.add(4);

        List<Integer> balancedList = balanceList(num);
        System.out.println(balancedList);  // Output the final balanced list
    }
}

