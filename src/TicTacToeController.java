import java.io.InputStream;
import java.util.Scanner;

public class TicTacToeController implements IController {

  private TicTacToe model;
  private IView view;

  public TicTacToeController(TicTacToe model) {
    this.model = model;
  }

  public void setView(IView view) {
    this.view = view;
    view.addFeatures(this);
  }

  @Override
  public void start() {
    model = new TicTacToeModel();
    view.startGame();
  }

  @Override
  public void makeMove(int r, int c) {
    if (model.isGameOver()) {
      //view.showInfo("Game Over. Please start a new game.");
    } else {
      try {
        Player p = model.getTurn();
        int idx = r * 3 + c;
        model.move(r, c);
        view.showBoard(idx, p);
        if (model.isGameOver()) {
          if (model.getWinner() == null) {
            view.showInfo("Draw!");
          } else {
            view.showInfo("Player " + model.getWinner() + " Wins!");
          }
        } else {
          view.showInfo("Player " + model.getTurn() + "'s turn.");
        }
      } catch (Exception e) {
        view.showInfo("This position is occupied.");
      }
    }
  }
}