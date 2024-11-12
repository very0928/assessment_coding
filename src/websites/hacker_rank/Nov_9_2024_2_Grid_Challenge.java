package websites.hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nov_9_2024_2_Grid_Challenge {
    public static void main(String[] args) {
        System.out.println(gridChallenge(new ArrayList<>(Arrays.asList("ebacd",
                "fghij",
                "olmkn",
                "trpqs",
                "xywuv"))));

    }

    /*
     * Complete the 'gridChallenge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static String gridChallenge(List<String> grid) {
        char[] previous = null;
        for (String s : grid) {
            char[] current = s.toCharArray();
            Arrays.sort(current);
            if (previous != null) {
                for (int j = 0; j < current.length; j++) {
                    if (previous[j] > current[j]) {
                        return "NO";
                    }
                }
            }
            previous = current;
        }
        return "YES";
    }
}
