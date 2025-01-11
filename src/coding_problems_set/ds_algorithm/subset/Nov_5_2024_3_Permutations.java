package coding_problems_set.ds_algorithm.subset;

import java.util.*;

/**
 * Given a set of distinct numbers, find all of its permutations.
 * <br>public List<List<Integer>> findPermutations(int[] nums)</br>
 *
 * <br>Permutation is defined as the re-arranging of the elements of the set. For example, {1, 2, 3} has the following six permutations:
 * {1, 2, 3} {1, 3, 2} {2, 1, 3} {2, 3, 1} {3, 1, 2} {3, 2, 1} </br>
 */
public class Nov_5_2024_3_Permutations {
    public List<List<Integer>> findPermutations(int[] nums) {
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>());

        List<List<Integer>> res = new ArrayList<>();
        for (int num : nums) {
            int size = queue.size();
            for (int j = 0; !queue.isEmpty() && j < size; j++) {
                List<Integer> temp = queue.poll();
                for (int k = 0; k <= temp.size(); k++) {
                    List<Integer> appended = new ArrayList<>(temp);
                    appended.add(k, num);
                    if (appended.size() == nums.length) {
                        res.add(appended);
                    } else {
                        queue.add(appended);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Nov_5_2024_3_Permutations sol = new Nov_5_2024_3_Permutations();
        System.out.println(sol.findPermutations(new int[]{}));
    }
}
