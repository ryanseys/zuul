package Builders;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import zuul.Item;
import zuul.Room;

public class ItemBuilder extends AbstractBuilder implements ActionListener {

  private static final long serialVersionUID = 1L;

  private Item bread, sword, apple, orange, pear, treasure;

  ArrayList<Item> iArray = new ArrayList<Item>();

  /**
   * Invoked when the user chooses to build his own game
   * @param b
   * @param r
   */
  public ItemBuilder(boolean[] b, Room[] r) {
    setLayout(new GridLayout(4, 4));
    initButtons("Items");
    rooms = new boolean[16];
    initRooms(b);
    initItems();
    this.r = r;
  }

  /**
   * Invoked when the user wants to play the default version
   * @param r
   */
  public ItemBuilder(Room[] r) {
	  this.r = r;
    setDefaultItems();
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == done) {
      setVisible(false);
      isDone = true;
    } else {
      int loc = ((ButtonBuilder) e.getSource()).getLoc();
      int popup = JOptionPane.showOptionDialog(this,
          "Select an Item to put in this room:", "Selected Room",
          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
          null, iArray.toArray(), null);
      if ((popup == 0) || (popup == 1) || (popup == 2) || (popup == 3)
          || (popup == 4) || (popup == 5)) {
      }
      if (iArray.get(popup).equals(treasure)) {
        r[loc].addItem(treasure);
        iArray.remove(treasure);
        b[loc].setEnabled(false);
      } else r[loc].addItem(iArray.get(popup));
      String abc = loc == 9 ? "Start Room: " : "";
      b[loc].setText(abc + getRooms()[loc].getItems());

    }
  }

  /* (non-Javadoc)
   * @see Builders.AbstractBuilder#initButtons(java.lang.String)
   */
  @Override
  public void initButtons(String type) {
    super.initButtons(type);
    b[9].setText("Click here to add Items to Start Room");
    b9.add(b[9]);
    JTextArea f = new JTextArea(
        "Note: Treasure must be added in order to win game.");
    f.setEditable(false);
    b9.add(f);
  }

  /**
   * Helper to set up some of the internal state variables
   */
  private void initItems() {
    bread = new Item("Bread", 30, 0, false);
    sword = new Item("Sword", 50, 0, true);
    apple = new Item("Apple", 10, 0, false);
    orange = new Item("Orange", 15, 0, false);
    pear = new Item("Pear", 20, 0, false);
    treasure = new Item("Treasure", 100, 0, true);

    iArray.add(bread);
    iArray.add(sword);
    iArray.add(apple);
    iArray.add(orange);
    iArray.add(pear);
    iArray.add(treasure);
  }

  /**
   * In case the user chooses to play the default version of the game,
   * we invoke this method
   */
  private void setDefaultItems() {
    initItems();
    r[0].addItem(treasure);
    r[5].addItem(bread);
    r[8].addItem(orange);
    r[8].addItem(apple);
    r[8].addItem(pear);
    r[9].addItem(sword);
  }

}
