package real_interview_problems.PayPal_002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Each person wears a sticker indicating their initial position in the queue from 1 to n.
 * Any person can bribe the person directly in front of them to swap positions, but they still wear their initial sticker.
 * Any person can bribe at most two others.
 * <br></br>
 * Determine the minimum number of bribes that took place to get to a given queue order.
 * Print the number of bribes, or, if anyone has bribed more than two people, print "Too Chaotic"
 * <br>public static void minimumBribes(List<Integer> q) </br>
 */
public class Nov_8_2024_1_New_Year_Choas {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                List<Integer> q = Stream.of(bufferedReader.readLine()
                                .replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());
                minimumBribes(q);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */
    public static void minimumBribes(List<Integer> q) {
        int bribes = 0;  // 贿赂次数计数器
        for (int i = q.size() - 1; i >= 0; i--) {
            // 检查当前位置和初始位置偏移是否超过2位
            if (q.get(i) - (i + 1) > 2) {
                System.out.println("Too Chaotic");
                return;
            }
            // 计算贿赂次数：只检查最大允许贿赂范围
            for (int j = Math.max(0, q.get(i) - 2); j < i; j++) {
                if (q.get(j) > q.get(i)) {
                    bribes++;
                }
            }
        }
        System.out.println(bribes);  // 输出总贿赂次数
    }
}
