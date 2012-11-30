package Test;

/**
 * This class is intended to test the PlayerHistory class
 *
 *
 *
 * @author  Vinayak Bansal
 * @version 2012.10.26
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zuul.Command;
import zuul.CommandWords;
import zuul.Item;
import zuul.PlayerHistory;

public class PlayerHistoryTest {

  PlayerHistory playerHistory = null;
  Command undoableCommand = null;
  Command nonUndoableCommand = null;
  Command goCommand = null;

  @Before
  public void setUp() throws Exception {
    playerHistory = new PlayerHistory();
    undoableCommand = Command.parse("drop Gold");
    nonUndoableCommand = Command.parse("Fight");
    goCommand = Command.parse("Go east");

  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testAddNullStep() {
    /*
     * most functions associated with addStep are implicitly tested by other
     * unit tests the only one that we need to check is what if we try to add a
     * null command
     */

    // null commands should not be added to the stack
    assertFalse(playerHistory.addStep(null));
  }

  @Test
  public void testAddStep() {
    // checking if the redo stack is cleared when we add a new step
    assertTrue(playerHistory.addStep(undoableCommand));
    playerHistory.undo(); // putting stuff on the redo stack
    assertTrue(playerHistory.addStep(undoableCommand)); // putting a new step
    assertNull(playerHistory.redo()); // redo should be empty

  }

  @Test
  public void testClear() {
    // putting stuff on both stacks
    playerHistory.addStep(undoableCommand);
    playerHistory.addStep(goCommand);

    // making sure both are not empty
    assertNotNull(playerHistory.undo());
    assertNotNull(playerHistory.redo());
    playerHistory.clear();
    // now they should be empty
    assertNull(playerHistory.undo());
    assertNull(playerHistory.redo());
  }

  @Test
  public void testPlayerHistory() {
    // constructor should provide empty stacks
    assertNull(playerHistory.undo());
    assertNull(playerHistory.redo());
  }

  @Test
  public void testRedo() {
    playerHistory.addStep(undoableCommand); // initial command
    assertNull(playerHistory.redo());
    playerHistory.undo();
    // redoing should return the same command that was initially added
    assertTrue(playerHistory.redo().equals(undoableCommand));
  }

  @Test
  public void testRemoveItem() {
    // putting stuff on both stacks
    playerHistory.addStep(new Command(CommandWords.PICKUP, new Item("Apple",
        true)));
    playerHistory.addStep(new Command(CommandWords.PICKUP, new Item("Orange",
        true)));

    playerHistory.undo(); // orange on redo, and apple on undo stacks
    playerHistory.removeItem(new Item("Orange", true));
    // now you cannot redo
    assertTrue(playerHistory.canUndo());
    assertFalse(playerHistory.canRedo());
  }

  @Test
  public void testUndoWithDrop() {
    playerHistory.addStep(undoableCommand);
    // making sure the opposite command of command saved is returned
    assertTrue(playerHistory.undo().equals(Command.parse("pickup Gold")));
  }

  @Test
  public void testUndoWithGo() {
    playerHistory.addStep(goCommand);
    // making sure the opposite command of command saved is returned
    assertTrue(playerHistory.undo().equals(Command.parse("go west")));
  }

  @Test
  public void testUndoWithNonUndodoable() {
    // non undoable commands should not affect playerHistory
    assertFalse(playerHistory.addStep(nonUndoableCommand));
    assertNull(playerHistory.undo());
    assertNull(playerHistory.redo());
  }

  // @Test
  // public void testEquals() {
  // assertTrue(playerHistory.equals(playerHistory));
  // playerHistory.addStep(undoableCommand);
  // assertTrue(playerHistory.equals(playerHistory));
  // assertFalse(playerHistory.equals(new PlayerHistory()));
  // }
}
