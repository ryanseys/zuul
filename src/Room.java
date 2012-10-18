import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

/**
 * Room class for representing a room
 * that a Player or Monsters can be in.
 *
 * There could be items in the room as well.
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

    public Monster getMonster() {
        if(monsters.isEmpty()) return null;
        return monsters.get(0);
    }

    /**
     * Remove a monster from the room
     *
     */
    public void removeMonster(Monster m) {
        //TODO

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
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are in " + description + ".\n" + getExitString();
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
        return returnString;
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
}

