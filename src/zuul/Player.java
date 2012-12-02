package zuul;

import java.io.Serializable;

/**
 * Player Class. This class is in charge of the Player. This class has a
 * reference to the current room that the player is in. As well, there is a
 * playerHistory variable, in order to undo and redo certain moves. This class
 * also has a reference to the view, in order to print out error messages for
 * bad commands. This class implements a doCommand method which will take the
 * input command words and correlate them to actual actions.
 * 
 * @author Jarred Linthorne-Shaw
 * @version 2012.10.23
 */

public class Player extends Humanoid  implements Serializable{

  /**
   * Serial version UID
   */
  private static final long serialVersionUID = 1L;
  private PlayerHistory playerHistory;

  /**
   * The Constructor for the player. Creates the player history.
   * 
   * @param health
   *          : The health of the player.
   * @param room
   *          : The current room of the player.
   * @param name
   *          : The name of the player.
   */
  public Player(int health, Room room, String name) {
    super(health, name, room);
    playerHistory = new PlayerHistory();
  }

  /**
   * Default constructor for the player. Creates the player history.
   * 
   * @param room
   *          : The current room of the player.
   */
  public Player(Room room) {
    super(room);
    playerHistory = new PlayerHistory();
  }

  /**
   * Asking the player if it can go one step forward. Its a wrapper for invoking
   * the history of the player
   * 
   * @return <code> true </code> if the player go step forward,
   *         <code> false</code> otherwise
   */
  public boolean canRedo() {
    return playerHistory.canRedo();
  }

  /**
   * Asking the player if it can go back. Its a wrapper for invoking the history
   * of the player
   * 
   * @return <code> true </code> if the player go back, <code> false</code>
   *         otherwise
   */
  public boolean canUndo() {
    return playerHistory.canUndo();
  }

  /**
   * This method takes in a command, and executes the required instruction.
   * 
   * @param c
   *          : The command to be performed
   */
  public void doCommand(Command c) {
    Room currentRoom = getCurrentRoom();
    // boolean variable, used to keep undo/redo off of the stack when not wanted.
    boolean b = false; 
    // If the undo command is called on another command(drop, pickup, go)
    if (c.getCommandWord().equals(CommandWords.UNDO)) { 
      c = playerHistory.undo(); // Undo the command
      b = true;
      // If the undo command is called on another command(drop, pickup, go)
    } else if (c.getCommandWord().equals(CommandWords.REDO)) { 
      c = playerHistory.redo(); // Redo the command
      b = true;
    }

    if ((b == true) && (c == null)) return;

    if (c.getCommandWord().equals(CommandWords.GO)) { // If the command is go
      // set the second word to be the direction
      Direction d = (Direction) c.getSecondWord(); 
      if (d == null) return;
      // Get the exit room in the specified direction
      Room r = currentRoom.getExit(d); 
      // the new room is the room in the specified direction
      if (r != null){ 
    	  setCurrentRoom(r);
      }
      else return;
      // Only add the step if this is the case, to prevent stack problems
      if (b == false) playerHistory.addStep(c); 

    } else if (c.getCommandWord().equals(CommandWords.FIGHT)) { // If the
                                                                // command is
                                                                // fight
      Monster m = currentRoom.getMonster(); // get the monster in the room
      if (m == null) { // If there are no monsters in the room, this will be
                       // null
      } else {
        // Remove health from the monster depending on the value 
        // of the best item in inventory
        m.removeHealth(getBestItem().getValue()); 
        // Remove health from the player depending on the value of 
        // the best item on the monster multiplied with the monster's level
        removeHealth((m.getBestItem().getValue()) * m.getLevel()); 
        if (m.getHealth() <= 0) { // Monster has died if its health is less than
                                  // or equal to zero
   
          //m.dropItems(); // Drop all of the monster's items and add them to the
                         // room
          currentRoom.removeMonster(m); // Remove the monster from the room
        }
      }
   // If the command is pickup
    } else if (c.getCommandWord().equals(CommandWords.PICKUP)) { 
      Item i = (Item) c.getSecondWord(); // Get the item from the second word
      if (i == null) return;
      i = currentRoom.getRealItem(i); // Determine if the item is real or not
      if (i == null) return;
      if (currentRoom.hasItem(i)) { // If the real item is in the current room
        addItem(i); // Add this item to your inventory
        currentRoom.removeItem(i); // Remove this item from the room
      }
      // Only add the step if this is the case, to prevent stack problems
      if (b == false) playerHistory.addStep(c); 
      // If the command is drop
    } else if (c.getCommandWord().equals(CommandWords.DROP)) { 
      Item i = (Item) c.getSecondWord(); // Get the item from the second word
      if (i == null) return;

      i = getRealItem(i); // Determine if the item is real or not

      if (i == null) return;
      if (getInventory().contains(i)) { // If the item is in the player's
                                        // inventory
        currentRoom.addItem(i); // Add the item to the room
        removeItem(i); // Remove the item from the player's inventory
      }
      // Only add the step if this is the case, to prevent stack problems
      if (b == false) playerHistory.addStep(c); 
      // If the command is eat
    } else if (c.getCommandWord().equals(CommandWords.EAT)) { 
      Item i = (Item) c.getSecondWord(); // Get the item from the second word
      if (i == null) return;

      if (!inventory.contains(i)) return;

      for (Item in : inventory)
        // Then assign the item to the actual item object in the inventory
        if (in.equals(i)) i = in; 
      if (!i.isWeapon()) { // If the item is not a weapon
        addHealth(i.getValue()); // Add the value of the item to the current
                                 // health
        // Set the health to the maximum health (i.e. 
        //Your health cannot exceed the maximum health)
        if (getHealth() > MAX_HEALTH) setHealth(MAX_HEALTH);
        // Remove the item from the inventory, you can't eat it twice
        removeItem(i); 
        playerHistory.removeItem(i);
      }
    }

    return;
  }

  @Override
  public void reset() {
    playerHistory.clear();
    super.reset();
  }
}
