package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zuul.Item;

public class ItemTest {

	Item i;
	Item i3;

	@Before
	public void setUp() throws Exception {
		i = new Item("Sword", 10, 10, true);
		i3 = new Item("Axe", 30, 10, true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareTo() {
		assertTrue(i.compareTo(i3) == -1);
		assertTrue(i3.compareTo(i) == 1);
		assertTrue(i.compareTo(i) == 0);
	}

	@Test
	public void testEqualsObject() {
		Item i2 = new Item("Sword", 10, 10, true);
		assertTrue(i.equals(i2));
		assertFalse(i.equals(i3));
	}

	@Test
	public void testSaveRetrieve() {
		FileOutputStream fos;
		ObjectOutputStream oos;
		ObjectInputStream in;
		try {
			fos = new FileOutputStream("myFile.txt");
			oos = new ObjectOutputStream(fos);
			in = new ObjectInputStream(new FileInputStream("myFile.txt"));
			i.save(oos);
			oos.close();
			Item retrieve = Item.retrieve(in);
			assertTrue(i.equals(retrieve));
			assertFalse(i.equals(13));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
