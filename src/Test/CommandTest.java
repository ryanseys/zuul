package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zuul.Command;
import zuul.CommandWords;
import zuul.Direction;
import zuul.Item;

public class CommandTest {

  @Test
  public void dropHasOpposite() {
    Item i = new Item("Cookie", false);
    Command c = new Command(CommandWords.DROP, i);
    assertTrue(c.getOpposite() != null);
  }

  @Test
  public void eatHasNoOpposite() {
    Item i = new Item("Cookie", false);
    Command c = new Command(CommandWords.EAT, i);
    assertTrue(c.getOpposite() == null);
  }

  @Test
  public void fightHasNoOpposite() {
    Command c = new Command(CommandWords.FIGHT, null);
    assertTrue(c.getOpposite() == null);
  }

  @Test
  public void goHasOpposite() {
    Command c = new Command(CommandWords.GO, Direction.WEST);
    assertTrue(c.getOpposite() != null);
  }

  @Test
  public void helpHasNoOpposite() {
    Command c = new Command(CommandWords.HELP, null);
    assertTrue(c.getOpposite() == null);
  }

  @Test
  public void newCommandGetCommandWord() {
    Item i = new Item("Cookie", false);
    Command c = new Command(CommandWords.EAT, i);
    assertTrue(c.getCommandWord().equals(CommandWords.EAT));
  }

  @Test
  public void newCommandGetSecondWord() {
    Item i = new Item("Cookie", false);
    Command c = new Command(CommandWords.EAT, i);
    assertTrue(c.getSecondWord().equals(i));
  }

  @Test
  public void pickupHasOpposite() {
    Item i = new Item("Cookie", false);
    Command c = new Command(CommandWords.PICKUP, i);
    assertTrue(c.getOpposite() != null);
  }

  @Test
  public void quitHasNoOpposite() {
    Command c = new Command(CommandWords.QUIT, null);
    assertTrue(c.getOpposite() == null);
  }

  @Test
  public void quitIsNotUnknown() {
    Command c = new Command(CommandWords.QUIT, null);
    assertFalse(c.isUnknown());
  }

  @Test
  public void redoHasNoOpposite() {
    Command c = new Command(CommandWords.REDO, null);
    assertTrue(c.getOpposite() == null);
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void undoHasNoOpposite() {
    Command c = new Command(CommandWords.UNDO, null);
    assertTrue(c.getOpposite() == null);
  }

  @Test
  public void unknownIsUnknown() {
    Command c = new Command(null, null);
    assertTrue(c.isUnknown());
  }
}
