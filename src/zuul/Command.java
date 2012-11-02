package zuul;
/**
 * Command class represents a combination of one or two
 * CommandWords, which together form a full instruction
 * for the player to execute.
 *
 * e.g. Combining "go" with a direction "north" together
 * forms the full command "go north", which instructs the
 * player to head to the room north of the current room,
 * if available.
 *
 * @author Ryan Seys
 * @version 2012.11.02
 */

public class Command {

  private CommandWords firstWord;
  private Object secondWord;

  /**
   * Create a command object. First and second word must be supplied, but
   * either one (or both) can be null.
   * @param firstWord The first word of the command. Null if the command was not recognised.
   * @param secondWord The second word of the command. Can be an object
   */
  public Command(CommandWords firstWord, Object secondWord) {
    this.firstWord = firstWord;
    this.secondWord = secondWord;
  }

  /**
   * Return the command word (the first word) of this command. If the
   * command was not understood, the result is null.
   * @return The command word.
   */
  public CommandWords getCommandWord() {
    return firstWord; //basically the same as getFirstWord
  }

  /**
   * Returns the second word of the command, or it will return null if no second word.
   * @return The second word of this command. Returns null if there was no second word.
   */
  public Object getSecondWord() {
    return secondWord;
  }

  /**
   * Returns whether the command is invalid or not
   * @return true if this command was not understood, false if understood
   */
  public boolean isUnknown() {
    return (firstWord == null);
  }

  /**
   * Will return whether or not the command is undoable
   * @return true if the command is undoable, false otherwise
   */
  public boolean isUndoable() {
    if(firstWord == CommandWords.FIGHT ||
          firstWord == CommandWords.HELP ||
          firstWord == CommandWords.QUIT ||
              firstWord == CommandWords.EAT) {
          return false;
      }
    else {
      return true;
    }
  }

  /**
   * Returns whether or not the command has a second word.
   * e.g. "quit" does not, and "go north" does.
   *
   * @return true if the command has a second word.
   */
  public boolean hasSecondWord() {
    return (secondWord != null);
  }

  /**
   * Gets the opposite command of this.
   * This is useful for undoing commands.
   */
  public Command getOpposite() {
    if(!isUndoable()) {
      return null;
    }
    else {
      if(firstWord == CommandWords.GO) {
        return new Command(CommandWords.GO, ((Direction) secondWord).getOpposite());
      }
      else if(firstWord == CommandWords.PICKUP) {
        return new Command(CommandWords.DROP, secondWord);
      }
      else if(firstWord == CommandWords.DROP) {
        return new Command(CommandWords.PICKUP, secondWord);
      }
    return null;
    }
  }

  /**
   * Static parsing method parses a command
   * and returns a command object which can
   * instruct the player to do a certain thing.
   */
  public static Command parse(String command) {
    if(command == "" || command == null) return null;
    command = command.toUpperCase().trim();
    String[] commands = command.split(" ");
    String first = commands[0];
      String second = null;
      if(commands.length == 2) {
          second = commands[1];
      }
      CommandWords cmdword;
      //try to get the enum for that word
      try {
          cmdword = CommandWords.valueOf(first); //get the first word for the command
      }
      catch( Exception e) {
          return null;
      }

    if(cmdword != null) {
      if(second == null ) {
        return new Command(cmdword, null);
      }
      else {
        if(cmdword == CommandWords.GO) {
          Direction dir;
                  //try to get the enum for that
                  try {
                      dir = Direction.valueOf(second); //get direction
                  }
                  catch(Exception e) {
                      return null;
                  }
          if(dir != null) return new Command(cmdword, dir);
        }
        else if (cmdword == CommandWords.DROP || cmdword == CommandWords.PICKUP || cmdword == CommandWords.EAT) {
          //TODO:
          //command for picking up an item, right now misunderstood because you cannot have reference to an item in the string.
          //must know about player or room or something, doesn't seem correct.
          //shouldn't make a new item because we know nothing else about it (value/weight)
                  return new Command(cmdword, new Item(second, 0, 0, false)); //assume weight and value to be zero
        }
      }
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
	  Command c = (Command)o;
	  return c.firstWord.equals(firstWord) && c.secondWord.equals(secondWord);
  }
}