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
    	Room southRoom = new Room("southRoom");
    	Room eastRoom = new Room("eastRoom");
    	Room westRoom = new Room("westRoom");
    	startRoom.addItem(new Item("GoldPiece"));
    	startRoom.setExit(Direction.NORTH, new Room("North Room"));
    	startRoom.setExit(Direction.SOUTH, new Room("South Room"));
    	startRoom.setExit(Direction.EAST, new Room("East Room"));
    	startRoom.setExit(Direction.WEST, new Room("West Room"));
    	
    	eastRoom.setExit(Direction.WEST, startRoom);
    	westRoom.setExit(Direction.EAST, startRoom);
    	northRoom.setExit(Direction.SOUTH, startRoom);
    	southRoom.setExit(Direction.NORTH, startRoom);
    	
    	Player p = new Player (startRoom); 
    	view = new View(p); 
    	view.update();
    	InputStreamReader converter = new InputStreamReader(System.in);
    	System.out.println("SDS");
    	BufferedReader in = new BufferedReader(converter);
    	String input = in.readLine();
    	while (true) {
    		Command c = Command.parse(input);
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
