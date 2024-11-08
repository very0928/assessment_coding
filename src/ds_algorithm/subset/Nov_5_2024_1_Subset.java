package ds_algorithm.subset;

import java.util.*;

/**
 * Given a set with distinct elements, find all of its distinct subsets.
 */
public class Nov_5_2024_1_Subset {
    public List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> queue = new ArrayList<>();
        queue.add(new ArrayList<>());
        for (int n : nums) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new LinkedList<>(queue.get(i));
                temp.add(n);
                queue.add(temp);
            }
        }

        return queue;
    }

    public static void main(String[] args) {
        Nov_5_2024_1_Subset sol = new Nov_5_2024_1_Subset();
        System.out.println(sol.findSubsets(new int[] {1, 3}));
    }
}
