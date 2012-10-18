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
    	Player p = new Player (100, null); //TODO
    	view = new View(p); 
    	InputStreamReader converter = new InputStreamReader(System.in);
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
    	}
    }

}
