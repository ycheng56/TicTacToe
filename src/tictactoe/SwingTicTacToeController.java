package tictactoe;

/**
 * This class represents a controller for the TicTacToe Game.
 */
public class SwingTicTacToeController implements TicTacToeController {

  private TicTacToe model;
  private TicTacToeView view;

  /**
   * Construct a TicTacToe Game controller, and initialize it with
   * Given TicTacToe model and GUI view.
   *
   * @param view the GUI view.
   * @param model the TicTacToe game model
   */
  public SwingTicTacToeController(TicTacToeView view,TicTacToe model) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
  }

  @Override
  public void playGame() {
    model.resetGame();
    view.startGame();
  }

  @Override
  public void makeMove(int r, int c) {
    if (!model.isGameOver()) {
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