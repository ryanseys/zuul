import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 /**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Game 
{
    private static View view;
        
    
    public static void main(String[] args) throws IOException {
    	Room startRoom = new Room("StartRoom");
    	Room northRoom = new Room("northRoom");
    	northRoom.addItem(new Item("Bread"));
    	Room southRoom = new Room("southRoom");
    	Room eastRoom = new Room("eastRoom");
    	Room westRoom = new Room("westRoom");
    	startRoom.addItem(new Item("GoldPiece"));
    	startRoom.addItem(new Item("SilverPiece"));
    	startRoom.addItem(new Item("Sword", 50, 0));
    	startRoom.setExit(Direction.NORTH, northRoom);
    	startRoom.setExit(Direction.SOUTH, southRoom);
    	startRoom.setExit(Direction.EAST, eastRoom);
    	startRoom.setExit(Direction.WEST, westRoom);
    	
    	eastRoom.setExit(Direction.WEST, startRoom);
    	eastRoom.addMonster(new Monster(Monster.MAX_HEALTH, Monster.DEFAULT_LEVEL, "Monster1"));
    	westRoom.setExit(Direction.EAST, startRoom);
    	northRoom.setExit(Direction.SOUTH, startRoom);
    	southRoom.setExit(Direction.NORTH, startRoom);
    	
    	Player p = new Player (Player.MAX_HEALTH, startRoom, "Player"); 
    	view = new View(p); 
    	view.update();
    	p.setView(view);
    	InputStreamReader converter = new InputStreamReader(System.in);
    	BufferedReader in = new BufferedReader(converter);
    	String input = in.readLine();
    	while (true) {
    		Command c = Command.parse(input);
    		if (c == null) {
    			view.garbageCommand();
    			view.update();
        		input = in.readLine();
            	continue;
    		}
    		
    		if (c.getCommandWord().equals (CommandWords.QUIT))
    			break;
    		p.doCommand(c);
    		if (p.getHealth()<0)
    			break;
    		view.update();
    		input = in.readLine();
        	
    	}
    }

}
