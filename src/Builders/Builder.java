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
   * Constructor
   */
  public Builder() {
    setLayout(new GridLayout(1, 1));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(true);
    rb = new RoomBuilder();

    this.add(rb);
    setVisible(true);

    while (!rb.isDone)
      // wait for gameBuilder to be done
      try {
        Thread.sleep(250);
      }
      catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    mb = new MonsterBuilder(rb.getBooleanRooms());
    this.add(mb);
    this.remove(rb);
    validate();
    while (mb.isDone == false)
      // wait for monsterBuilder to be done
      try {
        Thread.sleep(250);
      }
      catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    ib = new ItemBuilder(rb.getBooleanRooms(), mb.getRooms());
    this.add(ib);
    this.remove(mb);
    validate();

    while (ib.isDone() == false)
      // wait for itemBuilder to be done
      try {
        Thread.sleep(250);
      }
      catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    this.remove(ib);

    View view = View.getInstance(this);
    view.update();
    view.setVisible(true);
  }

  /**
   * @param defaultLayout
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
   * @return
   */
  public ItemBuilder getItemBuilder() {
    return ib;
  }

  /**
   * @return
   */
  public MonsterBuilder getMonsterBuilder() {
    return mb;
  }

  /**
   * @return
   */
  public RoomBuilder getRoomBuilder() {
    return rb;
  }
}
