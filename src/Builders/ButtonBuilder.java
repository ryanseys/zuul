package Builders;

/**
 * This class is nothing but a button with an int location
 * on the grid
 */
import javax.swing.JButton;

/**
 *
 */
@SuppressWarnings("serial")
public class ButtonBuilder extends JButton {
  private int location;

  /**
   * @param location
   */
  public ButtonBuilder(int location) {
    assert (location >= 0);
    assert (location <= 15);
    this.location = location;
  }

  /**
   * getter for the extra state variable
   */
  public int getLoc() {
    return location;
  }

}
