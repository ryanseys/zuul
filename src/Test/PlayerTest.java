package Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zuul.Command;
import zuul.Item;
import zuul.Monster;
import zuul.Player;
import zuul.Room;

public class PlayerTest {

  Player p;
  Room r;

  @Test
  public void fightLoseHealth() {
    Item i = new Item("Claws", 20, 20, true);
    Monster m = new Monster(r);
    m.addItem(i);
    r.addMonster(m);
    Command c = Command.parse("Fight");
    p.doCommand(c);
    assertTrue(p.getHealth() == 80);
  }

  @Test
  public void garbageCommand() {
    Command c = Command.parse("FDJAKLFJDAIO");
    try {
      p.doCommand(c);// =nullPointerException
      fail();
    }
    catch (Exception e) {
      assert (e instanceof NullPointerException);
    }
  }

  @Test
  public void itemEatFood() {
    Item i = new Item("Apple", 20, 20, false);
    p.addItem(i);
    p.setHealth(20);
    Command c = Command.parse("Eat Apple");
    p.doCommand(c);
    assertTrue(p.getHealth() == 40);
  }

  @Test
  public void itemEatFoodMaxHealth() {
    Item i = new Item("Apple", 20, 20, false);
    p.addItem(i);
    Command c = Command.parse("Eat Apple");
    p.doCommand(c);
    assertTrue(p.getHealth() == 100);
  }

  @Test
  public void nullCommand() {
    Command c = null;
    try {
      p.doCommand(c);// =nullPointerException
      fail();
    }
    catch (Exception e) {
      assert (e instanceof NullPointerException);
    }
  }

  @Test
  public void nullSecondCommandTest() {
    Command c = Command.parse("GO" + null);
    try {
      p.doCommand(c);// =nullPointerException
      fail();
    }
    catch (Exception e) {
      assert (e instanceof NullPointerException);
    }
    Command c2 = Command.parse("Eat" + null);
    try {
      p.doCommand(c2);// =nullPointerException
      fail();
    }
    catch (Exception e) {
      assert (e instanceof NullPointerException);
    }
    Command c3 = Command.parse("Pickup" + null);
    try {
      p.doCommand(c3);// =nullPointerException
      fail();
    }
    catch (Exception e) {
      assert (e instanceof NullPointerException);
    }
    Command c4 = Command.parse("Drop" + null);
    try {
      p.doCommand(c4);// =nullPointerException
      fail();
    }
    catch (Exception e) {
      assert (e instanceof NullPointerException);
    }
  }

  @Before
  public void setUp() throws Exception {
    r = new Room("Current Room");
    p = new Player(r);
  }

  @After
  public void tearDown() throws Exception {
  }
}
