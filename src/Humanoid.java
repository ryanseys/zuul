import java.util.ArrayList;


public class Humanoid {

	private int health;
	private ArrayList<Item> items;

	
	public Humanoid(int maxHealth){
		health = maxHealth;
		items = new ArrayList<Item>();
	}
	
	
	public Humanoid(){
		this(100);
	}
	
	
	public int getHealth(){
		return health;
	}
	
	public void addItem(Item i){
	
	}
	
	public boolean removeItem(Item i){
		return true;
	}
	
}
