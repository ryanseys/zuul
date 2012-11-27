package Builders;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import zuul.Room;

public class RoomBuilder extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ButtonBuilder b[];
	private JButton done;
	private JPanel b9;
	private boolean[] rooms;
	MapBuilder mb;
	boolean isDone = false;

	public RoomBuilder() {
		this.setLayout(new GridLayout(4, 4));
		initButtons();
		rooms = new boolean[16];
		initRooms();
		mb = new MapBuilder(rooms);
	}

	public RoomBuilder(int defaultRooms) {
		rooms = new boolean[16];
		initRooms();
		setDefaultRooms();
		mb = new MapBuilder(rooms);
	}

	public void initButtons() {

		b9 = new JPanel();
		b9.setLayout(new FlowLayout());
		JTextArea f = new JTextArea(
				"Now editing: Rooms\nThis is the starting room.");
		f.setEditable(false);
		b9.add(f);
		done = new JButton();
		done.setText("Click here if done editing Rooms");
		b9.add(done);

		done.addActionListener(this);

		b = new ButtonBuilder[16];
		for (int x = 0; x < 16; x++) {
			b[x] = new ButtonBuilder(x);
			b[x].addActionListener(this);
			if (x == 9) {
				this.add(b9);
			} else {
				this.add(b[x]);
			}
		}
	}

	public void initRooms() {
		for (int x = 0; x < 16; x++) {
			rooms[x] = false;
		}
		rooms[9] = true; // always true, starting room
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == done) {
			System.out.println("Done with room building");
			MonsterBuilder monBuil = new MonsterBuilder(rooms);

			isDone = true;
			Room[] roomsArray = monBuil.getRooms();
			if (roomsArray[0].hasMonsters()) {
				System.out.println("ROOM HAS MONSTERS!");
			}

		}

		else {
			int loc = ((ButtonBuilder) e.getSource()).getLoc();
			if (b[loc].getText() == "") {
				b[loc].setText("Room Selected");
				rooms[loc] = true;
			} else {
				b[loc].setText("");
				rooms[loc] = false;
			}
		}

		mb = new MapBuilder(rooms);

	}

	public boolean[] getRooms() {
		return rooms;
	}

	public void setDefaultRooms() {
		initRooms();
		rooms[0] = true;
		rooms[1] = true;
		rooms[5] = true;
		rooms[8] = true;
		rooms[10] = true;
		rooms[13] = true;
	}

	public boolean isDone() {
		return isDone;
	}

	public MapBuilder getMapBuilder() {
		return mb;
	}
}
