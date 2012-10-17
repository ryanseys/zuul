
public enum Direction {
	NORTH, EAST, SOUTH, WEST;
	
	public Direction getOpposite() {
		if(this.equals(Direction.NORTH)) {
			return Direction.SOUTH;
		}
		else if(this.equals(Direction.EAST))
		{
			return Direction.WEST;
		}
		else if(this.equals(Direction.SOUTH)) {
			return Direction.NORTH;
		}
		else if(this.equals(Direction.WEST)) {
			return Direction.EAST;
		}
		else {
			return null;
		}
	}
}
