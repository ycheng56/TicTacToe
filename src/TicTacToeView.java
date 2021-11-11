import java.io.PrintStream;


public class TicTacToeView implements IView {
  private final PrintStream out;

  public TicTacToeView(PrintStream out) {
    this.out = out;
  }

  @Override
  public void startGame() {
    out.println("Welcome to TicTacToe Game.");
    out.println("In each player's move, please enter the row number and the column number of 0,1, "
        + "or 2. For example: 2 1");
    out.println("Enter q to quit at any time.");
  }

  @Override
  public void showStringEntry(Player player) {
    out.println("Player " + player + "'s turn.");
    out.print("Enter the position: ");
  }

  @Override
  public void showBoard(String s) {
    out.println(s);
    out.println();
  }

  @Override
  public void showWinner(Player player) {
    if (player != null) {
      out.println(player + " Wins!");
    }
    else {
      out.println("Draw!");
    }
  }

  @Override
  public void restartGame() {
    out.println();
    out.println("Enter y to start a new game.");
    out.println("Enter q to quit.");
  }

  @Override
  public void illegalMove() {
    out.println("This position is invalid. Please re-enter the position.");
  }

  @Override
  public void endGame() {
    out.println("Bye.");
  }
}
