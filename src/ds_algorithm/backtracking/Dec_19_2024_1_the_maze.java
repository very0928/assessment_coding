package ds_algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a ball in a maze with empty spaces and walls.
 * The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 * -----------
 * Given the ball's start position, the destination and the maze,
 * determine whether the ball could stop at the destination.
 * -----------
 * The maze is represented by a binary 2D array.
 * 1 means the wall and 0 means the empty space.
 * You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column indexes.
 */
public class Dec_19_2024_1_the_maze {
    public static void main(String[] args) {
        Dec_19_2024_1_the_maze sol = new Dec_19_2024_1_the_maze();
        // Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]],
        // start = [0,4], destination = [3,2]
        System.out.println(sol.findPath(new int[][] {
                        {0,0,0,0,0},
                        {0,1,0,1,0},
                        {0,1,0,1,0},
                        {0,0,0,0,0},
                        {0,1,1,1,0}
                },
                new int[]{0,0},
                new int[]{4,4}));
    }

    /**
     * Input 1: a maze represented by a 2D array
     * ----------------------
     * 0 0 1 0 0
     * 0 0 0 0 0
     * 0 0 0 1 0
     * 1 1 0 1 1
     * 0 0 0 0 0
     * ----------------------
     * Input 2: start coordinate (rowStart, colStart) = (0, 4)
     * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
     * ----------------------
     * Output: true
     */
    private int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    public List<int[]> findPath(int[][] maze, int[] start, int[] destination) {
        List<int[]> path = new ArrayList<>();
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return path;
        }

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<int[]> currentPath = new ArrayList<>();
        dfs(maze, start[0], start[1], destination, visited, currentPath, path);

        return path;
    }

    private boolean dfs(int[][] maze, int x, int y, int[] destination,
                        boolean[][] visited, List<int[]> currentPath, List<int[]> result) {
        if (visited[x][y]) {
            return false;
        }

        currentPath.add(new int[]{x, y});

        if (x == destination[0] && y == destination[1]) {
            result.addAll(currentPath);
            return true;
        }

        visited[x][y] = true;

        for (int[] dir : directions) {
            int newX = x;
            int newY = y;

            while (newX >= 0 && newX < maze.length &&
                    newY >= 0 && newY < maze[0].length &&
                    maze[newX][newY] == 0) {
                newX += dir[0];
                newY += dir[1];
            }

            newX -= dir[0];
            newY -= dir[1];

            if (!visited[newX][newY] &&
                    dfs(maze, newX, newY, destination, visited, currentPath, result)) {
                return true;
            }
        }

        currentPath.remove(currentPath.size() - 1);  // backtrack
        return false;
    }

}
