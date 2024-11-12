package real_interview_problems.moonshot_001;

/**
 * Given two integers x and y, return the Hamming distance between them.
 * <p>
 * @see The hamming distance between two integers is the number of positions
 * at which the responding bits are different
 */
public class Nov_11_2024_2_hamming_distance {
    public static void main(String[] args) {
        System.out.println(hammingDistance_2(1, 3));
    }

    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;  // 计算 x 和 y 的异或
        int distance = 0;

        // 统计异或结果中 1 的个数，即不同位的数量
        while (xor != 0) {
            distance += xor & 1; // 如果最低位是 1，则距离加 1
            xor >>= 1;           // 右移一位
        }

        return distance;
    }

    public static int hammingDistance_2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
