package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import zuul.Item;
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

@SuppressWarnings("serial")
public abstract class View extends JFrame{

	/**
	 * This is the overarching method used to update everything in the class.
	 * It will change buttons as required and enables/disables them.
	 * It updates all of the panels as well.
	 */
	public abstract void update();



	/**
	 * This method pops up a dialog that informs the player that they have been defeated by a monster.
	 */
	protected abstract void gameDone();

	/**
	 * If the monster dies, A message should be printed accordingly.
	 * This method creates a popup that shows the items that the monster dropped.
	 * @param m : The monster that has died.
	 */
	protected abstract void monsterDead(Monster m);


	/**
	 * Quit method, used to exit the game.
	 */
	protected abstract void quit();
	
	
	/**
	 * This method updates the mapPanel with the images of the minimap.
	 * If there is no map in the players inventory, 
	 * then a picture message is shown telling the player to find the map.
	 */
	protected abstract void updateMapPanel() ;
	
	/**
	 * This method prints out the objective of the game.
	 * @return : Returns a string informing the player of the game objective.
	 */
	protected abstract String getObjective();
	
	/**
	 * This method pops up a dialog that gives the player a hint as to what to do next.
	 */
	protected abstract void getHint();
	
	/**
	 * This method pops up a dialog that congratulates the player on winning the game.
	 */
	protected abstract void win();
	
	/**
	 * Used when the inspect button is clicked.
	 * This method returns the image icon associated with the item when inspect is clicked.
	 * @param i : The item that is selected.
	 * @return : The corresponding image that represents the item.
	 */
	protected abstract ImageIcon getImageIcon (Item input);
	
	/**
	 * Reset method, used to start the game anew.
	 */
	protected abstract void reset();
	
	/**
	 * Initialize the game with the original monsters and items
	 * in the case that monsters were defeated, or items were eaten.
	 */
	protected abstract void resetInitialize();
	
	/**
	 * This method pops up a dialog that informs the player of the damage done to and from the player.
	 */
	protected abstract void fightPopUp();
	
	
	
	
	
}
