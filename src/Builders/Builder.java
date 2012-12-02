package Builders;

import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JFrame;

import View.View;

/**
 * @author ryan
 *
 */
public class Builder extends JFrame implements Serializable {

  private static final long serialVersionUID = 1L;
  private RoomBuilder rb;
  private MonsterBuilder mb;
  private ItemBuilder ib;

  /**
   * Constructor: Initializes all the builders, gets the user to choose its
   * preferences and then calls the view to start the game
   */
  public Builder() {
    setLayout(new GridLayout(1, 1));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(true);
    rb = new RoomBuilder();

    this.add(rb);
    setVisible(true);

    try {
		while (!rb.isDone)
			// wait for roomBuilder to be done
		    Thread.sleep(250);

		mb = new MonsterBuilder(rb.getBooleanRooms());
		this.add(mb);
		this.remove(rb);
		validate();
		while (mb.isDone == false)
			// wait for monsterBuilder to be done
		    Thread.sleep(250);

		ib = new ItemBuilder(rb.getBooleanRooms(), mb.getRooms());
		this.add(ib);
		this.remove(mb);
		validate();

		while (ib.isDone() == false)
		  // wait for itemBuilder to be done
		    Thread.sleep(250);
		this.remove(ib);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    View view = View.getInstance(this);
    view.update();
    view.setVisible(true);
  }

  /**
   * Overrided constructor to start the game in default mode
   * @param defaultLayout : the parameter is not used, but is 
   * still required.
   */
  public Builder(int defaultLayout) {
    rb = new RoomBuilder(1);
    mb = new MonsterBuilder(1);
    ib = new ItemBuilder(mb.getRooms());
    View view = View.getInstance(this);
    view.update();
    view.setVisible(true);
  }

  /**
   * Getter for ItemBuilder
   */
  public ItemBuilder getItemBuilder() {
    return ib;
  }

  /**
   * Getter for MonsterBuilder
   */
  public MonsterBuilder getMonsterBuilder() {
    return mb;
  }

  /**
   * Getter for RoomBuilder
   */
  public RoomBuilder getRoomBuilder() {
    return rb;
  }
}
