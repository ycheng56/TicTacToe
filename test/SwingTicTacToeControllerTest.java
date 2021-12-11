import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import tictactoe.SwingTicTacToeController;
import tictactoe.SwingTicTacToeView;
import tictactoe.TicTacToeModel;
import tictactoe.TicTacToeView;

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

  @Test
  public void testPlayGame() {
    controller.playGame();
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", model.toString());
  }

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


  }
}