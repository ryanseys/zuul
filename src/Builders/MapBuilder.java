package Builders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * This class will create an image of the minimap to be used in the View.
 * 
 * @author Jarred
 */
public class MapBuilder extends JFrame {

  private static final long serialVersionUID = 1L;
  static BufferedImage image = null, overlay = null;
  static BufferedImage combined;
  static BufferedImage player;
  File path = new File("Images");
  static boolean[] b;
  int i = 0;
  static int pp = 9; // player position

  /**
   * The constructor for the map builder.
   * 
   * @param b
   *          : A boolean array used to determine which rooms have been chosen
   *          for use
   * @param room
   *          : An integer correlating the rooms to which room the player is
   *          currently in.
   */
  public MapBuilder(boolean[] b) {

    try {
      image = ImageIO.read(new File(path, "full_map.png"));
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      overlay = ImageIO.read(new File(path, "room_empty.png"));
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      player = ImageIO.read(new File(path, "room_player.png"));
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // create the new image, canvas size is the max. of both image sizes
    int w = Math.max(image.getWidth(), overlay.getWidth());
    int h = Math.max(image.getHeight(), overlay.getHeight());
    combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    b[9] = true; // always true, since this is the start room.

    drawImage(-1, -1); // clear the image if all are true
    // Draw the rooms that are not in use.
    if (b[0] == false) drawImage(0, 0);
    if (b[1] == false) drawImage(90, 0);
    if (b[2] == false) drawImage(180, 0);
    if (b[3] == false) drawImage(270, 0);
    if (b[4] == false) drawImage(0, 54);
    if (b[5] == false) drawImage(90, 54);
    if (b[6] == false) drawImage(180, 54);
    if (b[7] == false) drawImage(270, 54);
    if (b[8] == false) drawImage(0, 108);
    if (b[10] == false) drawImage(180, 108);
    if (b[11] == false) drawImage(270, 108);
    if (b[12] == false) drawImage(0, 162);
    if (b[13] == false) drawImage(90, 162);
    if (b[14] == false) drawImage(180, 162);
    if (b[15] == false) drawImage(270, 162);

    drawPlayer(110, 117);

  }

  /**
   * This method takes in two coordinates, and draws a brown box that covers up
   * the selected room. This will give the total outline of the game.
   * 
   * @param x
   *          : The x coordinate of the room
   * @param y
   *          : The y coordinate of the room
   */
  public void drawImage(int x, int y) {
    if ((x == -1) && (y == -1)) try {
      ImageIO.write(image, "PNG", new File(path, "combined.png"));
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    else {
      // draw the first image only once, after this,
      // use the combined image.
      Graphics g = combined.getGraphics();
      if (i == 0) {
        g.drawImage(image, 0, 0, null);
        i = 1;
      } else g.drawImage(combined, 0, 0, null);
      g.drawImage(overlay, x, y, null);

      // Save as new image
      try {
        ImageIO.write(combined, "PNG", new File(path, "combined.png"));
      }
      catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * @param room
   */
  public void drawPlayer(int room) {
    // Draw the player into whichever room they are in.

    if (room == 0) drawPlayer(20, 8);
    if (room == 1) drawPlayer(110, 8);
    if (room == 2) drawPlayer(200, 8);
    if (room == 3) drawPlayer(290, 8);
    if (room == 4) drawPlayer(20, 62);
    if (room == 5) drawPlayer(110, 62);
    if (room == 6) drawPlayer(200, 62);
    if (room == 7) drawPlayer(290, 62);
    if (room == 8) drawPlayer(20, 116);
    if (room == 9) drawPlayer(110, 116);
    if (room == 10) drawPlayer(200, 116);
    if (room == 11) drawPlayer(290, 116);
    if (room == 12) drawPlayer(20, 170);
    if (room == 13) drawPlayer(110, 170);
    if (room == 14) drawPlayer(200, 170);
    if (room == 15) drawPlayer(290, 170);

  }

  /**
   * This method takes in two coordinates, and draws a blue circle into the
   * selected room.
   * 
   * @param x
   *          : The x coordinate of the player.
   * @param y
   *          : The x coordinate of the player.
   */
  public void drawPlayer(int x, int y) {

    // draw the first image only once, after this,
    // use the combined image.
    try {
      combined = ImageIO.read(new File(path, "combined.png"));
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Graphics g = combined.getGraphics();
    if (i == 0) {
      g.drawImage(image, 0, 0, null);
      i = 1;
    } else g.drawImage(combined, 0, 0, null);
    g.drawImage(player, x, y, null);

    // Save as new image
    try {
      ImageIO
          .write(combined, "PNG", new File(path, "combined_with_player.png"));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
