package zuul;

import java.io.Serializable;

/**
 * Item class. This class deals with the individual items. Items are given a
 * name, a value, and a weight. The name is simply the name of the item. The
 * value of the item is used for comparing different items. For weapons, this
 * value will decide which item is stronger. For food, this value will decide
 * how much the health of the player will increment. The weight of the item will
 * possibly later be used to have an item limit for the player. The isWeapon
 * boolean refers to whether or not the item is a weapon Not all setters and
 * getters are used at this point, but will probably be useful for later
 * milestones.
 * 
 * @author Jarred Linthorne-Shaw
 * @version 2012.10.23
 */

public class Item implements Comparable<Item>, Serializable {

  /**
   * Serial version UID
   */
  private static final long serialVersionUID = 1L;
	private String name;
	private int value;
	private int weight;
	private boolean isWeapon;

	/**
	 * Default constructor. Creates an item with a name with no value or weight
	 * 
	 * @param name
	 *            : The name of the item.
	 * @param isWeapon
	 *            : Whether or not it is a weapon
	 */
	public Item(String name, boolean isWeapon) {
		this(name, 0, 0, isWeapon);
	}

	/**
	 * Constructor for the item.
	 * 
	 * @param name
	 *            : The name of the item being created.
	 * @param value
	 *            : The value of the item.
	 * @param weight
	 *            : The weight of the item.
	 * @param isWeapon
	 *            : Whether or not it is a weapon
	 */
	public Item(String name, int value, int weight, boolean isWeapon) {
		// Constructor assigning the variables
		this.name = name.toUpperCase();
		this.value = value;
		this.weight = weight;
		this.isWeapon = isWeapon;
	}

	/**
	 * Overriding the compareTo method in Comparable.
	 * 
	 * @param i
	 *            : The passed item to compare.
	 * @return : This method returns 1 if the value of this item is greater than
	 *         the one passed. It returns 0 if the items have the same value. It
	 *         return -1 if the passed item is greater than this item.
	 */
	@Override
	public int compareTo(Item i) {
		if (value > i.value)
			return 1; // this item is of greater value than the
						// parameter item
		else if (value == i.value)
			return 0; // the items have the same value (tie)
		// otherwise, this.value < i.value
		return -1; // this item is of less value than the parameter item
	}

	/**
	 * Overriding the equals method, to check if items are the same.
	 * 
	 * @param o
	 *            : An object passed to check for equality
	 * @return : Returns a boolean whether or not the items are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Item)
			return (name.equals(((Item) o).getName()));
		return false;
	}

	/**
	 * Get the description of the item. Either add a or an to the string
	 * depending on if it is a consonant or not.
	 * 
	 * @return : The string giving the required information for inspection of an
	 *         item.
	 */
	public String getDescription() {
		String s = "This is ";
		if (name.startsWith("A") || name.startsWith("E")
				|| name.startsWith("I") || name.startsWith("O")
				|| name.startsWith("U"))
			s += "an ";
		else
			s += "a ";
		s += name.toLowerCase() + ".\n";
		if (isWeapon())
			s += "It can do " + value + " damage when used to fight.";
		else
			s += "It can recover " + value + " health when eaten.";

		return s;
	}

	/**
	 * Getter for the name.
	 * 
	 * @return : The name of the item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the value.
	 * 
	 * @return : The value of the item.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Getter for the weight.
	 * 
	 * @return : The weight of the item.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Query for the item.
	 * 
	 * @return : Whether is not the item is a weapon.
	 */
	public boolean isWeapon() {
		return isWeapon;
	}

	/**
	 * Setter for the name.
	 * 
	 * @param name
	 *            : The new name of the item.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for the value.
	 * 
	 * @param value
	 *            : The new value of the item.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Setter for the weight.
	 * 
	 * @param weight
	 *            : The new weight of the item.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * The toString method for an item which just returns it's name.
	 * 
	 * @return : The name of the item.
	 */
	@Override
	public String toString() {
		return name;
	}
}
