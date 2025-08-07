import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    private static char[][] grid = new char[3][3];

    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private static char currentPlayer = PLAYER_X;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }

        while (true) {
            printGrid();

            int row = -1;
            int col = -1;
            boolean validMove = false;

            while (!validMove) {
                System.out.println("Player " + currentPlayer + ", enter your move (row, col): ");
                try {
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                        if (grid[row][col] == ' ') {
                            validMove = true;
                        } else {
                            System.out.println("This cell is already taken. Please choose another one.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a row and column between 0 and 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numbers for row and col.");
                    scanner.next();
                }
            }

            grid[row][col] = currentPlayer;

            if (isGameOver()) {
                printGrid();

                if (hasWinner()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    System.out.println("It's a tie!");
                }

                break;
            }

            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }

        scanner.close();
    }

    private static void printGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("-+-+-");
            }
        }
    }

    private static boolean isGameOver() {
        return hasWinner() || isFull();
    }

    private static boolean hasWinner() {
        for (int i = 0; i < 3; i++) {
            if (isRowWin(i)) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (isColWin(i)) {
                return true;
            }
        }

        if (isDiag1Win() || isDiag2Win()) {
            return true;
        }

        return false;
    }

    private static boolean isRowWin(int row) {
        return (grid[row][0] != ' ' && grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2]);
    }

    private static boolean isColWin(int col) {
        return (grid[0][col] != ' ' && grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col]);
    }

    private static boolean isDiag1Win() {
        return (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]);
    }

    private static boolean isDiag2Win() {
        return (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]);
    }

    private static boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}