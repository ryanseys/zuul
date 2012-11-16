package View;

import zuul.Monster;
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
	 * Invoked when user types a command not mentioned in the
	 * CommandWords enum.
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
	 * Called when the user hits the Quit button.
	 */
	public void quit();
}
