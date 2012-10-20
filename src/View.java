

public class View {
	private Player player;
	public View (Player p) {
		player=p;
	}
	
	public void update() {
		System.out.println(player + " has a health of " + player.getHealth());
		System.out.println(player.getInventoryString());
		System.out.println(player.getCurrentRoom().getLongDescription());
		System.out.print("> ");
		
		
	}
	
	public void displayHelp() {
		System.out.println("You are lost. You are alone. You wander around in a cave.\n");
        System.out.println("Your command words are:");
        System.out.println("GO, PICKUP, DROP, UNDO, REDO, FIGHT, HELP, QUIT\n");
        
	}
	
	public void monsterMissing() {
		System.out.println("Nothing to Fight!");
	}
	
	public void garbageCommand() {
		System.out.println("Type better! Try again!");
	}
	
	public void invalidRoom() {
		System.out.println("Do you really want to walk into a wall?! Try again!");
	}

	public void gameDone() {
		System.out.println("Sorry you lost. The game is over");
		
	}
	public void monsterDead(Monster m) {
		System.out.println("The following monster has died: \n" + m);
	}


}
