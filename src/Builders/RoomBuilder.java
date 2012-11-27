package Builders;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import zuul.Room;


public class RoomBuilder extends JPanel implements ActionListener{

  private static final long serialVersionUID = 1L;
  private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b10, b11, b12, b13, b14, b15;
	private JButton done;
	private JPanel b9;
	private boolean[] rooms;
	MapBuilder mb;
	boolean isDone = false;
	
	public RoomBuilder(){
		this.setLayout(new GridLayout(4, 4));
		initButtons();
		rooms = new boolean[16];
		initRooms();
		mb = new MapBuilder(rooms);
	}
	
	public RoomBuilder(int defaultRooms){
		rooms = new boolean[16];
		initRooms();
		setDefaultRooms();
		mb = new MapBuilder(rooms);
	}
	
	public void initButtons(){

		b0 = new JButton(); 
		b1 = new JButton();  
		b2 = new JButton(); 
		b3 = new JButton(); 
		b4 = new JButton(); 
		b5 = new JButton(); 
		b6 = new JButton(); 
		b7 = new JButton(); 
		b8 = new JButton(); 
		
		b9 = new JPanel(); 
		
		b10 = new JButton(); 
		b11 = new JButton(); 
		b12 = new JButton(); 
		b13 = new JButton(); 
		b14 = new JButton(); 
		b15 = new JButton(); 
		
		b9.setLayout(new FlowLayout());
		JTextArea f = new JTextArea("Now editing: Rooms\nThis is the starting room.");
		f.setEditable(false);
		b9.add(f);
		done = new JButton();
		done.setText("Click here if done editing Rooms");
		b9.add(done);
		
		done.addActionListener(this);
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this); 
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this); 
		b8.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
		b15.addActionListener(this);
		
		this.add(b0);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);
		this.add(b7);
		this.add(b8);
		this.add(b9);
		this.add(b10);
		this.add(b11);
		this.add(b12);
		this.add(b13);
		this.add(b14);
		this.add(b15);
	}

	public void initRooms(){
		rooms[0] = false;
		rooms[1] = false;
		rooms[2] = false;
		rooms[3] = false;
		rooms[4] = false;
		rooms[5] = false;
		rooms[6] = false;
		rooms[7] = false;
		rooms[8] = false;
		rooms[9] = true;	//always true, starting room
		rooms[10] = false;
		rooms[11] = false;
		rooms[12] = false;
		rooms[13] = false;
		rooms[14] = false;
		rooms[15] = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b0){
			if(b0.getText()==""){
				b0.setText("Room Selected");
				rooms[0] = true;
			} else {
				b0.setText("");
				rooms[0] = false;
			}
		} else if(e.getSource() == b1){
			if(b1.getText()==""){
				b1.setText("Room Selected");
				rooms[1] = true;
			} else {
				b1.setText("");
				rooms[1] = false;
			}
		} else if(e.getSource() == b2){
			if(b2.getText()==""){
				b2.setText("Room Selected");
				rooms[2] = true;
			} else {
				b2.setText("");
				rooms[2] = false;
			}
		} else if(e.getSource() == b3){
			if(b3.getText()==""){
				b3.setText("Room Selected");
				rooms[3] = true;
			} else {
				b3.setText("");
				rooms[3] = false;
			}
		} else if(e.getSource() == b4){
			if(b4.getText()==""){
				b4.setText("Room Selected");
				rooms[4] = true;
			} else {
				b4.setText("");
				rooms[4] = false;
			}
		} else if(e.getSource() == b5){
			if(b5.getText()==""){
				b5.setText("Room Selected");
				rooms[5] = true;
			} else {
				b5.setText("");
				rooms[5] = false;
			}
		} else if(e.getSource() == b6){
			if(b6.getText()==""){
				b6.setText("Room Selected");
				rooms[6] = true;
			} else {
				b6.setText("");
				rooms[6] = false;
			}
		} else if(e.getSource() == b7){
			if(b7.getText()==""){
				b7.setText("Room Selected");
				rooms[7] = true;
			} else {
				b7.setText("");
				rooms[7] = false;
			}
		} else if(e.getSource() == b8){
			if(b8.getText()==""){
				b8.setText("Room Selected");
				rooms[8] = true;
			} else {
				b8.setText("");
				rooms[8] = false;
			}
		} else if(e.getSource() == b10){
			if(b10.getText()==""){
				b10.setText("Room Selected");
				rooms[10] = true;
			} else {
				b10.setText("");
				rooms[10] = false;
			}
		} else if(e.getSource() == b11){
			if(b11.getText()==""){
				b11.setText("Room Selected");
				rooms[11] = true;
			} else {
				b11.setText("");
				rooms[11] = false;
			}
		} else if(e.getSource() == b12){
			if(b12.getText()==""){
				b12.setText("Room Selected");
				rooms[12] = true;
			} else {
				b12.setText("");
				rooms[12] = false;
			}
		} else if(e.getSource() == b13){
			if(b13.getText()==""){
				b13.setText("Room Selected");
				rooms[13] = true;
			} else {
				b13.setText("");
				rooms[13] = false;
			}
		} else if(e.getSource() == b14){
			if(b14.getText()==""){
				b14.setText("Room Selected");
				rooms[14] = true;
			} else {
				b14.setText("");
				rooms[14] = false;
			}
		} else if(e.getSource() == b15){
			if(b15.getText()==""){
				b15.setText("Room Selected");
				rooms[15] = true;
			} else {
				b15.setText("");
				rooms[15] = false;
			}
		} else if (e.getSource() == done){
			System.out.println("Done with room building");
			MonsterBuilder monBuil = new MonsterBuilder(rooms);
		
			//this.setVisible(false);
			isDone = true;
				Room[] roomsArray = monBuil.getRooms();
				if(roomsArray[0].hasMonsters()){
					System.out.println("ROOM HAS MONSTERS!");
				}
				
			
			
		}
		mb = new MapBuilder(rooms); 
		
	}
	public boolean[] getRooms(){
		return rooms;
	}
	
	public void setDefaultRooms(){
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
	
	public MapBuilder getMapBuilder(){
		return mb;
	}
}
