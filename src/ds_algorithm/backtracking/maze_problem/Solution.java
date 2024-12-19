package ds_algorithm.backtracking.maze_problem;

class Solution {
    private int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}}; // 右左下上

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // 特殊情况判断
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return false;
        }

        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];

        return dfs(maze, start[0], start[1], destination, visited);
    }

    private boolean dfs(int[][] maze, int x, int y, int[] destination, boolean[][] visited) {
        // 如果已访问过，返回false避免重复访问
        if (visited[x][y]) {
            return false;
        }

        // 如果到达终点，返回true
        if (x == destination[0] && y == destination[1]) {
            return true;
        }

        visited[x][y] = true;

        // 尝试四个方向
        for (int[] dir : directions) {
            int newX = x;
            int newY = y;

            // 球会一直滚直到遇到墙
            while (newX >= 0 && newX < maze.length &&
                    newY >= 0 && newY < maze[0].length &&
                    maze[newX][newY] == 0) {
                newX += dir[0];
                newY += dir[1];
            }

            // 回退一步，因为上面的while循环会多走一步撞墙
            newX -= dir[0];
            newY -= dir[1];

            // 如果这条路径可以到达终点，返回true
            if (!visited[newX][newY] && dfs(maze, newX, newY, destination, visited)) {
                return true;
            }
        }

        return false;
    }
}