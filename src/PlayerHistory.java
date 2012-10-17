import java.util.Stack;

/*
 * This class keeps track of the history of a player. Where
 * all it has been currently, where all to go when the user 
 * hits the undo or the redo button.
 */
public class PlayerHistory {

	private Stack<Command> undoStack; // top contains what the last move of the player was
	private Stack<Command> redoStack;
	
	public PlayerHistory() {
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
	}
	/*
	 * This method is called when the player makes a move
	 * 
	 * @Return <code>true</code> if command was undoable, 
	 * <code>false</code> otherwise
	 */
	public boolean addStep(Command c) {
		if (c.isUndoable()) {
			undoStack.push(c);
			redoStack.clear(); //you have decided to go on a new path.
			return true;
		}
		return false; //the command cannot be undone. so we have to do nothing with the stack
		
	}
	
	/*
	 * Calculates and returns the command that needs to be 
	 * executed to get back to state
	 * where the player was one move ago.
	 * Can return <code>null </code>. Will do it in the case where the player
	 * has not even made its first move.
	 * 
	 */
	public Command undo() {
		if (!undoStack.isEmpty()) {
			Command toReturn = undoStack.pop();
			toReturn = toReturn.getOpposite();
			redoStack.push(toReturn);
			return toReturn;
			 
		}
		return null;
	}
	
	/*
	 * Calculates and returns the Command to be executed
	 * in case the player changed his mind about changing his
	 * mind.
	 * Will return null in case the user has decided to go 
	 * on a new path, and has not hit the undo yet.
	 */
	public Command redo() {
		
		if (!redoStack.isEmpty()) {
			Command toReturn = redoStack.pop();
			toReturn = toReturn.getOpposite();
			undoStack.push(toReturn);
			return toReturn;
			
		}
		return null;
	}
	
	/*
	 * Wipes the player history clean, in case the player dies
	 * or decides to start over again.
	 */
	public void clear() {
		undoStack.clear();
		redoStack.clear();
	}
	
	
}
