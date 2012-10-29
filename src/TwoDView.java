import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class TwoDView extends JFrame implements IView, ActionListener
{
	private JMenuItem newGame, help, quit, add;
	private JMenuBar menuBar;
	private JButton undo, redo, northRoom, southRoom, eastRoom, westRoom, currentRoom;
	private JPanel consolePanel, inventoryPanel;
	private Player p;
	private JList inventoryList;
	private DefaultListModel inventoryModel;

	public TwoDView (Player p) {
		this.p = p;
		p.addItem(new Item("Gold", false));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menuBar = new JMenuBar( );
	    setJMenuBar( menuBar );
	    setLayout(new GridLayout(3,3));

	    undo = new JButton("UNDO");
	    redo = new JButton("REDO");
	    northRoom = new JButton("North Room");
	    eastRoom = new JButton("East Room");
	    westRoom = new JButton("West Room");
	    southRoom = new JButton("South Room");
	    currentRoom = new JButton("You are Here");

	    undo.addActionListener(this);
	    redo.addActionListener(this);
	    northRoom.addActionListener(this);
	    eastRoom.addActionListener(this);
	    westRoom.addActionListener(this);
	    southRoom.addActionListener(this);
	    currentRoom.addActionListener(this);

	    consolePanel = new JPanel();
	    inventoryPanel = new JPanel();
//	    inventoryPanel.setLayout(new FlowLayout());
	    inventoryModel = new DefaultListModel();
	    inventoryList = new JList(inventoryModel);
	    JScrollPane pane = new JScrollPane(inventoryList);
	    inventoryPanel.add(pane);
//	    pane.setBounds(inventoryPanel.getBounds());

	    add(undo);
	    add(northRoom);
	    add(redo);
	    add(westRoom);
	    add(currentRoom);
	    add(eastRoom);
	    add(consolePanel);
	    add(southRoom);
	    add(inventoryPanel);

	    JMenu addressMenu = new JMenu( "File" );
	    menuBar.add( addressMenu );


	    newGame = new JMenuItem ( "New" );
	    addressMenu.add( newGame );
	    newGame.addActionListener(this);

	    help = new JMenuItem ( "Help" );
	    addressMenu.add( help );
	    help.addActionListener(this);

	    quit = new JMenuItem ( "Quit" );
	    addressMenu.add( quit );
	    quit.addActionListener(this);

	    update();
	}


	@Override
	public void update() {
		Room currentRoom = p.getCurrentRoom();
		if (currentRoom.getExit(Direction.NORTH) == null) {
			northRoom.setEnabled(false);
		} else {
			northRoom.setEnabled(true);
		}
		if (currentRoom.getExit(Direction.SOUTH) == null) {
			southRoom.setEnabled(false);
		} else {
			southRoom.setEnabled(true);
		}
		if (currentRoom.getExit(Direction.EAST)== null) {
			eastRoom.setEnabled(false);
		} else {
			eastRoom.setEnabled(true);
		}
		if (currentRoom.getExit(Direction.WEST) == null) {
			westRoom.setEnabled(false);
		} else {
			westRoom.setEnabled(true);
		}

		if (p.canUndo()) {
			undo.setEnabled(true);
		} else {
			undo.setEnabled(false);
		}
		if (p.canRedo()) {
			redo.setEnabled(true);
		} else {
			redo.setEnabled(false);
		}

		inventoryModel.removeAllElements();
		for (Item i :p.getInventory())
			inventoryModel.addElement(i);
	}
	@Override
	public void displayHelp() {
		// TODO Auto-generated method stub

	}
	@Override
	public void monsterMissing() {
		// TODO Auto-generated method stub

	}
	@Override
	public void garbageCommand() {
		// TODO Auto-generated method stub

	}
	@Override
	public void invalidRoom() {
		// TODO Auto-generated method stub

	}
	@Override
	public void gameDone() {
		// TODO Auto-generated method stub

	}
	@Override
	public void monsterDead(Monster m) {
		// TODO Auto-generated method stub

	}
	@Override
	public void eatingWeapon(Item i) {
		// TODO Auto-generated method stub

	}
	@Override
	public void noItem(Item i) {
		// TODO Auto-generated method stub

	}
	@Override
	public void itemInvalid(Item i) {
		// TODO Auto-generated method stub

	}
	@Override
	public void itemError(Item i) {
		// TODO Auto-generated method stub

	}
	@Override
	public void inCompleteCommand() {
		// TODO Auto-generated method stub

	}
	@Override
	public void undoRedoUnavailable(CommandWords commandWord) {
		// TODO Auto-generated method stub

	}
	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("New")) {
			//TODO
		}
		else if (e.getActionCommand().equals("Help")) {
			//TODO
		}
		else if (e.getActionCommand().equals("North Room")) {
			p.doCommand(Command.parse("Go North"));
		}
		else if (e.getActionCommand().equals("East Room")) {
			p.doCommand(Command.parse("Go East"));
		}
		else if (e.getActionCommand().equals("West Room")) {
			p.doCommand(Command.parse("Go West"));
		}
		else if (e.getActionCommand().equals("South Room")) {
			p.doCommand(Command.parse("Go South"));
		}
		else if (e.getActionCommand().equals("You are Here")) {
			JDialog abc = new JDialog();
			int def = JOptionPane.showOptionDialog(this, "You are in the current room", "Current Room", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, p.getCurrentRoom().getItems().toArray(), null);
			if (def != JOptionPane.CLOSED_OPTION) {
				p.doCommand(new Command(CommandWords.PICKUP, p.getCurrentRoom().getItems().get(def)));
			}
		}
		else if (e.getActionCommand().equals("UNDO")) {
			p.doCommand(Command.parse("UNDO"));
		}
		else if (e.getActionCommand().equals("REDO")) {
			p.doCommand(Command.parse("REDO"));
		}
		else if (e.getActionCommand().equals("Quit")) {
			System.exit(0);
		}
		update();


	}
}
