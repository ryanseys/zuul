/**
 * Command class.
 * 
 * @author  Ryan Seys
 */

public class Command
{
    private CommandWords firstWord;
    private Object secondWord;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command(CommandWords firstWord, Object secondWord)
    {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public CommandWords getFirstWord()
    {
        return firstWord;
    }
    
    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public CommandWords getCommandWord()
    {
        return firstWord; //basically the same as getFirstWord
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public Object getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (firstWord == null);
    }
    
    /**
     * @return whether the command is undoable
     */
    public boolean isUndoable() {
    	return true; // TODO: implement this
    }
    
    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    public Command getOpposite() {
    	if(firstWord == CommandWords.FIGHT ||
    		firstWord == CommandWords.HELP ||
    		firstWord == CommandWords.QUIT) {
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
}

