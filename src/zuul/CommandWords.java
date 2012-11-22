package zuul;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This is an enum for the command words. It represents all the different
 * commands that a player can call as a valid command. If the command is not in
 * here, it should not ever work as a valid command.
 * 
 * @author Ryan Seys
 * @version 2012.11.02
 */

public enum CommandWords{
  // All possible commands
  //all words for firstWord in Commands
  GO, PICKUP, DROP, FIGHT, HELP, QUIT, UNDO, REDO, EAT; 
  
  public void save(ObjectOutputStream input) throws IOException {
	  input.writeUTF(this.toString());
  }
  
  public static CommandWords retrieve(ObjectInputStream input) throws IOException {
	  String name = input.readUTF();
	  return CommandWords.valueOf(name);
  }
}
