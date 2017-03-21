package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level1Map;
import dkeep.logic.Level2Map;
import dkeep.logic.Map;
import dkeep.logic.OgreLevel;
import dkeep.logic.Level.state;

public class DungeonGraphics extends JPanel implements KeyListener {

	//all used images
	private BufferedImage hero_s;
	private BufferedImage hero_d;
	private BufferedImage hero_w;
	private BufferedImage hero_a;
	private BufferedImage hero_key;
	private BufferedImage hero_weapon;
	private BufferedImage guard_a;
	private BufferedImage guard_d;
	private BufferedImage guard_s;
	private BufferedImage guard_w;
	private BufferedImage guard_sleep;
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
	private BufferedImage ogre_attack;
	private BufferedImage ogre_stunned;
	private BufferedImage floor;
	
	private Level currentLevel;
	private state gameState= state.RUNNING;
	
	
	
	/**
	 * Create the panel.
	 */
	public DungeonGraphics() throws IOException {

		addKeyListener(this);
		Map newMap = new Level1Map();
		int randGuard= ThreadLocalRandom.current().nextInt(1,4);
		currentLevel = new DungeonLevel(newMap,randGuard);
		loadImages();
		
	}
	
	public void loadImages() throws IOException
	{
	
			this.guard_s = ImageIO.read(new File("images/goomba_guard.png"));
			this.hero_s = ImageIO.read(new File("images/mario_d.png"));
			this.key = ImageIO.read(new File("images/key.png"));
			this.door_closed = ImageIO.read(new File("images/plant.png"));
			this.ogre_s = ImageIO.read(new File("images/bowser_s.png"));
			this.wall_hor = ImageIO.read(new File("images/wall.png"));
			this.guard_sleep = ImageIO.read(new File("images/goomba_sleeping.png"));
			this.ogre_attack = ImageIO.read(new File("images/bowser_fire.png"));
			this.hero_key = ImageIO.read(new File("images/mario_s.png"));
			this.hero_weapon = ImageIO.read(new File("images/mario_d.png"));
			this.ogre_stunned = ImageIO.read(new File("images/bowser_stunned.png"));
			this.floor =  ImageIO.read(new File("images/floor.png"));

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		char[][] maptoprint = currentLevel.getMap();
		
		for(int i = 0; i < maptoprint.length; i++)
			for(int a= 0; a < maptoprint[i].length;a++)
			{
				
				if(maptoprint[i][a] == 'X')
				{
					g.drawImage(wall_hor, a*70, i*70, this);
				}else if (maptoprint[i][a] == 'H')
				{
					g.drawImage(hero_s, a*70,i*70, this);
				}
				else if(maptoprint[i][a] == 'I')
				{
					g.drawImage(door_closed,a* 70,i*70, this);
				}else if (maptoprint[i][a] == 'G')
				{
					g.drawImage(guard_s,a*70,i*70,this);
				}else if (maptoprint[i][a] == 'O' || maptoprint[i][a] == '$')
				{
					g.drawImage(ogre_s, a*70,i*70, this);
				}else if (maptoprint[i][a] == 'k')
				{
					g.drawImage(key, a*70, i*70,this);
				}else if (maptoprint[i][a] == 'g')
				{
					g.drawImage(guard_sleep, a*70, i*70,this);
				}else if (maptoprint[i][a] == '*')
				{
					g.drawImage(ogre_attack, a*70, i*70,this);
				}else if (maptoprint[i][a] == 'K')
				{
					g.drawImage(hero_key, a*70, i*70,this);
				}else if (maptoprint[i][a] == 'A')
				{
					g.drawImage(hero_weapon, a*70, i*70,this);
				}else if (maptoprint[i][a] == '8')
				{
					g.drawImage(ogre_stunned, a*70, i*70,this);
				}else 
					g.drawImage(floor, a*70, i*70, this);
			}
		
		
		
		
		
		
		
		
	}
	
	public void updateMove(char move)
	{
		if(gameState == state.RUNNING)
		{
			gameState = currentLevel.updateGameStatus(move);
			if(gameState == state.CHANGELEVEL)
			{
				currentLevel = currentLevel.nextLevel(-1);
				gameState = state.RUNNING;
			}
			repaint();
		}
		
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
			
		
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				updateMove('a');
				break;
			case KeyEvent.VK_RIGHT:
				updateMove('d');
				break;
			case KeyEvent.VK_UP:
				updateMove('w');
				break;
			case KeyEvent.VK_DOWN:
				updateMove('s');
				break;
				
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
