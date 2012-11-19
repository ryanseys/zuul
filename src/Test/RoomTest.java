package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zuul.Direction;
import zuul.Item;
import zuul.Monster;
import zuul.Room;

public class RoomTest {

  Room r;

  @Test
  public void newRoomAddItemHasThatItem() {
    Item i = new Item("Cookie", false);
    r.addItem(i);
    assertTrue(r.hasItem(i));
  }

  @Test
  public void newRoomAddItemRemoveItemSizeZero() {
    Item x = new Item("Cookie", false);
    r.addItem(x);
    r.removeItem(x);
    assertTrue(r.getItems().size() == 0);
  }

  @Test
  public void newRoomAddMonsterHasMonsters() {
    Monster m = new Monster(1, 2, "Name", r);
    r.addMonster(m);
    assertTrue(r.hasMonsters());
  }

  @Test
  public void newRoomAddOneItemSizeOne() {
    Item x = new Item("Cookie", false);
    r.addItem(x);
    assertTrue(r.getItems().size() == 1);
  }

  @Test
  public void newRoomDescriptionNotBlank() {
    assertTrue(!r.toString().equals(null));
    assertTrue(!r.toString().equals(""));
  }

  @Test
  public void newRoomDoesNotHaveThatItem() {
    assertFalse(r.hasItem(new Item("Cookie", false)));
  }

  @Test
  public void newRoomGetMonsterDoesNotRemoveIt() {
    Monster m = new Monster(1, 2, "Name", r);
    r.addMonster(m);
    r.getMonster();
    assertTrue(r.hasMonsters());
  }

  @Test
  public void newRoomHasNoExits() {
    assertTrue(r.getExitDirections().size() == 0);
  }

  @Test
  public void newRoomHasNoMonsters() {
    assertFalse(r.hasMonsters());
  }

  @Test
  public void newRoomMonsterRemovedIsSame() {
    Monster m = new Monster(1, 2, "Name", r);
    r.addMonster(m);
    assert (r.getMonster().equals(m));
  }

  @Test
  public void newRoomNotNull() {
    assertTrue(!r.equals(null));
  }

  @Test
  public void newRoomRemoveItemFirstSameHashCode() {
    int r1 = r.hashCode();
    r.removeItem(new Item("Cookie", false));
    int r2 = r.hashCode();
    assertTrue(r1 == r2);
  }

  @Test
  public void newRoomRemoveItemFirstSameSize() {
    r.removeItem(new Item("Cookie", false));
    assertTrue(r.getItems().size() == 0);
  }

  @Test
  public void newRoomRemoveMonsterFirstSameHashCode() {
    int r1 = r.hashCode();
    r.removeMonster(new Monster(1, 2, "Name", r));
    int r2 = r.hashCode();
    assertTrue(r1 == r2);
  }

  @Test
  public void newRoomSetExitHasOneExit() {
    r.setExit(Direction.EAST, r);
    assertTrue(r.getExitDirections().size() == 1);
  }

  @Test
  public void newRoomSetTwoExitsHasTwoExits() {
    r.setExit(Direction.EAST, r);
    r.setExit(Direction.WEST, r);
    assertTrue(r.getExitDirections().size() == 2);
  }

  @Before
  public void setUp() throws Exception {
    r = new Room("Current Room");
  }

  @After
  public void tearDown() throws Exception {
    r = null;
  }
}
