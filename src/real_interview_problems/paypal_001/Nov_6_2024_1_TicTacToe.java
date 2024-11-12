package real_interview_problems.paypal_001;

/**
 * 348. design-tic-tac-toe
 * <a href="https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0348.Design%20Tic-Tac-Toe/README_EN.md">Solution</a>
 */
public class Nov_6_2024_1_TicTacToe {
    int[][] counters;

    public Nov_6_2024_1_TicTacToe(int n) {
        counters = new int[2][(n << 1) + 1];
    }

    /**
     Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins.
     */
    public int move(int col, int row, int player) {
        int n = counters.length;
        int idx = player - 1;
        counters[idx][row] += 1;
        counters[idx][n + col] += 1;

        if (row == col) {
            counters[idx][(n << 1)] += 1;
        }
        if (row + col == n - 1) {
            counters[idx][(n << 1) + 1] += 1;
        }
        if (counters[idx][row] == n
                || counters[idx][n + col] == n
                || counters[idx][n << 1] == n
                || counters[idx][(n << 1) + 1] == n) {
            return  player;
        }

        return 0;
    }

    public static void main(String[] args) {
        Nov_6_2024_1_TicTacToe ticTacToe = new Nov_6_2024_1_TicTacToe(3);
        System.out.println(ticTacToe.move(0, 0, 1));

    }
}
