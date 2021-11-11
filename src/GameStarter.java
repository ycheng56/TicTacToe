public class GameStarter {

  public static void main(String[] args) {
    TicTacToeModel model = new TicTacToeModel();
    IView view = new TicTacToeView(System.out);
    IController controller = new TicTacToeController(model,System.in,view);
    controller.go();
  }
}
