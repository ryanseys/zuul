/**
 * Monster Class.
 * This class is in charge of the antagonists in Zuul.
 * The level of the monster refers to how tough the monster is.
 * This level will act as a sort of multiplier with the items that the monster has in order to calculate damage to player.
 * Everything else is handled in the super class.
 *
 */


public class Monster extends Humanoid {

	private final int level;
	
	
	public Monster(int health, int level){
		super(health);
		this.level = level;
	}
	
	public boolean equals(Object o){
		if(o instanceof Monster){
			return (this.level == ((Monster) o).level  && super.equals(o));
		}
		return false;
	}
	
}
