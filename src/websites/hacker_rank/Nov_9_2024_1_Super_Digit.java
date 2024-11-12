package websites.hacker_rank;

import java.io.*;
import java.util.Arrays;

public class Nov_9_2024_1_Super_Digit {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(System.out));
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        String n = firstMultipleInput[0];
        int k = Integer.parseInt(firstMultipleInput[1]);
        int result = superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
    /*
     * Complete the 'superDigit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING n
     *  2. INTEGER k
     */
    public static int superDigit(String n, int k) {
        // 计算 n 的初始超级和，然后乘以 k
        long initialSum = Arrays.stream(n.split(""))
                .mapToLong(Long::parseLong)
                .sum() * k;
        // 计算 initialSum 的超级数字
        return helpSuperDigit(initialSum);
    }

    private static int helpSuperDigit(long num) {
        // 基本情况：如果 num 是个位数，直接返回
        if (num < 10) {
            return (int) num;
        }
        // 否则，计算 num 的各位数字和
        long newSum = 0;
        while (num > 0) {
            newSum += num % 10;
            num /= 10;
        }
        // 递归计算 newSum 的超级数字
        return helpSuperDigit(newSum);
    }
}
