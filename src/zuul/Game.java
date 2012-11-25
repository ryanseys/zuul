package zuul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Builders.*;
import View.View;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting! To play this game, invoke the static main method with no string
 * parameters. This main class creates and initialises all the others: it
 * creates all rooms, creates the parser and starts the game. It also evaluates
 * and executes the commands that the parser returns.
 * 
 * @author Vinayak Bansal
 * @version 2012.10.22
 * @param <ItemBuilder>
 * @param <ItemBuilder>
 */

public class Game {

  public static final String KEY = "Key";
  public static final String FLAMETHROWER = "Flamethrower";
  public static final String BOSS2 = "Boss";
  public static final String CLAWS = "Claws";
  public static final String MAP = "Map";
  public static final String ALIEN2 = "Alien";
  public static final String PEAR = "Pear";
  public static final String ORANGE = "Orange";
  public static final String APPLE = "Apple";
  public static final String SWORD = "Sword";
  public static final String NORTH_ROOM2 = "NorthRoom2";
  public static final String NORTH_WEST_ROOM = "NorthWestRoom";
  public static final String WEST_ROOM = "WestRoom";
  public static final String EAST_ROOM = "EastRoom";
  public static final String SOUTH_ROOM = "SouthRoom";
  public static final String BREAD = "Bread";
  public static final String NORTH_ROOM1 = "NorthRoom1";
  public static final String START_ROOM = "StartRoom";
  public ItemBuilder itemBuilder;
  public MonsterBuilder monsterBuilder;
  public RoomBuilder roomBuilder;
  

  /**
   * Initialization method, to set up the game. Sets up the rooms, the monsters
   * and the items in the game.
   * @param <ItemBuilder>
   * @param <MapBuilder>
   * @param <RoomBuilder>
   */
  public static Room initialize(ItemBuilder ib, MonsterBuilder mb, RoomBuilder rb) {
    
    Room[] rooms = ib.getRooms();
    boolean[] roomStatuses = rb.getRooms();
    Room[] monsterRooms = mb.getRooms();
    
    Room startRoom = null;
    Room currentRoom = null;
    for(int i = 0; i < 16; i++) {
      if(roomStatuses[i] == true) {
        currentRoom = rooms[i];
        if(monsterRooms[i].hasMonsters()) currentRoom.addMonster(monsterRooms[i].getMonster());
        if(hasNeighbour(i, Direction.NORTH) && roomStatuses[i-4] == true) currentRoom.setExit(Direction.NORTH, rooms[i-4]);
        if(hasNeighbour(i, Direction.EAST) && roomStatuses[i+1] == true) currentRoom.setExit(Direction.EAST, rooms[i+1]);
        if(hasNeighbour(i, Direction.SOUTH) && roomStatuses[i+4] == true) currentRoom.setExit(Direction.SOUTH, rooms[i+4]);
        if(hasNeighbour(i, Direction.WEST) && roomStatuses[i-1] == true) currentRoom.setExit(Direction.WEST, rooms[i-1]);
      }
      if(i == 9) startRoom = currentRoom;
    }
    return startRoom;
  }
  
  public static boolean hasNeighbour(int id, Direction d) {
    switch(id) {
      case 0:
        if(d.equals(Direction.NORTH)) return false;
        if(d.equals(Direction.WEST)) return false;
        return true;
      case 1:
        if(d.equals(Direction.NORTH)) return false;
        return true;
      case 2:
        if(d.equals(Direction.NORTH)) return false;
        return true;
      case 3:
        if(d.equals(Direction.NORTH)) return false;
        if(d.equals(Direction.EAST)) return false;
        return true;
      case 4:
        if(d.equals(Direction.WEST)) return false;
        return true;
      case 5:
        return true;
      case 6:
        return true;
      case 7:
        if(d.equals(Direction.EAST)) return false;
        return true;
      case 8:
        if(d.equals(Direction.WEST)) return false;
        return true;
      case 9:
        return true;
      case 10:
        return true;
      case 11:
        if(d.equals(Direction.EAST)) return false;
        return true;
      case 12:
        if(d.equals(Direction.SOUTH)) return false;
        if(d.equals(Direction.WEST)) return false;
        return true;
      case 13:
        if(d.equals(Direction.SOUTH)) return false;
        return true;
      case 14:
        if(d.equals(Direction.SOUTH)) return false;
        return true;
      case 15:
        if(d.equals(Direction.EAST)) return false;
        if(d.equals(Direction.SOUTH)) return false;
        return true;
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
	  View view = View.getInstance(null, null, null);
    view.update();
    view.setVisible(true);
  }

}
