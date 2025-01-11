package coding_problems_set.real_interview_problems._20250106_ebay_001;

public class Jan_06_2025_1_MaximumSubarray {
    public static void main(String[] args) {
        Jan_06_2025_1_MaximumSubarray solution = new Jan_06_2025_1_MaximumSubarray();

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(nums)); // 输出 6
    }

    // 动态规划解法
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0; // 防止空数组
        int n = nums.length;
        int[] dp = new int[n]; // dp[i] 表示以 nums[i] 为结尾的最大子数组和
        dp[0] = nums[0]; // 初始化第一个元素
        int maxSum = dp[0]; // 初始化最大和为第一个元素的值
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]); // 更新最大和
        }
        return maxSum;
    }

}
