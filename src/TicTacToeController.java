import java.io.InputStream;
import java.util.Scanner;

public class TicTacToeController implements IController {
  private TicTacToe model;
  private Scanner in;
  private IView view;

  public TicTacToeController(TicTacToe model, InputStream in, IView view) {
    this.model = model;
    this.in = new Scanner(in);
    this.view = view;
  }

  @Override
  public void go() {
    view.startGame();
    boolean quit = false;
    while (!quit) {
      model = new TicTacToeModel();
      view.showBoard(model.toString());

      while (!model.isGameOver()) {
        makeMove();
        view.showBoard(model.toString());
      }

      view.showWinner(model.getWinner());
      quit = restart();
    }
    view.endGame();
  }

  @Override
  public void makeMove() {
    while (true) {
      view.showStringEntry(model.getTurn());

      String rowString = in.next();
      if (rowString.equals("q")) {
        view.endGame();
        System.exit(0);
      }

      String colString = in.next();
      if (colString.equals("q")) {
        view.endGame();
        System.exit(0);
      }

      try {
        int row = Integer.parseInt(rowString);
        int col = Integer.parseInt(colString);
        model.move(row, col);
        break;
      }
      catch (Exception e) {
        //e.printStackTrace();
        view.illegalMove();
      }
    }
  }

  @Override
  public boolean restart() {
    while (true) {
      view.restartGame();
      String option = in.next();
      if (option.toLowerCase().equals("q")) {
        return true;
      } else if (option.toLowerCase().equals("y")) {
        return false;
      }
    }
  }
}