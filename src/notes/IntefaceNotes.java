package notes;

public interface IntefaceNotes {

  /**
   * Places an X or O mark in the specified cell.
   * @param row the row position
   * @param col the column position
   * @throws IllegalArgumentException if the input position is invalid(occupied or out of boundary)
   * @throws IllegalStateException if the game is over
   */
  void move(int row, int col) throws IllegalArgumentException, IllegalStateException;
/*
  Tests:
  - test for valid row and col (position is empty)
  - test for boundary conditions
  - test invalid rol and col (including boundary conditions)
  - test moving into an occupied cell
  - test moving after isGameOver returns true
 */

  /**
   * Determine whose turn is.
   * @return Player X or Player O
   * @throws IllegalStateException
   */
  Player nextPlayer() throws IllegalStateException;
/*
Tests:
- test for nextPlayer returning X or O
- test for nextPlayer after isGameOver return true
*/

  boolean isGameOver();
/*
Tests:
- test for when this returns false
- boundary conditions for when this returns false
- test for when this returns true
 */

  Player getWinner();
/*
Tests:
- test for when winner is X, O, or the game is a tie.
 */

  Player getCell(int row, int col) throws IllegalArgumentException;
/*
Tests:
- test for valid values of row and col
- test for invalid value of row
- test for invalid value of col
 */

}

enum Player {
  X, O;
}

