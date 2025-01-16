package coding_problems_set.ds_algorithm.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 * <br> public List<List<Integer>> findSubsets(int[] nums) </br>
 */
public class Nov_5_2024_2_Subsets_With_Duplicates {
    public static void main(String[] args) {
        Nov_5_2024_2_Subsets_With_Duplicates sol = new Nov_5_2024_2_Subsets_With_Duplicates();
        System.out.println(sol.findSubsets(new int[] {1, 5, 3, 3}));
        System.out.println(sol.findSubsets(new int[] {0, 0, 0}));

    }

    public List<List<Integer>> findSubsets(int[] nums) {
        Arrays.sort(nums); // Step 1: sort
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        int startIndex = 0, endIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            startIndex = 0;
            // Step 2: create new subsets only from the subsets added in the previous step
            if (i > 0 && nums[i] == nums[i - 1]) {
                startIndex = endIndex + 1;
            }
            endIndex = subsets.size() - 1;
            for (int j = startIndex; j <= endIndex; j++) {
                List<Integer> set = new ArrayList<>(subsets.get(j));
                set.add(nums[i]);
                subsets.add(set);
            }
        }
        return subsets;
    }
}
