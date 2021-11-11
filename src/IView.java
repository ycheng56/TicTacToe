public interface IView {
  void startGame();

  void showStringEntry(Player player);

  void showBoard(String s);

  void showWinner(Player player);

  void restartGame();

  void illegalMove();

  void endGame();
}
