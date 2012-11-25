import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import View.View;


public class Builder extends JFrame{

	private roomBuilder rb;
	private monsterBuilder mb;
	private itemBuilder ib;
	
	
	public Builder(){
		this.setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		rb = new roomBuilder();
		
		this.add(rb);
		this.setVisible(true);
		while(rb.isDone() == false){
			//wait for gameBuilder to be done
			System.out.println("Waiting for roomBuilder to be finished");
		}
		
		mb = new monsterBuilder(rb.getRooms());
		this.add(mb);
		this.remove(rb);
		this.validate();
		while(mb.isDone == false){
			//wait for monsterBuilder to be done
			System.out.println("Waiting for monsterBuilder to be finished");
		}
		
		ib = new itemBuilder(rb.getRooms());
		this.add(ib);
		this.remove(mb);
		this.validate();
		while(ib.isDone() == false){
			//wait for itemBuilder to be done
			System.out.println("Waiting for itemBuilder to be finished");
		}
		this.remove(ib);

		  View view = View.getInstance();
	      view.update();
	      view.setVisible(true);
	}
	
	public static void main(String[] args){
		Builder b = new Builder();
	}

	
}
