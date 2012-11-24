import java.awt.GridLayout;

import javax.swing.JFrame;


public class Builder extends JFrame{

	private gameBuilder gb;
	private monsterBuilder mb;
	
	
	public Builder(){
		this.setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		gb = new gameBuilder();
		
		this.add(gb);
		this.setVisible(true);
		while(gb.isDone() == false){
			//wait for gameBuilder to be done
			System.out.println("Waiting for Room to be finished");
		}
		
		mb = new monsterBuilder(gb.getRooms());
		this.add(mb);
		this.remove(gb);
		this.validate();

	}
	
	public static void main(String[] args){
		Builder b = new Builder();
	}

	
}
