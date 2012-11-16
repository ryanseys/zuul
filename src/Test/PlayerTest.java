package Test;
import static org.junit.Assert.*;

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

	@Before
	public void setUp() throws Exception {
		r = new Room("Current Room");
		p = new Player(r);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void undoUnavailableTest() {
		Command c = Command.parse("UNDO");
		try {
			p.doCommand(c);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void redoUnavailableTest() {
		Command c = Command.parse("REDO");
		try {
			p.doCommand(c);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void garbageCommand() {
		Command c = Command.parse("FDJAKLFJDAIO");
		try {
			p.doCommand(c);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void nullCommand() {
		Command c = null;
		try {
			p.doCommand(c);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void incompleteCommandTest() {
		Command c = Command.parse("GO");
		try {
			p.doCommand(c);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
		Command c2 = Command.parse("Eat");
		try {
			p.doCommand(c2);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
		Command c3 = Command.parse("Pickup");
		try {
			p.doCommand(c3);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
		Command c4 = Command.parse("Drop");
		try {
			p.doCommand(c4);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void nullSecondCommandTest() {
		Command c = Command.parse("GO" + null);
		try {
			p.doCommand(c);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
		Command c2 = Command.parse("Eat" + null);
		try {
			p.doCommand(c2);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
		Command c3 = Command.parse("Pickup" + null);
		try {
			p.doCommand(c3);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
		Command c4 = Command.parse("Drop" + null);
		try {
			p.doCommand(c4);//=nullPointerException
			fail();
		} catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void invalidRoom() {
		Command c = Command.parse("GO North");
		try {
			p.doCommand(c);
			fail();
		}catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void monsterMissing() {
		Command c = Command.parse("Fight");
		try {
			p.doCommand(c);
			fail();
		}catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void itemPickupInvalid() {
		Command c = Command.parse("Pickup Item");
		try {
			p.doCommand(c);
			fail();
		}catch (Exception e){
			assert (e instanceof NullPointerException);
		}

	}

	@Test
	public void itemDropInvalid() {
		Command c = Command.parse("Drop Item");
		try {
			p.doCommand(c);
			fail();
		}catch (Exception e){
			assert (e instanceof NullPointerException);
		}

	}

	@Test
	public void itemEatInvalid() {
		Command c = Command.parse("Eat Item");
		try {
			p.doCommand(c);
			fail();
		}catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void itemEatWeapon() {
		Item i = new Item("Sword", true);
		p.addItem(i);
		Command c = Command.parse("Eat Sword");
		try {
			p.doCommand(c);
			fail();
		}catch (Exception e){
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
		assertTrue(p.getHealth()==40);
	}

	@Test
	public void itemEatFoodMaxHealth() {
		Item i = new Item("Apple", 20, 20, false);
		p.addItem(i);
		Command c = Command.parse("Eat Apple");
		p.doCommand(c);
		assertTrue(p.getHealth()==100);
	}

	@Test
	public void fightLoseHealth() {
		Item i = new Item("Claws", 20, 20, true);
		Monster m = new Monster(r);
		m.addItem(i);
		r.addMonster(m);
		Command c = Command.parse("Fight");
		p.doCommand(c);
		assertTrue (p.getHealth()==80);
	}
}
