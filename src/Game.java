import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 /**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, invoke the static main method with no string 
 *  parameters.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Vinayak Bansal
 * @version 2012.10.22
 */

public class Game 
{
    private static View view;
        
    
    public static void main(String[] args) throws IOException {
    	Room startRoom = new Room("StartRoom");
    	Room northRoom = new Room("northRoom");
    	northRoom.addItem(new Item("Bread", 30, 0, false));
    	Room southRoom = new Room("southRoom");
    	Room eastRoom = new Room("eastRoom");
    	Room westRoom = new Room("westRoom");
    	startRoom.addItem(new Item("GoldPiece", false));
    	startRoom.addItem(new Item("SilverPiece", false));
    	startRoom.addItem(new Item("Sword", 50, 0, true));
    	startRoom.setExit(Direction.NORTH, northRoom);
    	startRoom.setExit(Direction.SOUTH, southRoom);
    	startRoom.setExit(Direction.EAST, eastRoom);
    	startRoom.setExit(Direction.WEST, westRoom);
    	westRoom.addItem(new Item("Apple", 10, 0, false));
    	westRoom.addItem(new Item("Orange", 15, 0, false));
    	westRoom.addItem(new Item("Pear", 20, 0, false));
    	
    	eastRoom.setExit(Direction.WEST, startRoom);
    	
    	Monster Monster1 = new Monster(Monster.MAX_HEALTH, Monster.DEFAULT_LEVEL, "Monster1", eastRoom);
    	eastRoom.addMonster(Monster1);
    	Monster1.addItem(new Item("Claws", 10, 0, true));
    	
    	Monster Boss = new Monster(100, 2, "Boss", southRoom);
    	southRoom.addMonster(Boss);
    	Boss.addItem(new Item("Flamethrower", 30, 0, true));
    	
    	
    	westRoom.setExit(Direction.EAST, startRoom);
    	northRoom.setExit(Direction.SOUTH, startRoom);
    	southRoom.setExit(Direction.NORTH, startRoom);
    	
    	Player p = new Player (Player.MAX_HEALTH, startRoom, "Player"); 
    	view = new View(p); 
    	view.update();
    	p.setView(view);
    	
    	
    	InputStreamReader converter = new InputStreamReader(System.in);
    	BufferedReader in = new BufferedReader(converter);
    	String input = in.readLine(); //priming the loop
    	
    	while (true) { // keep playing until the player quits or he is killed by a monster
    		Command c = Command.parse(input);
    		if (c == null) { //parse returns null if the input command from the user could not be passed
    			view.garbageCommand();
    			view.update();
        		input = in.readLine();
            	continue;
    		}
    		
    		if (c.getCommandWord().equals (CommandWords.QUIT)) {
    			break;
    		}
    			
    		p.doCommand(c); //making the player eat, fight, pickup, drop, go, etc
    		if (p.getHealth() <= 0) { //what if the player died as a result of doing that command.
    			break;
    		}
    		view.update();
    		input = in.readLine();
        	
    	}
    	if (p.getHealth() <= 0)
    		view.gameDone(); //telling the user that the game is over.
    	else {
    		view.quit();
    	}
    }

}
