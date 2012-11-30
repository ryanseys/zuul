package Builders;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import zuul.Room;

public abstract class AbstractBuilder extends JPanel implements ActionListener {

  private static final long serialVersionUID = 1L;
  protected ButtonBuilder b[];
  protected JButton done;
  protected Room r[];
  protected JPanel b9;
  protected boolean[] rooms;
  protected boolean isDone = false;

  public Room[] getRooms() {
    return r;
  }

  /**
   * @param type
   */
  public void initButtons(String type) {

    b9 = new JPanel();
    b9.setLayout(new FlowLayout());
    JTextArea f = new JTextArea("Now editing: " + type
        + "\nThis is the starting room.");
    f.setEditable(false);
    b9.add(f);
    done = new JButton();
    done.setText("Click here if done editing " + type);
    b9.add(done);

    done.addActionListener(this);

    b = new ButtonBuilder[16];
    for (int x = 0; x < 16; x++) {
      b[x] = new ButtonBuilder(x);
      b[x].addActionListener(this);
      if (x == 9) this.add(b9);
      else this.add(b[x]);
    }
  }

  /**
   * @param input
   */
  public void initRooms(boolean[] input) {
    rooms[9] = true; // always true, starting room

    for (int x = 0; x < 16; x++) {
      rooms[x] = input[x];
      if (rooms[x] == false) b[x].setEnabled(false);
    }
  }

  /**
   * @return
   */
  public boolean isDone() {
    return isDone;
  }

}
