package Builders;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import zuul.Humanoid;
import zuul.Item;
import zuul.Monster;
import zuul.Room;

public class MonsterBuilder extends AbstractBuilder implements ActionListener {

  private static final long serialVersionUID = 1L;

  MapBuilder mb;
  Monster alien;
  Monster boss;
  ArrayList<Monster> mArray = new ArrayList<Monster>();

  /**
   * @param b the boolean array of where the rooms are
   */
  public MonsterBuilder(boolean[] b) {
    setLayout(new GridLayout(4, 4));
    initButtons("Monsters");
    rooms = new boolean[16];
    initRooms(b);
    initMonsters();
  }

  /**
   * Invoked when the user wants to play the default option
   * @param defaultRooms: not used but necessary for overloading
   */
  public MonsterBuilder(int defaultRooms) {
    initRealRooms();
    setDefaultMonsters();
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO different option dialog for no monsters
    if (e.getSource() == done) {
      setVisible(false);
      isDone = true;
    } else {
      int loc = ((ButtonBuilder) e.getSource()).getLoc();
      int popup = JOptionPane.showOptionDialog(this,
          "Select a Monster to put in this room:", "Selected Room",
          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
          null, mArray.toArray(), null);
      if ((popup == 0) || (popup == 1)) {
        if (mArray.get(popup).equals(alien)) {
          r[loc].addMonster(alien);
          alien.setCurrentRoom(r[loc]);
          mArray.remove(alien);
        } else {
          r[loc].addMonster(boss);
          boss.setCurrentRoom(r[loc]);
          mArray.remove(boss);
        }
        b[loc].setEnabled(false);
        b[loc]
            .setText("" + getRooms()[loc].getMonster().getName() + " Placed!");
      }
    }

  }

  /* (non-Javadoc)
   * @see Builders.AbstractBuilder#initButtons(java.lang.String)
   */
  @Override
  public void initButtons(String type) {
    super.initButtons(type);
    JTextArea f2 = new JTextArea(
        "Note: Must add Alien to have Map in game.\n           Must add Boss to have Key in game.");
    f2.setEditable(false);
    b9.add(f2);
  }

  /**
   * Initializing the two state variables - alien and boss
   * Putting them in a temp room
   */
  public void initMonsters() {
    Room temp = new Room("Temp room");
    alien = new Monster(Humanoid.MAX_HEALTH, Monster.DEFAULT_LEVEL, "Alien",
        null);
    alien.addItem(new Item("Map", 0, 0, true));
    alien.addItem(new Item("Claws", 10, 0, true));
    alien.setCurrentRoom(temp);
    boss = new Monster(100, 2, "Boss", null);
    boss.addItem(new Item("Flamethrower", 30, 0, true));
    boss.addItem(new Item("Key", 0, 0, true));
    boss.setCurrentRoom(temp);
    mArray.add(alien);
    mArray.add(boss);
  }

  /**
   * MonsterBuilder is the place where the real rooms get constructed
   * Till now we were working with an array of booleans
   */
  private void initRealRooms() {
    r = new Room[16];
    for (int x = 0; x < 16; x++)
      r[x] = new Room(x + "");
  }

  /* (non-Javadoc)
   * @see Builders.AbstractBuilder#initRooms(boolean[])
   */
  @Override
  public void initRooms(boolean[] input) {
    super.initRooms(input);
    initRealRooms();
  }

  /**
   * 
   */
  private void setDefaultMonsters() {
    initMonsters();
    r[10].addMonster(alien);
    alien.setCurrentRoom(r[10]);
    r[13].addMonster(boss);
    boss.setCurrentRoom(r[13]);
  }
}
