package coding_problems_set.ds_algorithm.backtracking.maze_problem;
import java.util.Arrays;

public class MazeTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        runTestCases(solution);
    }

    private static void test(int testNumber, Solution solution, int[][] maze,
                             int[] start, int[] destination, boolean expected) {
        boolean result = solution.hasPath(maze, start, destination);
        System.out.println("Test Case " + testNumber + ":");
        System.out.println("Maze:");
        printMaze(maze);
        System.out.println("Start: " + Arrays.toString(start));
        System.out.println("Destination: " + Arrays.toString(destination));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println("-------------------");
    }

    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void runTestCases(Solution solution) {
        // Test Case 1: Basic path exists
        test(1, solution,
                new int[][] {
                        {0,0,1,0,0},
                        {0,0,0,0,0},
                        {0,0,0,1,0},
                        {1,1,0,1,1},
                        {0,0,0,0,0}
                },
                new int[]{0,4},
                new int[]{4,4},
                true
        );

        // Test Case 2: No path exists
        test(2, solution,
                new int[][] {
                        {0,0,1,0,0},
                        {0,0,0,0,0},
                        {0,0,0,1,0},
                        {1,1,1,1,1},
                        {0,0,0,0,0}
                },
                new int[]{0,4},
                new int[]{4,4},
                false
        );

        // Test Case 3: Single cell maze
        test(3, solution,
                new int[][] {{0}},
                new int[]{0,0},
                new int[]{0,0},
                true
        );

        // Test Case 4: Start equals destination
        test(4, solution,
                new int[][] {
                        {0,0},
                        {0,0}
                },
                new int[]{0,0},
                new int[]{0,0},
                true
        );

        // Test Case 5: Maze with loop path
        test(5, solution,
                new int[][] {
                        {0,0,0,0,0},
                        {1,1,0,0,0},
                        {0,0,0,0,0},
                        {0,1,0,0,0},
                        {0,0,0,0,0}
                },
                new int[]{0,0},
                new int[]{4,4},
                true
        );

        // Test Case 6: Narrow corridor
        test(6, solution,
                new int[][] {
                        {0,1,0},
                        {0,1,0},
                        {0,0,0}
                },
                new int[]{0,0},
                new int[]{2,2},
                true
        );

        // Test Case 7: Only walls around destination
        test(7, solution,
                new int[][] {
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,1,1},
                        {0,0,1,0}
                },
                new int[]{0,0},
                new int[]{3,3},
                false
        );

        // Test Case 8: Empty maze
        test(8, solution,
                new int[][] {
                        {0,0,0},
                        {0,0,0},
                        {0,0,0}
                },
                new int[]{0,0},
                new int[]{2,2},
                true
        );

        // Test Case 9: Maze with minimal path
        test(9, solution,
                new int[][] {
                        {0,1},
                        {0,0}
                },
                new int[]{0,0},
                new int[]{1,1},
                true
        );

        // Test Case 10: Complex maze with multiple potential paths
        test(10, solution,
                new int[][] {
                        {0,0,0,0,0},
                        {0,1,0,1,0},
                        {0,1,0,1,0},
                        {0,0,0,0,0},
                        {0,1,1,1,0}
                },
                new int[]{0,0},
                new int[]{4,4},
                true
        );
    }
}