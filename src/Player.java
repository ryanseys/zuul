/**
 * Player Class.
 * This class is in charge of the Player.
 * This class has a reference to the current room that the player is in.
 * As well, there is a playerHistory variable, in order to undo and redo certain moves.
 * This class implements a doCommand method which will take the input command words and correlate them to actual actions.
 *
 */


public class Player extends Humanoid {

	private PlayerHistory playerHistory;
	private Room currentRoom;
	
	public Player(int health){
		super(health);
	}
	
	public void undo(){
		Command c = playerHistory.undo();
		doCommand(c);
	}
	
	public void redo(){
		Command c = playerHistory.redo();
		doCommand(c);
	}
	
	public void doCommand(Command c){
		if (c.getCommandWord().equals("Go")){
			Direction d = (Direction) c.getSecondWord();
			Room r = currentRoom.getExit(d);
			if(r!=null){
				currentRoom = r;
			} // else error TODO
		} //else if ..other commands TODO
	}
	
}
