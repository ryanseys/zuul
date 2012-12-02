package Test;

import static org.junit.Assert.assertTrue;

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

}
