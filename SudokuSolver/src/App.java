public class App {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) throws Exception {
        int[][] board = {
            {0, 0, 0, 0, 2, 0, 0, 0, 6},
            {5, 0, 0, 0, 3, 0, 0, 0, 0},
            {0, 0, 9, 5, 0, 6, 1, 0, 0},
            {0, 0, 0, 0, 5, 0, 0, 0, 0},
            {9, 0, 0, 4, 0, 7, 0, 0, 3},
            {0, 0, 1, 0, 0, 0, 0, 8, 0},
            {4, 0, 0, 6, 0, 9, 0, 0, 7},
            {0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 7, 0, 0, 0, 0, 3, 0, 0} 
          };

          printBoard(board);
    
          if (solveBoard(board)) {
            System.out.println("Solved successfully!");
          }
          else {
            System.out.println("Unsolvable board :(");
          }
          printBoard(board);
        }
  
  private static void printBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      if (row % 3 == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int col = 0; col < GRID_SIZE; col++) {
        if (col % 3 == 0 && col != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][col]);
      }
      System.out.println();
    }
  }

    private static boolean isInRow(int [][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInCol(int [][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInGrid(int [][] board, int number, int row, int col) {
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxCol; j < localBoxCol + 3; j++) {
               if (board[i][j] == number) {
                   return true;
               } 
            }
        }
        return false;
    }

    private static boolean isValidNumber(int[][] board, int number, int row, int col) {
        return !isInRow(board, number, row) && !isInCol(board, number, col) && !isInGrid(board, number, row, col);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int number = 1; number <= GRID_SIZE; number++) {
                        if (isValidNumber(board, number, row, col)) {
                            board[row][col] = number;

                            if (solveBoard(board)) {
                                return true;
                              }
                              else {
                                board[row][col] = 0;
                              }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
} 
