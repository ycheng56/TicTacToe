import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class JFrameView extends JFrame implements IView {
  private JLabel infoLabel;
  private JLabel gameArea;
  private JButton startButton, enterButton;
  private JTextField input;

  public JFrameView(String caption) {
    super(caption);

    setSize(800,500);
    setLocation(200,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


    gameArea = new JLabel("",SwingConstants.CENTER);
    gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    gameArea.setFont(new Font("Monospace", Font.PLAIN, 60));
    infoLabel = new JLabel("Welcome to TicTacToe Game! In each player's move, please enter the "
        + "row number and the column number of 0,1, or 2.");
    input = new JTextField(2);
    startButton = new JButton("Start New Game");
    enterButton = new JButton("Enter");

    startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    gameArea.setAlignmentX(Component.CENTER_ALIGNMENT);

    infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    input.setAlignmentX(Component.CENTER_ALIGNMENT);
    enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(startButton);
    this.add(gameArea);
    this.add(infoLabel);
    this.add(input);
    this.add(enterButton);


    //pack();
    setVisible(true);

  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

  @Override
  public void showBoard(String s) {
    gameArea.setText("<html><tt>" + s.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n"
        , "<br/>").replaceAll(" ","&nbsp;") + "<tt></html>");
  }

  @Override
  public void startGame() {
    infoLabel.setText("Player X's turn. Please enter the position");
  }

  @Override
  public void addFeatures(IController features) {
    startButton.addActionListener(evt ->features.start());
    enterButton.addActionListener(evt ->features.makeMove());
    input.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
          features.makeMove();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
  }

  @Override
  public void showInfo(String s) {
    infoLabel.setText(s);
  }
}
