package coding_problems_set.real_interview_problems._20241120_pexx_001;

import java.util.LinkedList;
import java.util.Queue;

public class Nov_20_2024_1_Shortest_Path {
    public static void main(String[] args) {

    }

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1) {
            return 0;
        }
        int[][] directions = {{0, -1}, {0, 1}, {-1,0}, {1,0}};
        boolean [][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0 ,0 , k, 0});
        visited[0][0][k] = true;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int x = state[0];
            int y = state[1];
            int remainK = state[2];
            int steps = state[3];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int newK = remainK - grid[nx][ny];
                    if (newK < 0) {
                        continue;
                    }
                    if (nx == m - 1 && ny == n - 1) {
                        return steps + 1;
                    }

                    if (!visited[nx][ny][newK]) {
                        visited[nx][ny][newK] = true;
                        queue.offer(new int[] {nx, ny, newK, steps + 1});
                    }
                }
            }
        }
        return -1;
    }


    public int shortestPath(int[][] grid, int[] start, int[] end) {
        int m = grid.length;
        int n = grid[0].length;

        // 定义四个方向
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 创建队列，用于 BFS
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        // 初始化 BFS
        queue.add(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = true;
        int steps = 0;

        // 开始 BFS
        while (!queue.isEmpty()) {
            int size = queue.size();

            // 遍历当前层
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                // 如果到达终点，返回步数
                if (x == end[0] && y == end[1]) {
                    return steps;
                }

                // 向四个方向扩展
                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    // 检查边界、是否访问过以及是否可达
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n
                            && !visited[newX][newY] && grid[newX][newY] == 0) {
                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }

            // 步数增加
            steps++;
        }

        // 如果无法到达终点，返回 -1
        return -1;
    }

}
