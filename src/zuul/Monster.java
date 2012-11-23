package zuul;

/**
 * Monster Class. This class is in charge of the antagonists in Zuul. The level
 * of the monster refers to how tough the monster is. This level will act as a
 * sort of multiplier with the items that the monster has in order to calculate
 * damage to player. Everything else is handled in the super class. For now,
 * monsters are passive and will only attack a player if the player chooses to
 * fight them.
 * 
 * @author Jarred Linthorne-Shaw
 * @version 2012.10.23
 */

public class Monster extends Humanoid {

  /**
   * Serial version UID
   */
  private static final long serialVersionUID = 1L;
  private final int level;
  public static final int DEFAULT_LEVEL = 1;

  /**
   * The Constructor for the monster. Sets the level of the monster.
   * 
   * @param health
   *          : The base health of the monster
   * @param level
   *          : The strength of the monster
   * @param name
   *          : The name of the monster
   */
  public Monster(int health, int level, String name, Room room) {
    super(health, name, room);
    this.level = level;
  }

  /**
   * The default Constructor for the monster. Sets the monsters level to the
   * default level.
   */
  public Monster(Room room) {
    super(room);
    level = DEFAULT_LEVEL;
  }

  /**
   * This method compares this monster to the object passed.
   * 
   * @param o
   *          : The object passed into the method.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Monster) return ((level == ((Monster) o).level) && super
        .equals(o));
    return false;
  }

  /**
   * Getter for the monster's level.
   * 
   * @return The level of the monster.
   */
  public int getLevel() {
    return level;
  }
}
