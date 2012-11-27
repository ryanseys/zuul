package Builders;

import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JFrame;

import View.View;

public class Builder extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private RoomBuilder rb;
	private MonsterBuilder mb;
	private ItemBuilder ib;

	public Builder() {
		this.setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		rb = new RoomBuilder();

		this.add(rb);
		this.setVisible(true);

		while (!rb.isDone) {
			// wait for gameBuilder to be done
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		mb = new MonsterBuilder(rb.getBooleanRooms());
		this.add(mb);
		this.remove(rb);
		this.validate();
		while (mb.isDone == false) {
			// wait for monsterBuilder to be done
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ib = new ItemBuilder(rb.getBooleanRooms(), mb.getRooms());
		this.add(ib);
		this.remove(mb);
		this.validate();

		while (ib.isDone() == false) {
			// wait for itemBuilder to be done
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.remove(ib);

		View view = View.getInstance(this);
		view.update();
		view.setVisible(true);
	}

	public Builder(int defaultLayout) {
		rb = new RoomBuilder(1);
		mb = new MonsterBuilder(1);
		ib = new ItemBuilder(mb.getRooms());
		View view = View.getInstance(this);
		view.update();
		view.setVisible(true);
	}

	public ItemBuilder getItemBuilder() {
		return ib;
	}

	public MonsterBuilder getMonsterBuilder() {
		return mb;
	}

	public RoomBuilder getRoomBuilder() {
		return rb;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Builder b = new Builder();
	}

	public void setRoomBuilder() {
		rb = null;
	}

	public void setItemBuilder() {
		ib = null;
	}

	public void setMonsterBuilder() {
		mb = null;
	}
}
