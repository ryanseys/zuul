/**
 * 
 * This is the view as well as the controller.
 * 
 * @author Joint programming between Ryan, Jarred and Vinayak
 * For more details on who made what change, please refer to the 
 * change sets associated with this file on GitHub
 */
package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import zuul.Command;
import zuul.CommandWords;
import zuul.Direction;
import zuul.Item;
import zuul.Monster;
import zuul.Player;
import zuul.Room;

@SuppressWarnings("serial")
public class ThreeDView extends View 
{
	private JMenuItem undo, redo; 
	private JTextArea healthField;
	
	private Polygon doorWest, doorEast, doorNorth, chest, monster, doorSouth, treasure;
	private JLayeredPane consolePanel;
	private JLabel backgroundLabel;
	
    private JPanel backgroundPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
	private JPanel monsterPanel = new JPanel();
	private JPanel chestPanel = new JPanel();
	private JPanel treasurePanel = new JPanel();
	private JPanel bossPanel = new JPanel();
	private JPanel bossPanel2 = new JPanel();
	private JPanel scene = new JPanel();
	private JPanel fixPanel = new JPanel();
	private JPanel fixPanel2 = new JPanel();
	private JPanel fixPanel3 = new JPanel();

	
	public ThreeDView (Player p) {
		super(p);
		 
	    this.setLayout(new BorderLayout());
	    scene.setLayout(new BorderLayout());
	    consolePanel = new JLayeredPane();
	    scene.add(consolePanel, BorderLayout.CENTER);
	    consolePanel.setBounds(0,0,600,400);
	    scene.setBounds(0,0,600,400);
	    this.add(scene, BorderLayout.CENTER);
	    
	    backgroundLabel = new JLabel(new ImageIcon("Images/background_plain.png"));
	    backgroundPanel.add(backgroundLabel);
	    backgroundPanel.setBounds(0, 0, 850, 900);
	    backgroundPanel.setOpaque(true);

	    consolePanel.add(backgroundPanel, new Integer(0), 0);

	    addPolygons();
	    
	    backgroundLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				handleCoordinates(arg0.getX(), arg0.getY());
			}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override 
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
	    inventoryPanel.setLayout(new GridLayout(2, 1));
	    
	    
	    
	    JPanel interfacePanel = new JPanel();
	    interfacePanel.setLayout(new GridLayout(3, 1));

	    healthField = new JTextArea("Player Health: " + p.getHealth());
	    healthField.setEditable(false);
	    
	    interfacePanel.add(mapPanel);
	    interfacePanel.add(healthField);
	    interfacePanel.add(inventoryPanel);
	    this.add(interfacePanel, BorderLayout.EAST);

	    undo = new JMenuItem ( "Undo" );
	    addressMenu.add( undo );
	    undo.addActionListener(this);
	    
	    redo = new JMenuItem ( "Redo" );
	    addressMenu.add( redo );
	    redo.addActionListener(this);
	    
	    addressMenu.add( quit );
	    
	    update();
	}

	/**
	 * Initializes the areas for various GUI objects.
	 */
	private void addPolygons() {
		doorWest = new Polygon();
	    doorEast = new Polygon();
	    doorNorth = new Polygon();
	    doorSouth = new Polygon();
	    chest = new Polygon();
	    monster = new Polygon();
	    treasure = new Polygon();
	    //TODO still some strange instances where mouse is not found inside polygon
	    
	    doorWest.addPoint(103, 247);
	    doorWest.addPoint(103, 458);
	    doorWest.addPoint(174, 166);
	    doorWest.addPoint(174, 374);
	    
	    doorEast.addPoint(632, 166);
	    doorEast.addPoint(632, 374);
	    doorEast.addPoint(705, 247);
	    doorEast.addPoint(705, 459);
	    
	    doorNorth.addPoint(366, 152);
	    doorNorth.addPoint(366, 314);
	    doorNorth.addPoint(458, 152);
	    doorNorth.addPoint(458, 314);
	    
	    doorSouth.addPoint(363, 495);
	    doorSouth.addPoint(450, 495);
	    doorSouth.addPoint(450, 573);
	    doorSouth.addPoint(363, 573);
	    
	    chest.addPoint(197, 280);
	    chest.addPoint(351, 280);
	    chest.addPoint(351, 430);
	    chest.addPoint(197, 430);
	    
	    monster.addPoint(472, 170);
	    monster.addPoint(609, 170);
	    monster.addPoint(609, 487);
	    monster.addPoint(472, 487);
	    
	    treasure.addPoint(0, 0);
	    treasure.addPoint(600, 0);
	    treasure.addPoint(0, 400);
	    treasure.addPoint(600, 400);
	}

	@Override
	public void update() {
		if(p.canUndo()){
			undo.setEnabled(true);
		} else {
			undo.setEnabled(false);
		}
		if(p.canRedo()){
			redo.setEnabled(true);
		} else {
			redo.setEnabled(false);
		}

		super.update();
		mapPanel.validate();
		updateHealthField();
		setupView();
	}
	

	/**
	 * This method is used to update the console showing the player and monster health.
	 * @return : The string that should be placed onto the console.
	 */
	protected void updateHealthField(){
		String s = "Player Health: " + p.getHealth();
		if (p.getCurrentRoom().hasMonsters()) {
			s += "\nMonster Health: "
					+ p.getCurrentRoom().getMonster().getHealth();
		}
		healthField.setText(s);
	}
	
	@Override
	protected void resetInitialize() {
		Room west = p.getCurrentRoom().getExit(Direction.WEST);
		Item r1 = new Item("Apple", 10, 0, false);
		if (!west.getItems().contains(r1)) {
			west.addItem(r1);
		}
		Item r2 = new Item("Orange", 15, 0, false);
		if (!west.getItems().contains(r2)) {
			west.addItem(r2);
		}
		Item r3 = new Item("Pear", 20, 0, false);
		if (!west.getItems().contains(r3)) {
			west.addItem(r3);
		}
		Room north1 = p.getCurrentRoom().getExit(Direction.NORTH);
		Item r4 = new Item("Bread", 30, 0, false);
		if (!north1.getItems().contains(r4)) {
			north1.addItem(r4);
		}
		Room east = p.getCurrentRoom().getExit(Direction.EAST);
		Monster monster1 = new Monster(Monster.MAX_HEALTH,
				Monster.DEFAULT_LEVEL, "Monster1", east);
		east.removeItem(new Item("Claws", 10, 0, true));
		east.removeItem(new Item("Map", 0, 0, true));
		if (!east.hasMonsters()) {
			east.addMonster(monster1);
			monster1.addItem(new Item("Map", 0, 0, true));
			monster1.addItem(new Item("Claws", 10, 0, true));
		}
		Room south = p.getCurrentRoom().getExit(Direction.SOUTH);
		Monster boss = new Monster(100, 2, "Boss", south);
		south.removeItem(new Item("Key", 0, 0, true));
		south.removeItem(new Item("Flamethrower", 30, 0, true));
		if (!south.hasMonsters()) {
			south.addMonster(boss);
			boss.addItem(new Item("Flamethrower", 30, 0, true));
			boss.addItem(new Item("Key", 0, 0, true));
		}
	}


		
	/**
	 * This method uses the coordinates from the mouse to determine
	 * on which object the user clicked. Once it has determined that,
	 * it calls the appropriate command on the model (the player).
	 * @param x - the x coordinate of the mouse click
	 * @param y - the y coordinate of the mouse click
	 */
	private void handleCoordinates(int x, int y){
		
		if(p.getCurrentRoom().hasItem(new Item("Treasure", 100, 0, true))){
			if(treasure.contains(x, y)){
				win();
			} 
		}
		
		if(doorEast.contains(x, y)){
			p.doCommand(Command.parse("Go East"));
		} else if(doorWest.contains(x, y)){
			if(p.getCurrentRoom().getExit(Direction.WEST).getLocked()!=true || unlocked==true){
				p.doCommand(Command.parse("Go West"));
			} else {
				if(!p.getInventory().contains(new Item("Key", true))){
					JOptionPane.showMessageDialog(this, "The Door is locked!\nYou are sure the treasure is just beyond.\nIf only you had a Key..");
				} else {
					JOptionPane.showMessageDialog(this, "You have opened the door!\nYou see the treasure in front of you!");
					unlocked = true;
					p.getCurrentRoom().getExit(Direction.WEST).setLocked(false);
					p.doCommand(Command.parse("Go West"));
				}
			}
		} else if(doorNorth.contains(x, y)){
			p.doCommand(Command.parse("Go North"));
		} else if(doorSouth.contains(x, y)){
			p.doCommand(Command.parse("Go South"));
		} else if(chest.contains(x, y)){
			int popup;
			if(!p.getCurrentRoom().getItems().isEmpty()){
				popup = JOptionPane.showOptionDialog(this, "This Room has the following items:", "Current Room", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, p.getCurrentRoom().getItems().toArray(), null);
					
			} else {
				String[] options = new String[1];
				options[0] = "OK";
				JOptionPane.showOptionDialog(this, "This room does not have any items in it!", "Current Room", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, null);
				popup = JOptionPane.CLOSED_OPTION;
					
			}
			if (popup != JOptionPane.CLOSED_OPTION) {
				p.doCommand(new Command(CommandWords.PICKUP, p.getCurrentRoom().getItems().get(popup)));
			}
			if(p.getInventory().contains(new Item("Treasure", true))){
				win();
			}
		} else if(monster.contains(x, y) && p.getCurrentRoom().hasMonsters()){
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
		update();
	}
	
	
	/**
	 * Helps the update method with updating view - helps to update 
	 * panels, the update method specializes in updating the menus and
	 * buttons
	 */
	private void setupView(){
		consolePanel.removeAll();
		consolePanel.add(backgroundPanel, new Integer(0), 0);
		
		JLabel fixLabel = new JLabel(new ImageIcon("Images/fix.png"));
		
		JLabel chestLabel = new JLabel(new ImageIcon("Images/chest_in_room.png"));
		if(!p.getCurrentRoom().hasItem(new Item("Treasure", 100, 0, true))){
			chestPanel.add(chestLabel);
			chestPanel.setBounds(210,278, 165, 160);
			chestPanel.setBackground(new Color(185, 122, 87));
			consolePanel.add(chestPanel, new Integer(1), 0);
			fixPanel.add(fixLabel);
			fixPanel.setBackground(Color.black);
			fixPanel.setBounds(249, 278, 5, 5);
			consolePanel.add(fixPanel, new Integer(1), 0);
		} else {
			JLabel treasureLabel = new JLabel(new ImageIcon("Images/treasure_in_room.png"));
			treasurePanel.add(treasureLabel);
			treasurePanel.setBounds(75, 0, 700, 500);
			consolePanel.add(treasurePanel, new Integer(1), 0);
		}
		if(p.getCurrentRoom().hasMonsters()){
			if(p.getCurrentRoom().getMonster().getName().equals("Boss")){
				JLabel bossLabel = new JLabel(new ImageIcon("Images/boss1_in_room.png"));
				JLabel bossLabel2 = new JLabel(new ImageIcon("Images/boss2_in_room.png"));
				bossPanel.add(bossLabel);
				bossPanel2.add(bossLabel2);
				bossPanel.setBackground(new Color(185, 122, 87));
				bossPanel2.setBackground(new Color(69, 43, 29));
				bossPanel.setBounds(487,143, 180, 300);
				bossPanel2.setBounds(380,330, 280, 180);
			    consolePanel.add(bossPanel2, new Integer(1), 0);
			    consolePanel.add(bossPanel, new Integer(1), 0);
				fixPanel2.add(fixLabel);
				fixPanel2.setBackground(Color.black);
				fixPanel2.setBounds(596, 143, 5, 5);
				consolePanel.add(fixPanel2, new Integer(1), 0);
			} else {
				JLabel monsterLabel = new JLabel(new ImageIcon("Images/monster_in_room.png"));
				monsterPanel.add(monsterLabel);
				monsterPanel.setBackground(new Color(185, 122, 87));
				monsterPanel.setBounds(488,210, 180, 300);
			    consolePanel.add(monsterPanel, new Integer(1), 0);   
				fixPanel3.add(fixLabel);
				fixPanel3.setBackground(Color.black);
				fixPanel3.setBounds(596, 210, 5, 5);
				consolePanel.add(fixPanel3, new Integer(1), 0);
			}
		} 
		if(p.getCurrentRoom().getExit(Direction.NORTH)!=null){
			JLabel northLabel = new JLabel(new ImageIcon("Images/north_door.png"));
		    northPanel.add(northLabel);
		    northPanel.setBackground(new Color(185, 122, 87));
		    northPanel.setBounds(385,146, 100, 180);
		    consolePanel.add(northPanel, new Integer(1), 0);
		}
		if(p.getCurrentRoom().getExit(Direction.EAST)!=null){
			JLabel eastLabel = new JLabel(new ImageIcon("Images/east_door.png"));
		    eastPanel.add(eastLabel);
		    eastPanel.setBackground(new Color(185, 122, 87));
		    eastPanel.setBounds(650,158, 80, 310);
		    consolePanel.add(eastPanel, new Integer(1), 1);
		}
		if(p.getCurrentRoom().getExit(Direction.WEST)!=null){
			JLabel westLabel;
			westPanel.removeAll();
			if(p.getCurrentRoom().getExit(Direction.WEST).getLocked()){
				westLabel = new JLabel(new ImageIcon("Images/west_door_locked.png"));
			} else {
				westLabel = new JLabel(new ImageIcon("Images/west_door.png"));
			}
			westPanel.add(westLabel);
		    westPanel.setBackground(new Color(185, 122, 87));
		    westPanel.setBounds(120,158, 80, 310);
		    consolePanel.add(westPanel, new Integer(1), 0);
		}
		if(p.getCurrentRoom().getExit(Direction.SOUTH)!=null){
			JLabel southLabel = new JLabel(new ImageIcon("Images/south_door.png"));
		    southPanel.add(southLabel);
		    southPanel.setBackground(new Color(69, 43, 29));
		    southPanel.setBounds(385,490, 90, 90);
		    consolePanel.add(southPanel, new Integer(1), 0);
		}
	}
}
