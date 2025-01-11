package coding_problems_set.ds_algorithm.dynamic_programming;

import java.util.Arrays;

public class Dec_18_2024_1_Longest_Increasing_Subsequence {
    public static void main(String[] args) {

    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length]; // dp[i] 以nums[i]结尾的最长递增子序列长度
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS_BS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] tail = new int[nums.length];
        int size = 0;
        for(int num : nums){
            // 在tail中通过二分查找num应该插入的位置
            int left = 0, right = size;
            while(left < right){
                int mid = (left + right) / 2;
                if(tail[mid] < num){
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tail[left] = num;
            if(left == size) size++;
        }
        return size;
    }

}
