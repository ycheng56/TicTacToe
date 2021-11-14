public interface IView {

  void showBoard(int i, Player p);

  void startGame();

  void addFeatures(IController features);

  void showInfo(String s);
}
