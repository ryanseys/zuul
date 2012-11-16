package zuul;
import java.io.IOException;

import View.ThreeDView;
import View.View;

 /**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * To play this game, invoke the static main method with no string
 * parameters.
 *
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author  Vinayak Bansal
 * @version 2012.10.22
 */

public class Game {

  private static Room startRoom;

  public static void main(String[] args) throws IOException {

  	initialize();
  	Player p = new Player (Player.MAX_HEALTH, startRoom, "Player");

  	View view;
    view = new ThreeDView(p);
  	view.update();
	view.setVisible(true);

  }


  /**
   * Initialization method, to set up the game.
   * Sets up the rooms, the monsters and the items in the game.
   */
  public static void initialize(){
	    startRoom = new Room("StartRoom");
	  	Room northRoom1 = new Room("NorthRoom1");
	  	northRoom1.addItem(new Item("Bread", 30, 0, false));
	  	Room southRoom = new Room("SouthRoom");
	  	Room eastRoom = new Room("EastRoom");
	  	Room westRoom = new Room("WestRoom");
	  	Room northWestRoom = new Room("NorthWestRoom");
	  	Room northRoom2 = new Room("NorthRoom2");
	  	startRoom.addItem(new Item("Sword", 50, 0, true));
	  	startRoom.setExit(Direction.NORTH, northRoom1);
	  	startRoom.setExit(Direction.SOUTH, southRoom);
	  	startRoom.setExit(Direction.EAST, eastRoom);
	  	startRoom.setExit(Direction.WEST, westRoom);
	  	westRoom.addItem(new Item("Apple", 10, 0, false));
	  	westRoom.addItem(new Item("Orange", 15, 0, false));
	  	westRoom.addItem(new Item("Pear", 20, 0, false));

	  	eastRoom.setExit(Direction.WEST, startRoom);

	  	Monster Monster1 = new Monster(Monster.MAX_HEALTH, Monster.DEFAULT_LEVEL, "Monster1", eastRoom);
	  	eastRoom.addMonster(Monster1);
	  	Monster1.addItem(new Item("Map", 0, 0, true));
	  	Monster1.addItem(new Item("Claws", 10, 0, true));

	  	Monster Boss = new Monster(100, 2, "Boss", southRoom);
	  	southRoom.addMonster(Boss);
	  	Boss.addItem(new Item("Flamethrower", 30, 0, true));
	  	Boss.addItem(new Item("Key", 0, 0, true));


	  	westRoom.setExit(Direction.EAST, startRoom);
	  	northRoom1.setExit(Direction.SOUTH, startRoom);
	  	northRoom1.setExit(Direction.NORTH, northRoom2);
	  	southRoom.setExit(Direction.NORTH, startRoom);

	  	northRoom2.setExit(Direction.SOUTH, northRoom1);
	  	northRoom2.setExit(Direction.WEST, northWestRoom);
	  	northWestRoom.setExit(Direction.EAST, northRoom2);
	  	northWestRoom.setLocked(true);
	  	northWestRoom.addItem(new Item("Treasure", 100, 0, true));
  }

}
