import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class mapBuilder extends JFrame {

	  BufferedImage image = null, overlay = null;
	  BufferedImage combined;
	  BufferedImage player;
		File path = new File("Images");
		static boolean[] b;
		int i = 0;
		static int pp = 9; //player position
		public mapBuilder(boolean[] b, int room){

			try {
				image = ImageIO.read(new File(path, "full_map.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				overlay = ImageIO.read(new File(path, "room_empty.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				player = ImageIO.read(new File(path, "room_player.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// create the new image, canvas size is the max. of both image sizes
			int w = Math.max(image.getWidth(), overlay.getWidth());
			int h = Math.max(image.getHeight(), overlay.getHeight());
			combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			
			b[9] = true;

			drawImage(-1, -1);
			if(b[0] == false) drawImage(0,0);	
			if(b[1] == false) drawImage(90,0);
			if(b[2] == false) drawImage(180,0);	
			if(b[3] == false) drawImage(270,0);	
			if(b[4] == false) drawImage(0,60);	
			if(b[5] == false) drawImage(90,60);	
			if(b[6] == false) drawImage(180,60);
			if(b[7] == false) drawImage(270,60);	
			if(b[8] == false) drawImage(0,120);
			if(b[10] == false) drawImage(180,120);
			if(b[11] == false) drawImage(270,120);
			if(b[12] == false) drawImage(0,180);
			if(b[13] == false) drawImage(90,180);
			if(b[14] == false) drawImage(180,180);
			if(b[15] == false) drawImage(270,180);
			
			if(room == 0) drawPlayer(20, 10);
			if(room == 1) drawPlayer(110, 10);
			if(room == 2) drawPlayer(200, 10);
			if(room == 3) drawPlayer(290, 10);
			if(room == 4) drawPlayer(20, 70);
			if(room == 5) drawPlayer(110, 70);
			if(room == 6) drawPlayer(200, 70);
			if(room == 7) drawPlayer(290, 70);
			if(room == 8) drawPlayer(20, 130);
			if(room == 9) drawPlayer(110,130);
			if(room == 10) drawPlayer(200, 130);
			if(room == 11) drawPlayer(290, 130);
			if(room == 12) drawPlayer(20, 190);
			if(room == 13) drawPlayer(110, 190);
			if(room == 14) drawPlayer(200, 190);
			if(room == 15) drawPlayer(290, 190);

		}


	public static void main(String[] args){
		b = new boolean[16];
		initArray();
		mapBuilder mb = new mapBuilder(b, pp);
	}
	
	private static void initArray() {
		b[0] = true;
		b[1] = true;
		b[2] = false;
		b[3] = false;
		b[4] = false;
		b[5] = true;
		b[6] = false;
		b[7] = false;
		b[8] = true;
		b[10] = true;
		b[11] = false;
		b[12] = false;
		b[13] = true;
		b[14] = false;
		b[15] = false;
	}


	public void drawImage(int x, int y){
		if(x == -1 && y == -1){
			try {
				ImageIO.write(image, "PNG", new File(path, "combined.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			if (i == 0) {
				g.drawImage(image, 0, 0, null);
				i = 1;
			} else {
				g.drawImage(combined, 0, 0, null);
			}
			g.drawImage(overlay, x, y, null);

			// Save as new image
			try {
				ImageIO.write(combined, "PNG", new File(path, "combined.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void drawPlayer(int x, int y){

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		if(i == 0){
			g.drawImage(image, 0, 0, null);
			i=1;
		} else {
			g.drawImage(combined, 0, 0, null);
		}
		g.drawImage(player, x, y, null);

		// Save as new image
		try {
			ImageIO.write(combined, "PNG", new File(path, "combined_with_player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
