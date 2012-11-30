package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import zuul.Direction;

public class DirectionTest {

  @Test
  public void oppositeEastIsWest() {
    assertTrue(Direction.EAST.getOpposite().equals(Direction.WEST));
  }

  @Test
  public void oppositeNorthIsSouth() {
    assertTrue(Direction.NORTH.getOpposite().equals(Direction.SOUTH));
  }

  @Test
  public void oppositeSouthIsNorth() {
    assertTrue(Direction.SOUTH.getOpposite().equals(Direction.NORTH));
  }

  @Test
  public void oppositeWestIsEast() {
    assertTrue(Direction.WEST.getOpposite().equals(Direction.EAST));
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
      Direction i = Direction.NORTH;
      i.save(oos);
      oos.close();
      Direction retrieve = Direction.retrieve(in);
      assertTrue(i.equals(retrieve));
      assertFalse(i.equals(Direction.SOUTH));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
