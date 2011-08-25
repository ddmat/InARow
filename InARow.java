import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class InARow extends JFrame implements ActionListener {
  private final int inARow = 4;
  private final int X = 5;
  private final int Y = 5;

  private final String one = "X";
  private final String two = "O";


  JButton[][] grid;

  String player;
  boolean[] found;

  public static void main(String[]arg) {
    new InARow();
  }

  public InARow() {
    this.setLayout(new GridLayout(X, Y));
    grid = new JButton[X][Y];

    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[x].length; y++) {
        grid[x][y] = new JButton("-");
        grid[x][y].addActionListener(this);
        this.add(grid[x][y]);
      }
    }

    player = one;
    found = new boolean[inARow];
    setVisible(true);
    setSize(X * 100, Y * 100);
  }

  public void actionPerformed(ActionEvent ae) {
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[x].length; y++) {
        if (ae.getSource().equals(grid[x][y])) {
          grid[x][y].setEnabled(false);
          grid[x][y].setText(player);
          check();

          if (player.equals(one)) {
            player = two;
          } else {
            player = one;
          }
        }
      }
    }
  }

  private void check() {
    checkFor(player);
  }
  private void checkFor(String team) {
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[x].length; y++) {
        if (checkForAt(team, x, y)) {
          JOptionPane.showMessageDialog(null, "WIN FOR " + team);
        }
      }
    }
  }
  private boolean checkForAt(String team, int x, int y) {
    int inARowCount = 0;

    //diagonal check
    for (int i = 0; i < inARow; i++) {
      if (grid[x].length > (y + i)) {
        if (grid[x][y + i].getText().equals(team)) {
          inARowCount++;
        }
      }
    }

    if (inARowCount >= inARow) {
      return true;
    }

    //horizontal check
    inARowCount = 0;

    for (int i = 0; i < inARow; i++) {
      if (grid.length > (x + i)) {
        if (grid[x + i][y].getText().equals(team)) {
          inARowCount++;
        }
      }
    }

    if (inARowCount >= inARow) {
      return true;
    }

    //horizontal \ check
    inARowCount = 0;
    int tmpx = x;
    int tmpy = y;

    for (int i = 0; i < inARow; i++) {
      if (grid.length > tmpx && grid[x].length > tmpy) {
        if (grid[tmpx][tmpy].getText().equals(team)) {
          inARowCount++;
        }
      }

      tmpx++;
      tmpy++;
    }

    if (inARowCount >= inARow) {
      return true;
    }

    //horizontal / check
    inARowCount = 0;
    tmpx = x;
    tmpy = y;
    System.out.println("---");

    for (int i = 0; i < inARow; i++) {
      if (grid.length > tmpx && grid[x].length > tmpy && tmpx >= 0 && tmpy >= 0) {
        if (grid[tmpx][tmpy].getText().equals(team)) {
          inARowCount++;
          System.out.println("fond");
        }
      }

      tmpx--;
      tmpy++;
    }

    if (inARowCount >= inARow) {
      return true;
    }

    return false;
  }
}

