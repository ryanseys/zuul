import java.util.ArrayList;

/**
 * Monster Class.
 * This class is in charge of the antagonists in Zuul.
 * The level of the monster refers to how tough the monster is.
 * This level will act as a sort of multiplier with the items that the monster has in order to calculate damage to player.
 * Everything else is handled in the super class.
 *
 * For now, monsters are passive and will only attack a player if the player chooses to fight them.
 *
 * @author  Jarred Linthorne-Shaw
 * @version 2012.10.23
 */

public class Monster extends Humanoid {

	private final int level;
	public static final int DEFAULT_LEVEL = 1;
	private Room currentRoom;

	/**
	 * The Constructor for the monster.
	 * Sets the level of the monster.
	 * @param health : The base health of the monster
	 * @param level : The strength of the monster
	 * @param name : The name of the monster
	 */
	public Monster(int health, int level, String name, Room room){
		super(health, name);
		this.level = level;
		currentRoom = room;
	}

	/**
	 * The default Constructor for the monster.
	 * Sets the monsters level to the default level.
	 */
	public Monster(Room room){
		super();
		this.level = DEFAULT_LEVEL;
		currentRoom = room;
	}

	/**
	 * This method compares this monster to the object passed.
	 * @param o : The object passed into the method.
	 */
	public boolean equals(Object o){
		if(o instanceof Monster){
			return (this.level == ((Monster) o).level  && super.equals(o));
		}
		return false;
	}

	/**
	 * This method drops all of the items that the monster is carrying into the room it is in.
	 */
	public void dropItems(){
		ArrayList<Item> inventory = this.getInventory();
		for(Item i: inventory){
			currentRoom.addItem(i);
		}
	}

	/**
	 * Getter for the monster's level.
	 * @return The level of the monster.
	 */
	public int getLevel(){
		return level;
	}
}
