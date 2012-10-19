import java.util.Observable;
import java.util.Observer;


public class View implements Observer{
	public static final int HELP = 1;
	private Player player;
	public View (Player p) {
		player=p;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		assert(arg1 instanceof Integer);
		int input = (Integer) arg1;
		if (input == HELP) {
			System.out.println("You are lost. You are alone. You wander around in a cave.\n");
	        System.out.println("Your command words are:");
	        System.out.println("GO, PICKUP, DROP, UNDO, REDO, FIGHT, HELP, QUIT");
	        
		}
		System.out.println(player + " has a health of " + player.getHealth());//TODO
		System.out.println(player.getInventoryString());
		System.out.println(player.getCurrentRoom().getLongDescription());
		
		
	}


}
