public interface IView {

  String getInputString();

  void clearInputString();

  void showBoard(String s);

  void startGame();

  void addFeatures(IController features);

  void showInfo(String s);
}
