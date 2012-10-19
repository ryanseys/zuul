
public class View {
	private Player player;
	public View (Player p) {
		player=p;
	}
	
	public void update() {
		System.out.println(player + " has a health of " + player.getHealth());//TODO
		System.out.println(player.getInventoryString());
		System.out.println(player.getCurrentRoom().getLongDescription());
		
		
	}

}
