package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DungeonGraphics extends JPanel {

	//all used images
	private BufferedImage hero_s;
	private BufferedImage hero_d;
	private BufferedImage hero_w;
	private BufferedImage hero_a;
	private BufferedImage guard_a;
	private BufferedImage guard_d;
	private BufferedImage guard_s;
	private BufferedImage guard_w;
	private BufferedImage wall_vert;
	private BufferedImage wall_hor;
	private BufferedImage wall_corner1;
	private BufferedImage wall_corner2;
	private BufferedImage wall_corner3;
	private BufferedImage wall_corner4;
	private BufferedImage lever;
	private BufferedImage key;
	private BufferedImage door_closed;
	private BufferedImage door_open;
	private BufferedImage ogre_w;
	private BufferedImage ogre_s;
	private BufferedImage ogre_a;
	private BufferedImage ogre_d;
	
	private Graphics gameGraphics;
	
	
	
	/**
	 * Create the panel.
	 */
	public DungeonGraphics() throws IOException {

		loadImages();
		
	}
	
	public void loadImages() throws IOException
	{
	
			this.guard_s = ImageIO.read(new File("images/guard_s.png"));
			this.guard_a = ImageIO.read(new File("images/guard(a).png"));

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(guard_s,0,0,this);
		g.drawImage(guard_a, 1, 1, this);
		
	}

}
