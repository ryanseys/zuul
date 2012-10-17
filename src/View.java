
public class View {
	private Player player;
	public View (Player p) {
		player=p;
	}
	
	public void update() {
		System.out.println("The current player has a health of " + player.getHealth());//TODO
		
	}

}
