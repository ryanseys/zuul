 /**
 * This class is intended to display the current state of the model
 * in different ways. For all views to successfully display the model
 * and changes to it, it must implement the methods listed in this
 * interface.
 *
 * @author  Vinayak Bansal
 * @version 2012.10.26
 */

public interface IView {
	
	/**
	 * This method is invoked very frequently, and tells the status of the
	 * player.
	 * Its outputs information as to where the player is,
	 * what its health is,
	 * its exits and what items it has
	 *
	 */
	public void update();

	/**
	 * This method is invoked when the user types 'Help' and does not know
	 * what all commands are available to him.
	 */
	public void displayHelp();

	/**
	 * Called when the player hits the 'Fight' command
	 * and there is no monster in the room that the player is
	 * currently in.
	 */
	public void monsterMissing();

	/**
	 * Invoked when user types a command not mentioned in the
	 * CommandWords enum.
	 */
	public void garbageCommand();

	/**
	 * Invoked when say the user wants to go North, and there is no
	 * north exit in the current room
	 */
	public void invalidRoom();

	/**
	 * Invoked when the player's health becomes 0 or less. The player is dead.
	 */
	public void gameDone();

	/**
	 * Invoked when a monster is killed, and tells what happens as a
	 * result of this.
	 * @param m : The monster that has died, so that info about it can be
	 * displayed.
	 */
	public void monsterDead(Monster m);

	/**
	 * Invoked when the user tries to eat a weapon
	 *
	 * @param i : The weapon that the user is trying to eat.
	 */
	public void eatingWeapon(Item i);

	/**
	 * Invoked when the player tries to eat an item that the player currently
	 * does not have.
	 * @param i: The item that the player is trying to eat.
	 */
	public void noItem(Item i);

	/**
	 * This method is called when you try to do something with an item that does not exist
	 * @param i: The item that you are trying to perform an operation on.
	 */
	public void itemInvalid(Item i);

	/**
	 * This method is called when you try to perform an operation on a real item
	 * its just that the player does not have it currently.
	 * @param i
	 */
	public void itemError(Item i);

	/**
	 * The case where the user types a valid first command (as defined
	 * by the CommandWords interface), but we need more information to execute
	 *  that command.
	 */
	public void inCompleteCommand();

	/**
	 * Case where user wants to UNDO, and he is the beginning
	 * OR
	 * the user pressed REDO, and he is already at the latest position.
	 * @param commandWord : The command that the user was trying to execute.
	 */
	public void undoRedoUnavailable(CommandWords commandWord);

	/**
	 * Called when the user hits the Quit button.
	 */
	public void quit();
}
