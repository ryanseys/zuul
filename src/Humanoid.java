/**
 * Humanoid Class.
 * This is a super class of the player and monster classes
 * This class has the health variable, and a MAX_HEALTH static variable.
 * The health represents the remaining health of the player or monster.
 * The MAX_HEALTH is a default health, if there is no other input.
 * The inventory is a list of the items that the player/monster have.
 * Items can be added or removed from the inventory.
 * 
 */

import java.util.ArrayList;


public class Humanoid {

	private int health;
	private static int MAX_HEALTH = 100;
	private ArrayList<Item> inventory;

	
	public Humanoid(int maxHealth){
		health = maxHealth;
		inventory = new ArrayList<Item>();
	}
	
	
	public Humanoid(){
		this(MAX_HEALTH);
	}
	
	
	public int getHealth(){
		return health;
	}
	
	public void addItem(Item i){
		inventory.add(i);
	}
	
	public ArrayList<Item> getInventory(Humanoid h){
		return inventory;
	}
	
	public boolean removeItem(Item i){
		return inventory.remove(i);
	}
	
}
