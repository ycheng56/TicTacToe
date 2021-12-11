package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represents a TicTacToe Model, which implements the TicTacToe interface
 */
public class TicTacToeModel implements TicTacToe {

  // add your implementation here
  private Player[][] board;
  private int round;

  /**
   * Construct an TicTacToe Model. No parameter is given.
   */
  public TicTacToeModel() {
    resetGame();
  }

  /**
   * Return the string format game status
   * @return the string format game status
   */
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
            row -> " " + Arrays.stream(row).map(
                p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
  }

  @Override
  public void move(int r, int c) throws IllegalArgumentException,IllegalStateException {
    // Check if game over
    if (isGameOver()) {
      throw new IllegalStateException("Game over");
    }
    // Check if the position is occupied
    if (getMarkAt(r,c) != null) {
      throw new IllegalArgumentException("The space is occupied.");
    }
    // Update the board and round
    board[r][c] = getTurn();
    round++;
  }

  @Override
  public Player getTurn() {
    if (isGameOver()) {return null;}
    // Player X: 0,2,4,6,8 Player O: 1,3,5,7
    return (round % 2 == 0) ? Player.X : Player.O;
  }

  @Override
  public boolean isGameOver() {
    // board is full or someoneWin
    boolean hasWinner = horizontalWin() || verticalWin() || diagonalWin();
    return (round == 9)|| hasWinner;
  }

  @Override
  public Player getWinner() {
    if (horizontalWin() || verticalWin() || diagonalWin()) {
      return (round % 2 == 1) ? Player.X : Player.O;
    }
    return null;
  }

  @Override
  public Player[][] getBoard() {
    return Arrays.stream(board).map(Player[]::clone).toArray(d->board.clone());
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r > 2 || c < 0 || c > 2) {
      throw new IllegalArgumentException("Row or Column index must be 0, 1, or 2.");
    }
    return this.board[r][c];
  }

  @Override
  public void resetGame() {
    this.board = new Player[3][3];
    this.round = 0;
  }

  /**
   * Determine if current board has the same three marks in any row
   * @return current board has the same three marks in any row
   */
  private boolean horizontalWin() {
    for (int r = 0; r < 3; r++) {
      if (
        board[r][0] !=null &&
        board[r][0] == board[r][1] &&
        board[r][1] == board[r][2]) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determine if current board has the same three marks in any column
   * @return current board has the same three marks in any column
   */
  private boolean verticalWin() {
    for (int c = 0; c < 3; c++) {
      if (
          board[0][c] !=null &&
              board[0][c] == board[1][c] &&
              board[1][c] == board[2][c]) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determine if current board has the same three marks in any diagonal
   * @return current board has the same three marks in any diagonal
   */
  private boolean diagonalWin() {
    return board[1][1] != null && ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2] == board[1][1]
        && board[1][1] == board[2][0]));
  }
}
