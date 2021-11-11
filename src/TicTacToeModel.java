import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {

  // add your implementation here
  private Player[][] board;
  private int attempt;

  public TicTacToeModel() {
    this.board = new Player[3][3];
  }

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
    if (isGameOver()) {
      throw new IllegalStateException("Game over");
    }
    if (getMarkAt(r,c) != null) {
      throw new IllegalArgumentException("The space is occupied.");
    }
    board[r][c] = getTurn();
    attempt++;
  }

  @Override
  public Player getTurn() {
    if (isGameOver()) {return null;}
    return (attempt % 2 == 0) ? Player.X : Player.O;
  }

  @Override
  public boolean isGameOver() {
    // reach max attempt or someoneWin
    boolean hasWinner = horizontalWin() || verticalWin() || diagonalWin();
    return (attempt == 9)|| hasWinner;
  }

  @Override
  public Player getWinner() {
    if (horizontalWin() || verticalWin() || diagonalWin()) {
      return (attempt % 2 == 1) ? Player.X : Player.O;
    }
    return null;
  }

  @Override
  public Player[][] getBoard() {
    Player [][] clone = new Player[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        clone[r][c] = getMarkAt(r,c);
      }
    }
    return clone;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r > 2 || c < 0 || c > 2) {
      throw new IllegalArgumentException("Row or Column index must be 0, 1, or 2.");
    }

    return this.board[r][c];
  }

  public boolean horizontalWin() {
    for (int r = 0; r < 3; r++) {
      if (
          board[r][0] !=null &&
              board[r][0] == board[r][1] &&
              board[r][1] == board[r][2]) {
        return true;
      };
    }
    return false;
  }

  public boolean verticalWin() {
    for (int c = 0; c < 3; c++) {
      if (
          board[0][c] !=null &&
              board[0][c] == board[1][c] &&
              board[1][c] == board[2][c]) {
        return true;
      };
    }
    return false;
  }

  public boolean diagonalWin() {
    return board[1][1] != null && ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2] == board[1][1]
        && board[1][1] == board[2][0]));
  }
}

