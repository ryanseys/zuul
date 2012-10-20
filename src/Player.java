/**
 * Player Class.
 * This class is in charge of the Player.
 * This class has a reference to the current room that the player is in.
 * As well, there is a playerHistory variable, in order to undo and redo certain moves.
 * This class also has a reference to the view, in order to print out error messages for bad commands.
 * This class implements a doCommand method which will take the input command words and correlate them to actual actions.
 *
 */



public class Player extends Humanoid  {

	private PlayerHistory playerHistory;
	private Room currentRoom;
	private View v;
	
	/**
	 * The Constructor for the player.
	 * Creates the player history.
	 * @param health : The health of the player.
	 * @param room : The current room of the player.
	 * @param name : The name of the player.
	 */
	public Player(int health, Room room, String name){
		super(health, name);
		currentRoom = room;
		playerHistory = new PlayerHistory();
	}
	
	/**
	 * Default constructor for the player. 
	 * Creates the player history.
	 * @param room : The current room of the player.
	 */
	public Player(Room room){
		super();
		currentRoom = room;
		playerHistory = new PlayerHistory();
	}

	/**
	 * This method takes in a command, and executes the required instruction.
	 * @param c : The command to be performed
	 */
	public void doCommand(Command c){
		boolean b = false;	//boolean variable, used to keep undo/redo off of the stack when not wanted.
		
		if (c.getCommandWord().equals(CommandWords.UNDO)){
			c = playerHistory.undo();
			b = true;
		} else if (c.getCommandWord().equals(CommandWords.REDO)){
			c = playerHistory.redo();
			b = true;
		}
		
		//if there is nothing to undo or redo, there will be an error
		if(c==null){
			//TODO change this garbage command to something more meaningful in view
			v.garbageCommand();
			return;
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
				playerHistory.addStep(c);	//only add the step to the stack if it's not an undo/redo
			}

		} else if (c.getCommandWord().equals(CommandWords.FIGHT)){
			Monster m = currentRoom.getMonster();
			if(m==null){
				v.monsterMissing();
			} else {
				m.removeHealth(this.getBestItem().getValue());
				this.removeHealth((m.getBestItem().getValue()) * m.getLevel());
				if(m.getHealth()<=0){
					v.monsterDead(m);
					m.dropItems();
					currentRoom.removeMonster(m);
				}
				if(this.getHealth()<=0){
					//TODO player death
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
		} else if (c.getCommandWord().equals(CommandWords.EAT)){
			Item i = (Item) c.getSecondWord();
			for(Item in: inventory){
				if(in.getName().equals(i.getName())){
						i=in;
				}	
			}
			if(!i.isWeapon()){
			
				this.addHealth(i.getValue());
				if(this.getHealth() > MAX_HEALTH){
					setHealth(MAX_HEALTH);
				}
				removeItem(i);
			}

		}
		
		return;
	}
	
	/**
	 * Setter for the view.
	 * @param v : The view.
	 */
	public void setView(View v){
		this.v = v;
	}

	/**
	 * Getter for the current room.
	 * @return : The room the player is current in.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	
}
