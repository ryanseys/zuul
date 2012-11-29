package Builders;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import zuul.Humanoid;
import zuul.Item;
import zuul.Monster;
import zuul.Room;

public class MonsterBuilder extends AbstractBuilder implements ActionListener {

	private static final long serialVersionUID = 1L;

	MapBuilder mb;
	Monster alien;
	Monster boss;
	ArrayList<Monster> mArray = new ArrayList<Monster>();

	public MonsterBuilder(boolean[] b) {
		this.setLayout(new GridLayout(4, 4));
		initButtons("Monsters");
		rooms = new boolean[16];
		initRooms(b);
		initMonsters();
	}

	public MonsterBuilder(int defaultRooms) {
		initRealRooms();
		setDefaultMonsters();
	}

	private void setDefaultMonsters() {
		initMonsters();
		r[10].addMonster(alien);
		alien.setRoom(r[10]);
		r[13].addMonster(boss);
		boss.setRoom(r[13]);
	}

	@Override
	public void initButtons(String type) {
		super.initButtons(type);
		JTextArea f2 = new JTextArea("Note: Must add Alien to have Map in game.\n           Must add Boss to have Key in game.");
		f2.setEditable(false);
		b9.add(f2);
	}
		
	public void initMonsters() {
		Room temp = new Room("Temp room");
		alien = new Monster(Humanoid.MAX_HEALTH, Monster.DEFAULT_LEVEL,
				"Alien", null);
		alien.addItem(new Item("Map", 0, 0, true));
		alien.addItem(new Item("Claws", 10, 0, true));
		alien.setRoom(temp);
		boss = new Monster(100, 2, "Boss", null);
		boss.addItem(new Item("Flamethrower", 30, 0, true));
		boss.addItem(new Item("Key", 0, 0, true));
		boss.setRoom(temp);
		mArray.add(alien);
		mArray.add(boss);
	}

	@Override
	public void initRooms(boolean[] input) {
		super.initRooms(input);
		initRealRooms();
	}

	public void initRealRooms() {
		r = new Room[16];
		for (int x = 0; x < 16; x++) {
			r[x] = new Room(x + "");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO different option dialog for no monsters
		if (e.getSource() == done) {
			this.setVisible(false);
			isDone = true;
		} else {
			int loc = ((ButtonBuilder) e.getSource()).getLoc();
			int popup = JOptionPane.showOptionDialog(this,
					"Select a Monster to put in this room:", "Selected Room",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, mArray.toArray(),
					null);
			if (popup == 0 || popup == 1) {
				if (mArray.get(popup).equals(alien)) {
					r[loc].addMonster(alien);
					alien.setRoom(r[loc]);
					mArray.remove(alien);
				} else {
					r[loc].addMonster(boss);
					boss.setRoom(r[loc]);
					mArray.remove(boss);
				}
				b[loc].setEnabled(false);
				b[loc].setText("" + getRooms()[loc].getMonster().getName()
						+ " Placed!");
			}
		}

	}
}
