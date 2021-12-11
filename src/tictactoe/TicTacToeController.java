package tictactoe;

/**
 * This is a controller interface for TicTacToe Game.
 */
public interface TicTacToeController {

  /**
   * Create an empty board and clear all previous moves.
   */
  void playGame();

  /**
   * Make a move for given position
   *
   * @param r the row position
   * @param c the column position
   */
  void makeMove(int r, int c);
}
