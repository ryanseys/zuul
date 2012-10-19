/**
 * Player Class.
 * This class is in charge of the Player.
 * This class has a reference to the current room that the player is in.
 * As well, there is a playerHistory variable, in order to undo and redo certain moves.
 * This class implements a doCommand method which will take the input command words and correlate them to actual actions.
 *
 */



public class Player extends Humanoid  {

	private PlayerHistory playerHistory;
	private Room currentRoom;
	private View v;
	
	public Player(int health, Room r, String name){
		super(health, name);
		currentRoom = r;
		playerHistory = new PlayerHistory();
	}
	
	public Player(Room r){
		super();
		currentRoom = r;
		playerHistory = new PlayerHistory();
	}


	public void doCommand(Command c){
		boolean b = false;
		
		if (c.getCommandWord().equals(CommandWords.UNDO)){
			c = playerHistory.undo();
			b = true;
		} else if (c.getCommandWord().equals(CommandWords.REDO)){
			c = playerHistory.redo();
			b = true;
		}
		
		if(c==null){
			v.garbageCommand();
		}
		
		
		if (c.getCommandWord().equals(CommandWords.GO)){
			Direction d = (Direction) c.getSecondWord();
			Room r = currentRoom.getExit(d);
			if(r!=null){
				currentRoom = r;
			} else {
				v.invalidRoom();
			}
			if(b == false){
				playerHistory.addStep(c);
			}

		} else if (c.getCommandWord().equals(CommandWords.FIGHT)){
			Monster m = currentRoom.getMonster();
			if(m==null){
				v.monsterMissing();
			} else {
				m.updateHealth(this.getBestItem().getValue());
				this.updateHealth(m.getBestItem().getValue());
				if(m.getHealth()<=0){
					currentRoom.removeMonster(m);
				}
			}
			
		} else if (c.getCommandWord().equals(CommandWords.HELP)){
			v.displayHelp();
		} else if (c.getCommandWord().equals(CommandWords.PICKUP)){
			Item i = (Item) c.getSecondWord();
			i = currentRoom.getRealItem(i);
			if(currentRoom.hasItem(i)){
				addItem(i);
				currentRoom.removeItem(i);
			}
			if(b == false){
				playerHistory.addStep(c);
			}
		} else if (c.getCommandWord().equals(CommandWords.DROP)){
			Item i = (Item) c.getSecondWord();
			
			if(getInventory().contains(i)){
				
				currentRoom.addItem(i);
				removeItem(i);
			}
			if(b == false){
				playerHistory.addStep(c);
			}
		} 
		
		
	}
	
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	public void setView(View v){
		this.v = v;
	}
}
