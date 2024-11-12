package real_interview_problems.moonshot_001;

/**
 * 非负整数 {@code x} ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被舍去 。
 * @see [leetcode-69]
 */
public class Nov_11_2024_1_sqrt_x {
    public static void main(String[] args) {
        Nov_11_2024_1_sqrt_x sol = new Nov_11_2024_1_sqrt_x();

        System.out.println(sol.mySqrt(2147483647));
    }

    public int mySqrt(int num) {
        if (num <= 1) {
            return num;
        }
        int start = 1;
        int end = num;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid * mid == num) {
                return mid;
            } else if ((long) mid * mid > (long)num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return Math.round(end);
    }
}
