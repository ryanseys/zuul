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
   * It initializes the panel, and sets the text on it 
   * according to the which stage you are in.
   * 
   * @param type: Which stage of the builder are you in?
   * The 'Room', 'Monster' and 'Item' stage?
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
   * This method sets one of its state variables to
   * according to the input.
   * 
   * Also does an error check to ensure that the starting room is always
   * set to true
   * @param input: An array of 16 booleans where a true indicates
   * that there is a room present at that location in the grid
   */
  public void initRooms(boolean[] input) {
    rooms[9] = true; // always true, starting room

    for (int x = 0; x < 16; x++) {
      rooms[x] = input[x];
      if (rooms[x] == false) b[x].setEnabled(false);
    }
  }

  /**
   * @return if the current stage of the builder has been done
   * yet or not
   */
  public boolean isDone() {
    return isDone;
  }

}
