package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class newMap extends JPanel implements MouseListener{

	
	private BufferedImage mario,wall,floor,bowser,key,plant;
	private int x_pos,y_pos;
	private char[][] map;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public newMap(){
		
		super();
		try {
			loadImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(this);
		
	}
	
	
	public void loadImages() throws IOException
	{
		
		this.mario = ImageIO.read(new File("images/mario_d.png"));
		this.key = ImageIO.read(new File("images/key.png"));
		this.bowser = ImageIO.read(new File("images/bowser_s.png"));
		this.wall = ImageIO.read(new File("images/wall.png"));
		this.floor =  ImageIO.read(new File("images/floor.png"));
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		x_pos =e.getX();
		 y_pos = e.getY();
		System.out.println(x_pos+","+y_pos);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getXPos()
	{
		return this.x_pos;
	}
	
	public int getYPos()
	{
		return this.y_pos;
	}

}
