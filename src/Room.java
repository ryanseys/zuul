import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

/**
 * Room class for representing a room that a Player can be in.
 * The room can also contain items and monsters.
 * The room also has a specific set of exits which lead to another
 * room as well as have a specific direction.
 *
 * @author Ryan Seys
 * @version 2012.08.23
 */

public class Room
{
  private String description;
  private List<Item> items;
  private List<Monster> monsters;
  private Map<Direction, Room> exits;

  /**
   * Create a room described "description". Initially, it has
   * no exits. "description" is something like "a kitchen" or
   * "an open court yard".
   * @param description The room's description.
   */
  public Room(String description)
  {
      this.description = description;
      exits = new HashMap<Direction, Room>();
      monsters = new ArrayList<Monster>();
      items = new ArrayList<Item>();
  }

  /**
   * Adds an item to the room
   * @param i Item to add to the room
   */
  public void addItem(Item i) {
  	items.add(i);
  }

  /**
   * Add a monster to the room
   * @param m Monster to add to the room
   */
  public void addMonster(Monster m) {
  	monsters.add(m);
  }

  /**
   * Returns the first monster found in the room
   * @return The first monster found in the room
   */
  public Monster getMonster() {
      if(monsters.isEmpty()) return null;
      return monsters.get(0);
  }

  public Item getRealItem(Item item) {
    int index = items.indexOf(item);
    if(index != -1) return items.get(index);
    else return null;
  }

  /**
   * Removes a specific item from the room
   * @param i The Item object to remove
   */
  public void removeItem(Item i) {
      items.remove(i);
  }

  /**
   * Remove a monster from the room
   * @param m The Monster to remove from the room
   */
  public void removeMonster(Monster m) {
      monsters.remove(m);
  }

  /**
   * Define an exit from this room.
   * @param direction The direction of the exit.
   * @param neighbor  The room to which the exit leads.
   */
  public void setExit(Direction direction, Room neighbor)
  {
      exits.put(direction, neighbor);
  }

  /**
   * @return whether the room has any monsters
   */
  public boolean hasMonsters() {
  	return !monsters.isEmpty();
  }

  public List<Direction> getExitDirections() {
  	return new ArrayList<Direction>(exits.keySet());
  }

  /**
   * Return a description of the room in the form:
   * You are in the kitchen.
   * Exits: north west
   * @return A long description of this room
   */
  public String getDescription()
  {
      return "You are in " + description + ".\n" + getExitString() +
      getMonsterString() + getItemString();
  }

  /**
   * Return a string describing the room's exits, for example
   * "Exits: north west".
   * @return Details of the room's exits.
   */
  private String getExitString()
  {
      String returnString = "Exits:";
      Set<Direction> directions = exits.keySet();
      for(Direction exit : directions) {
          returnString += " " + exit.name();
      }
      return returnString + "\n";
  }

  /**
   * @return A string listing all the monsters in the room
   */
  private String getMonsterString() {
      if(monsters.isEmpty()) return "";
      String returnString = "Monsters:";
      for(Monster m: monsters) {
          returnString += " " + m + "\n" + m.getInventoryString() + "\nHealth:" + m.getHealth();
      }
      return returnString + "\n";
  }

  /**
   * @return A string listing all the items in the room
   */
  private String getItemString() {
      if(items.isEmpty()) return "";
      String returnString = "Items:";
      for(Item i: items) {
          returnString += " " + i;
      }
      return returnString + "\n";
  }

  /**
   * Uses the .equals method on items to determine if the list
   * contains the specific item or not. Returns the result as boolean
   *
   * @return true if the room has the specific item, false if not.
   */
  public boolean hasItem(Item i) {
      return items.contains(i);
  }

  /**
   * Return the room that is reached if we go from this room in direction
   * "direction". If there is no room in that direction, return null.
   * @param direction The exit's direction.
   * @return The room in the given direction.
   */
  public Room getExit(Direction direction)
  {
      return exits.get(direction);
  }
  
  /**
   * TODO
   */
  
  public List<Item> getItems() {
	  return items;
  }
}
