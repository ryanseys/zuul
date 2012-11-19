/**
 * 
 * This is the view as well as the controller.
 * 
 * @author Joint programming between Ryan, Jarred and Vinayak
 * For more details on who made what change, please refer to the
 * change sets associated with this file on GitHub
 */
package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import zuul.Command;
import zuul.CommandWords;
import zuul.Direction;
import zuul.Item;
import zuul.Monster;
import zuul.Room;

@SuppressWarnings("serial")
public class TwoDView extends View {
  private static final String PICKUP2 = "Pickup";
  private JButton pickup, undo, redo, northRoom, southRoom, eastRoom, westRoom,
      fight;
  private JLabel currentRoom;
  private JTextArea consoleField;
  private JPanel consolePanel, centralPanel, undoRedoPanel;

  public TwoDView() {
    super();
    undo = new JButton(UNDO);
    redo = new JButton(REDO);
    northRoom = new JButton(GO_NORTH);
    eastRoom = new JButton(GO_EAST);
    westRoom = new JButton(GO_WEST);
    southRoom = new JButton(GO_SOUTH);
    fight = new JButton(FIGHT);
    pickup = new JButton(PICKUP2);
    
    this.setLayout(new GridLayout(3, 3));
    
    undo.addActionListener(this);
    redo.addActionListener(this);
    northRoom.addActionListener(this);
    eastRoom.addActionListener(this);
    westRoom.addActionListener(this);
    southRoom.addActionListener(this);
    pickup.addActionListener(this);
    fight.addActionListener(this);

    consolePanel = new JPanel();
    consolePanel.setLayout(new GridLayout(3, 2));
    inventoryPanel.setLayout(new GridLayout(1, 2));
    centralPanel = new JPanel();
    undoRedoPanel = new JPanel();

    currentRoom = new JLabel("Current Room Actions:");
    centralPanel.add(currentRoom);
    centralPanel.add(pickup);
    centralPanel.add(fight);
    centralPanel.setBackground(new Color(255, 249, 206));

    undoRedoPanel.add(undo);
    undoRedoPanel.add(redo);
    consoleField = new JTextArea();
    consoleField.setEditable(false);
    consolePanel.add(consoleField);

    add(undoRedoPanel);
    add(northRoom);
    add(mapPanel);
    add(westRoom);
    add(centralPanel);
    add(eastRoom);
    add(consolePanel);
    add(southRoom);
    add(inventoryPanel);

    addressMenu.add(quit);

    update();
  }

  @Override
  public void update() {
    Room currentRoom = p.getCurrentRoom();
    if (currentRoom.getExit(Direction.NORTH) == null) northRoom
        .setEnabled(false);
    else northRoom.setEnabled(true);
    if (currentRoom.getExit(Direction.SOUTH) == null) southRoom
        .setEnabled(false);
    else southRoom.setEnabled(true);
    if (currentRoom.getExit(Direction.EAST) == null) eastRoom.setEnabled(false);
    else eastRoom.setEnabled(true);
    if (currentRoom.getExit(Direction.WEST) == null) westRoom.setEnabled(false);
    else westRoom.setEnabled(true);

    if (p.canUndo()) undo.setEnabled(true);
    else undo.setEnabled(false);
    if (p.canRedo()) redo.setEnabled(true);
    else redo.setEnabled(false);

    if (!p.getCurrentRoom().getItems().isEmpty()) pickup.setEnabled(true);
    else pickup.setEnabled(false);

    super.update();
    if (p.getCurrentRoom().hasMonsters()) fight.setEnabled(true);
    else fight.setEnabled(false);
    consoleField.setText(updateConsole());
  }

  /**
   * This method is used to update the console showing the player and monster
   * health.
   * 
   * @return : The string that should be placed onto the console.
   */
  protected String updateConsole() {
    String s = "";
    s += ("Player Health: " + p.getHealth() + "\n");
    if (p.getCurrentRoom().getMonster() != null) s += ("Monster Health: " + p
        .getCurrentRoom().getMonster().getHealth());
    return s;
  }

  /**
   * This method gets an action from a button press and reacts accordingly.
   * 
   * @param e
   *          : The actionEvent when a button or menu item is clicked.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
	  if (e.getActionCommand().equals("Reset")) {
			reset();
		}
		else if (e.getActionCommand().equals("Objective")) {
			JOptionPane.showMessageDialog(this, getObjective());
		}
		else if(e.getActionCommand().equals("Hint")){
			getHint();
		}
		else if (e.getActionCommand().equals("Go North")) {
			p.doCommand(Command.parse("Go North"));
		}
		else if (e.getActionCommand().equals("Go East")) {
			p.doCommand(Command.parse("Go East"));
		}
		else if (e.getActionCommand().equals("Go West")) {
			if(p.getCurrentRoom().getExit(Direction.WEST).getLocked()!=true || unlocked==true){
				p.doCommand(Command.parse("Go West"));
			} else {
				if(!p.getInventory().contains(new Item("Key", true))){
					JOptionPane.showMessageDialog(this, "The Door is locked!\nYou are sure the treasure is just beyond.\nIf only you had a Key..");
				} else {
					JOptionPane.showMessageDialog(this, "You have opened the door!\nYou see the treasure in front of you!");
					unlocked = true;
					p.doCommand(Command.parse("Go West"));
				}
			}
		}
		else if (e.getActionCommand().equals("Go South")) {
			p.doCommand(Command.parse("Go South"));
		}
		else if (e.getActionCommand().equals("Pickup")) {
			pickup.setEnabled(true);
				int popup = JOptionPane.showOptionDialog(this, "You are in the current room", "Current Room", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, p.getCurrentRoom().getItems().toArray(), null);
				if (popup != JOptionPane.CLOSED_OPTION) {
					p.doCommand(new Command(CommandWords.PICKUP, p.getCurrentRoom().getItems().get(popup)));
				}
				if(p.getInventory().contains(new Item("Treasure", true))){
					win();
				}
		}
		else if (e.getActionCommand().equals("Drop")) {
			Item selectedItem = ((Item) inventoryList.getSelectedValue());
			if(selectedItem != null) {
				p.doCommand(new Command(CommandWords.DROP, selectedItem));
				updateMapPanel();
			}
		}
		else if (e.getActionCommand().equals("Fight")) {
			Monster m = p.getCurrentRoom().getMonster();
			p.doCommand(Command.parse("Fight"));
			if(p.getHealth()<=0){
				this.gameDone();
			} else {
				if(p.getCurrentRoom().hasMonsters()){
					m.setHealth(p.getCurrentRoom().getMonster().getHealth());
				} else {
					m.setHealth(0);
				}
				if(m.getHealth()<=0){
					monsterDead(m);
				} else {
					fightPopUp();
				}
			}
		}
		else if (e.getActionCommand().equals("Eat")) {
			Item selectedItem = ((Item) inventoryList.getSelectedValue());
			if(selectedItem != null) {
				p.doCommand(new Command(CommandWords.EAT, selectedItem));
			}
		}
		else if (e.getActionCommand().equals("Inspect")) {
			Item selectedItem = ((Item) inventoryList.getSelectedValue());
			if(selectedItem != null) {
		   		JOptionPane.showMessageDialog(this, selectedItem.getDescription(), "Item", getDefaultCloseOperation(), getImageIcon(selectedItem));
			}
		}
		else if (e.getActionCommand().equals("UNDO")) {
			p.doCommand(Command.parse("UNDO"));
		}
		else if (e.getActionCommand().equals("REDO")) {
			p.doCommand(Command.parse("REDO"));
		}
		else if (e.getActionCommand().equals("Quit")) {
			quit();
		}
		update();
    
  }
}