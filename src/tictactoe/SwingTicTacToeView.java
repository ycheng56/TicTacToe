package tictactoe;

import javax.swing.*;
import java.awt.*;

/**
 * This is a GUI view for the TicTacToe Game.
 */
public class SwingTicTacToeView extends JFrame implements TicTacToeView {

  private JLabel infoLabel;
  private JPanel gameArea;
  private JButton[] cells;
  private JButton startButton;

  /**
   * Construct a TicTacToe GUI View.
   *
   * @param caption the GUI caption
   */
  public SwingTicTacToeView(String caption) {
    super(caption);

    setSize(500, 500);
    setLocation(400, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    gameArea = new JPanel();
    gameArea.setLayout(new FlowLayout());
    gameArea.add(addButton());

    infoLabel = new JLabel("Welcome to TicTacToe Game! Player X's turn.");
    infoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    startButton = new JButton("Start New Game");

    startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    gameArea.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.add(startButton);
    this.add(infoLabel);
    this.add(gameArea);

    pack();
    setVisible(true);

  }

  /**
   * Add 9 buttons to the board, each button represent
   * a slot on the board
   * @return the board panel
   */
  private JPanel addButton() {
    // initialize a panel to store the buttons
    JPanel panel = new JPanel(new GridLayout(3, 3));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    cells = new JButton[9];
    // assign buttons to the list
    for (int i = 0; i < cells.length; i++) {
      cells[i] = new JButton(" ");
      cells[i].setFont(new Font("Arial", Font.PLAIN, 130));
      cells[i].setPreferredSize(new Dimension(150, 150));
      cells[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      panel.add(cells[i]);
    }
    return panel;
  }


  @Override
  public void showBoard(int i, Player p) {
    cells[i].setText(p.toString());
  }

  @Override
  public void startGame() {
    for (JButton cell : cells) {
      cell.setText(" ");
    }
    infoLabel.setText("Player X's turn.");
  }

  @Override
  public void addFeatures(TicTacToeController features) {
    startButton.addActionListener(evt -> features.playGame());
    for (int i = 0; i < cells.length; i++) {
      int row = i / 3;
      int col = i % 3;
      cells[i].addActionListener(evt -> features.makeMove(row, col));
    }
  }

  @Override
  public void showInfo(String s) {
    infoLabel.setText(s);
  }
}
