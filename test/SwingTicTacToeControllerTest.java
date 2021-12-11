import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Player;
import tictactoe.SwingTicTacToeController;
import tictactoe.SwingTicTacToeView;
import tictactoe.TicTacToeModel;
import tictactoe.TicTacToeView;

/**
 * Junit test for TicTacToe Controller
 */
public class SwingTicTacToeControllerTest {

  private SwingTicTacToeController controller;
  private TicTacToeModel model;
  private TicTacToeView view;

  @Before
  public void setUp() {
    model = new TicTacToeModel();
    view = new SwingTicTacToeView("Tictactoe");
    controller = new SwingTicTacToeController(view, model);
  }

  /**
   * Test the playGame() method call the playGame() method to play a new TicTacToe Game
   */
  @Test
  public void testPlayGame() {
    // start a game
    controller.playGame();
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", model.toString());
    controller.makeMove(0, 0);
    assertEquals(" X |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", model.toString());

    // discard the game and start a new game
    controller.playGame();
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", model.toString());

  }

  /**
   * Test the makeMove() method.
   * X is winner.
   */
  @Test
  public void testMakeMove() {
    controller.makeMove(0, 0);
    controller.makeMove(0, 1);
    controller.makeMove(0, 2);
    controller.makeMove(1, 0);
    controller.makeMove(1, 1);
    controller.makeMove(2, 0);
    controller.makeMove(2, 1);
    controller.makeMove(1, 2);
    controller.makeMove(2, 2);
    assertEquals(" X | O | X\n"
        + "-----------\n"
        + " O | X | O\n"
        + "-----------\n"
        + " O | X | X", model.toString());
    assertEquals(Player.X, model.getWinner());
  }
}