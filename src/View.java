
public class View {
	private Player player;
	public View (Player p) {
		player=p;
	}
	
	public void update() {
		System.out.println("The current player has a health of " + player.getHealth());//TODO
		System.out.println("The player has the following items");
		
		for (Item item : player.getInventory(null)) {
			System.out.println(item.getDescription());
		}
		
		System.out.println("The player is in room " + player.getCurrentRoom().getLongDescription());
		
		
	}

}
