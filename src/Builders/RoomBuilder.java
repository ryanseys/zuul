package Builders;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import zuul.Room;

public class RoomBuilder extends AbstractBuilder implements ActionListener {

  private static final long serialVersionUID = 1L;
  MapBuilder mb;
  boolean isDone = false;

  /**
   * Invoked when the user chooses to build his own game
   */
  public RoomBuilder() {
    setLayout(new GridLayout(4, 4));
    initButtons("Rooms");
    rooms = new boolean[16];
    initRooms();
    mb = new MapBuilder(rooms);
  }

  /**
   * Invoked when the user chooses to play the default version
   * @param defaultRooms: not used, but necessary for overloading
   */
  public RoomBuilder(int defaultRooms) {
    rooms = new boolean[16];
    initRooms();
    setDefaultRooms();
    mb = new MapBuilder(rooms);
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == done) {
      MonsterBuilder monBuil = new MonsterBuilder(rooms);

      isDone = true;
      Room[] roomsArray = monBuil.getRooms();
      if (roomsArray[0].hasMonsters()) System.out.println("ROOM HAS MONSTERS!");

    }

    else {
      int loc = ((ButtonBuilder) e.getSource()).getLoc();
      if (b[loc].getText() == "") {
        b[loc].setText("Room Selected");
        rooms[loc] = true;
      } else {
        b[loc].setText("");
        rooms[loc] = false;
      }
    }

    mb = new MapBuilder(rooms);

  }

  /**
   * getter for one of the state variables
   */
  public boolean[] getBooleanRooms() {
    return rooms;
  }

  /**
   *getter for one of the state variables
   */
  public MapBuilder getMapBuilder() {
    return mb;
  }

  /**
   * Helper for the constructor
   */
  private void initRooms() {
    for (int x = 0; x < 16; x++)
      rooms[x] = false;
    rooms[9] = true; // always true, starting room
  }

  /**
   *  Helper for the constructor
   */
  private void setDefaultRooms() {
    initRooms();
    rooms[0] = true;
    rooms[1] = true;
    rooms[5] = true;
    rooms[8] = true;
    rooms[10] = true;
    rooms[13] = true;
  }
}
