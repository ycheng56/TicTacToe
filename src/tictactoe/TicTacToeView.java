package tictactoe;

/**
 * This is a GUI View Interface for TicTacToe Game.
 */
public interface TicTacToeView {

  /**
   * Show the whole game board
   * @param i the index of the cell
   * @param p Player
   */
  void showBoard(int i, Player p);

  /**
   * Start a new game
   */
  void startGame();

  /**
   * Set the controller as listener.
   * @param features the listener
   */
  void addFeatures(TicTacToeController features);

  /**
   * Display game information on the view
   * @param s the information will be displayed
   */
  void showInfo(String s);
}
