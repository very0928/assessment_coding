package ds_algorithm.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 * <br> public List<List<Integer>> findSubsets(int[] nums) </br>
 */
public class Nov_5_2024_2_Subsets_With_Duplicates {
    public List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int endIdx = res.size();
        for (int i = 0; i < nums.length; i++) {
            int startIdx = 0;
            if (i > 0 && nums[i] == nums[i - 1]) {
                startIdx = endIdx;
            }
            endIdx = res.size();
            for (int j = startIdx; j < endIdx; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Nov_5_2024_2_Subsets_With_Duplicates sol = new Nov_5_2024_2_Subsets_With_Duplicates();
        System.out.println(sol.findSubsets(new int[] {1, 5, 3, 3}));
        System.out.println(sol.findSubsets(new int[] {0, 0, 0}));

    }
}
