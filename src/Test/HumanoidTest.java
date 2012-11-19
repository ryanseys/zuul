package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zuul.Item;
import zuul.Player;
import zuul.Room;

public class HumanoidTest {

  Player p;
  Room r;

  @Before
  public void setUp() throws Exception {
    r = new Room("Current");
    p = new Player(r);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testAddHealth() {
    p.setHealth(20);
    p.addHealth(40);
    assertTrue(p.getHealth() == 60);
  }

  @Test
  public void testAddItem() {
    Item i = new Item("Arrow", 10, 10, false);
    p.addItem(i);
    assertFalse(p.getInventory().isEmpty());
  }

  @Test
  public void testEqualsObject() {
    Item i = new Item("Blade", 60, 10, true);
    Item i2 = new Item("Blade", 60, 10, true);
    assertTrue(i.equals(i2));
  }

  @Test
  public void testGetBestItem() {
    Item i = new Item("Blade", 60, 10, true);
    Item i2 = new Item("Bow", 10, 10, true);
    p.addItem(i);
    p.addItem(i2);
    Item i3 = p.getBestItem();
    assertTrue(i3.equals(i));

  }

  @Test
  public void testGetRealItem() {
    Item i = new Item("Hammer", true);
    p.addItem(i);
    Item i2 = p.getRealItem(i);
    assertTrue(p.getInventory().get(0).equals(i2));
  }

  @Test
  public void testRemoveHealth() {
    p.removeHealth(60);
    assertTrue(p.getHealth() == 40);
  }

  @Test
  public void testRemoveItem() {
    Item i = new Item("Arrow", 10, 10, false);
    p.addItem(i);
    p.removeItem(i);
    assertTrue(p.getInventory().isEmpty());
  }
}
