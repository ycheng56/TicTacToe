public class GameStarter {

  public static void main(String[] args) {
    TicTacToeModel model = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeController(model);
    IView view = new JFrameView("Tic Tac Toe");
    controller.setView(view);
  }
}
