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
    view.showBoard(model.toString());
  }

  @Override
  public void start() {
    model = new TicTacToeModel();
    view.showBoard(model.toString());
    view.startGame();
    view.clearInputString();
  }

  @Override
  public void makeMove() {
    String input = view.getInputString();
    try {
      String[] inputList = input.split(" ");
      int row = Integer.parseInt(inputList[0]);
      int col = Integer.parseInt(inputList[1]);
      model.move(row,col);
      view.showBoard(model.toString());
      view.clearInputString();
      if (model.isGameOver()) {
        view.showInfo("Player " + model.getWinner() + " Wins!");
      }
      else {
        view.showInfo("Player " + model.getTurn() + "'s turn.Please enter the position:");
      }
    }
    catch (Exception e) {
      view.showBoard(model.toString());
      view.clearInputString();
      view.showInfo("This position is invalid. Please re-enter the position:");
    }
  }
}