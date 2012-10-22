

public class View {
	private Player player;
	public View (Player p) {
		player=p;
	}
	
	/**
	 * This method is invoked very frequently, and tells the status of the 
	 * player.
	 * Its outputs information as to where the player is, 
	 * what its health is,
	 * its exits and what items it has
	 * 
	 */
	public void update() {
		System.out.println(player + " has a health of " + player.getHealth());
		System.out.println(player.getInventoryString());
		System.out.println(player.getCurrentRoom().getLongDescription());
		System.out.print("> ");
		
		
	}
	
	/**
	 * This method is invoked when the user types 'Help' and does not know
	 * what all commands are available to him.
	 */
	public void displayHelp() {
		System.out.println("You are lost. You are alone. You wander around in a cave.\n");
        System.out.println("Your command words are:");
        for (CommandWords commandWord : CommandWords.values()) {
        	System.out.print(commandWord + " ");
        }
        System.out.println("\n");
        
        
	}
	
	/**
	 * Called when the player hits the 'Fight' command
	 * and there is no monster in the room that the player is 
	 * currently in.
	 */
	public void monsterMissing() {
		System.out.println("Nothing to Fight!");
	}
	
	/**
	 * Invoked when user types a command not mentioned in the 
	 * CommandWords enum.
	 */
	public void garbageCommand() {
		System.out.println("Type better! Try again!");
	}
	
	/**
	 * Invoked when say the user wants to go North, and there is no 
	 * north exit in the current room
	 */
	public void invalidRoom() {
		System.out.println("Do you really want to walk into a wall?! Try again!");
	}

	/**
	 * Invoked when the player's health becomes 0 or less. The player is dead.
	 */
	public void gameDone() {
		System.out.println("Sorry you lost. The game is over");
		
	}
	
	/**
	 * Invoked when a monster is killed, and tells what happens as a 
	 * result of this.
	 * @param m : The monster that has died, so that info about it can be
	 * displayed.
	 */
	public void monsterDead(Monster m) {
		System.out.println("The following monster has died: \n" + m);
	}

	/**
	 * Invoked when the user tries to eat a weapon
	 * 
	 * @param i : The weapon that the user is trying to eat.
	 */
	public void eatingWeapon(Item i) {
		System.out.println("You cannot eat a weapon named " + i.getName());
		
	}

	/**
	 * Invoked when the player tries to eat an item that the player currently
	 * does not have.
	 * @param i: The item that the player is trying to eat.
	 */
	public void noItem(Item i) {
		System.out.println("You do not have an item named " + i.getName() + ". Therefore you cannot eat it.");
		
	}

	/**
	 * The case where the user types a valid first command (as defined
	 * by the CommandWords interface), but we need more information to execute
	 *  that command.
	 */
	public void inCompleteCommand() {
		System.out.println( "You typed in a correct command, but one that expects a second piece of info. Please try again");
	}

	/**
	 * Case where user wants to UNDO, and he is the beginning
	 * OR
	 * the user pressed REDO, and he is already at the latest position.
	 * @param commandWord : The command that the user was trying to execute.
	 */
	public void undoRedoUnavailable(CommandWords commandWord) {
		System.out.println( "There is nothing to " + commandWord + " right now.");
	}

	/**
	 * Called when the user hits the Quit button.
	 */
	public void quit() {
		System.out.println( "You quit the game.");
		
	}


}
