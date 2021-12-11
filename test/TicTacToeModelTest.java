import java.util.Arrays;
import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the tic-tac-toe model. Verifying that game state is properly managed, and all game
 * actions are properly validated.
 */
public class TicTacToeModelTest {

  private TicTacToe ttt1 = new TicTacToeModel();

  /**
   * Test the move() method
   */
  @Test
  public void testMove() {
    // Move 1: X takes (0,0)
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));

    // Move 2: O takes (0,1)
    ttt1.move(0, 1);
    assertEquals(Player.X, ttt1.getTurn());
    assertEquals(Player.O, ttt1.getMarkAt(0, 1));

    // Move 3: X takes (0,2)
    ttt1.move(0, 2);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 2));

    // Move 4: O takes (1,0)
    ttt1.move(1, 0);
    assertEquals(Player.X, ttt1.getTurn());
    assertEquals(Player.O, ttt1.getMarkAt(1, 0));
  }

  /**
   * Test the move() method, make a move for the occupied position. Exception is expected.
   */
  @Test
  public void testInvalidMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    try {
      ttt1.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      ttt1.move(-1, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  /**
   * Test the move() method, make a move after the game is over Exception is expected.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    diagonalWinHelper();
    ttt1.move(2, 2); // 2,2 is an empty position
  }

  /**
   * Test different case of win Case 1: Horizontal Win
   */
  @Test
  public void testHorizontalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | X | X\n"
        + "-----------\n"
        + " O |   |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }

  /**
   * Test different case of win Case 2: Vertical Win
   */
  @Test
  public void testVerticalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(0, 1); // O takes upper middle
    ttt1.move(1, 0); // X takes left middle
    assertNull(ttt1.getWinner());
    ttt1.move(0, 2); // O takes upper right
    ttt1.move(2, 0); // X takes lower left
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | O | O\n"
        + "-----------\n"
        + " X |   |  \n"
        + "-----------\n"
        + " X |   |  ", ttt1.toString());
  }

  /**
   * Test different case of win Case 3: Diagonal win
   */
  @Test
  public void testDiagonalWin() {
    diagonalWinHelper();
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.O, ttt1.getWinner());
    assertEquals(" X | X | O\n"
        + "-----------\n"
        + " X | O |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }

  // set up situation where game is over, O wins on the diagonal, board is not full
  private void diagonalWinHelper() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 0); // X takes middle left
    assertNull(ttt1.getWinner());
    ttt1.move(1, 1); // O takes center
    ttt1.move(0, 1); // X takes upper middle
    ttt1.move(0, 2); // O takes upper right
  }

  @Test
  public void testCatsGame() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(1, 1);
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 2);
    ttt1.move(0, 1);
    ttt1.move(2, 1);
    ttt1.move(1, 0);
    ttt1.move(1, 2);
    ttt1.move(2, 2);
    ttt1.move(2, 0);
    assertTrue(ttt1.isGameOver());
    assertNull(ttt1.getWinner());
    assertEquals(" X | O | X\n"
        + "-----------\n"
        + " O | O | X\n"
        + "-----------\n"
        + " X | X | O", ttt1.toString());
  }

  /**
   * Test the getMarkAt method()
   */
  @Test
  public void testGetMarkAt() {
    ttt1.move(0, 0);
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    ttt1.move(0, 1);
    assertEquals(Player.O, ttt1.getMarkAt(0, 1));
    assertNull(ttt1.getMarkAt(0, 2));
  }

  /**
   * Test the getMarkAt() method Exception is expected
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    ttt1.getMarkAt(-12, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    ttt1.getMarkAt(0, -30);
  }

  /**
   * test getBoard() method
   */
  @Test
  public void testGetBoard() {
    diagonalWinHelper();
    Player[][] bd = ttt1.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][1]);
    assertEquals(Player.X, bd[0][1]);

    // attempt to cheat by mutating board returned by getBoard()
    // check correct preconditions
    assertEquals(Player.O, bd[2][0]);
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    bd[2][0] = Player.X;  // mutate
    // check correct post conditions
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    Player[][] bd2 = ttt1.getBoard();
    assertEquals(Player.O, bd2[2][0]);
  }

  @Test
  public void testXIsWinner() {
    ttt1.move(0, 0);
    ttt1.move(0, 1);
    ttt1.move(0, 2);
    ttt1.move(1, 0);
    ttt1.move(1, 1);
    ttt1.move(2, 0);
    ttt1.move(2, 1);
    ttt1.move(1, 2);
    ttt1.move(2, 2);
    assertTrue(ttt1.isGameOver());
    assertNull(ttt1.getTurn()); // return null when the game is over
    int full_cell = Arrays.stream(ttt1.getBoard()).mapToInt(
        row -> Arrays.stream(row).mapToInt(
            p -> p == null ? 0 : 1).sum()).sum();
    assertEquals(9, full_cell);
    assertEquals(Player.X, ttt1.getWinner());
  }

  /**
   * test resetGame() method
   * after reset the model, the board should be empty
   */
  @Test
  public void testResetGame() {
    ttt1.move(0, 0);
    assertEquals(" X |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", ttt1.toString());

    ttt1.resetGame();
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", ttt1.toString());
  }
}
