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

import dkeep.gui.DungeonGame.StateViewer;
import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level1Map;
import dkeep.logic.Level2Map;
import dkeep.logic.Map;
import dkeep.logic.OgreLevel;
import dkeep.logic.Level.state;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JComboBox;

public class DungeonGraphics extends JPanel implements KeyListener {

	//all used images
	private BufferedImage mario;
	private BufferedImage mario_key;
	private BufferedImage hero_weapon;
	private BufferedImage guard;
	private BufferedImage guard_sleep;
	private BufferedImage wall;
	private BufferedImage lever;
	private BufferedImage key;


	private BufferedImage door;
	private BufferedImage bowser;
	private BufferedImage bowser_fire;
	private BufferedImage bowser_stunned;
	private BufferedImage floor;
	
	private Level currentLevel;

	

	private state gameState= state.RUNNING;
	private GraphicsVariables variables;
	


	
	/**
	 * Create the panel.
	 */
	public DungeonGraphics(GraphicsVariables variables) throws IOException {

		this.variables = variables;
		this.requestFocusInWindow();
		this.setFocusable(true);

		
		addKeyListener(this);
		Map newMap = new Level1Map();	
		variables.setLevel(new DungeonLevel(newMap,this.variables.getGuardTypenmb()));
		this.currentLevel = this.variables.getLevel();
		setLayout(null);
		loadImages();
		
	}
	
	public void loadImages() throws IOException
	{
	
			this.guard = ImageIO.read(new File("images/goomba_guard.png"));
			this.mario = ImageIO.read(new File("images/mario_d.png"));
			this.key = ImageIO.read(new File("images/key.png"));
			this.door = ImageIO.read(new File("images/plant.png"));
			this.bowser = ImageIO.read(new File("images/bowser_s.png"));
			this.wall = ImageIO.read(new File("images/wall.png"));
			this.guard_sleep = ImageIO.read(new File("images/goomba_sleeping.png"));
			this.bowser_fire = ImageIO.read(new File("images/bowser_fire.png"));
			//this.mario_key = ImageIO.read(new File("images/mario_key_s.png"));
			this.hero_weapon = ImageIO.read(new File("images/mario_d.png"));
			this.bowser_stunned = ImageIO.read(new File("images/bowser_stunned.png"));
			this.floor =  ImageIO.read(new File("images/floor.png"));

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		char[][] maptoprint = variables.getMap();
		int sizeWidth = getWidth()/maptoprint.length;
		//int sizeHeight = getHeight()/maptoprint.length;
		
		for(int i = 0; i < maptoprint.length; i++)
			for(int a= 0; a < maptoprint[i].length;a++)
			{
				
				g.drawImage(floor, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				
				if(maptoprint[i][a] == 'X')
				{
					g.drawImage(wall, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
					//TODO
					System.out.println("print");
				}else if (maptoprint[i][a] == 'H')
				{
					g.drawImage(mario, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}
				else if(maptoprint[i][a] == 'I')
				{
					g.drawImage(door,a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'G')
				{
					g.drawImage(guard,a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'O')
				{
					g.drawImage(bowser, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'k')
				{
					g.drawImage(key, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'g')
				{
					g.drawImage(guard_sleep, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == '*')
				{
					g.drawImage(bowser_fire, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'K')
				{
					g.drawImage(mario, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'A')
				{
					g.drawImage(hero_weapon, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == '8')
				{
					g.drawImage(bowser_stunned, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if(maptoprint[i][a] == '$')
				{
					g.drawImage(key, a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}
			
					
			}
		
		
			
		
		
		
		
	}
	
	public void updateMove(char move)
	{
		if(gameState == state.RUNNING)
		{
			Level currentLevel = variables.getLevel();
			gameState = currentLevel.updateGameStatus(move);
			if(gameState == state.CHANGELEVEL)
			{
				variables.setLevel(currentLevel.nextLevel(variables.getOgrenmb()));
				gameState = state.RUNNING;
			}
			repaint();
		}
		else if(gameState == state.LOSE || gameState == state.WIN)
		{
			if(move == 'e')
			{
				try {
				DungeonGame.changeState(StateViewer.MENU);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
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
			try {
				mario = ImageIO.read(new File("images/mario_a.png"));
			} catch (IOException e1) {
		
				e1.printStackTrace();
			}
				updateMove('a');
				break;
			case KeyEvent.VK_RIGHT:
			try {
				
				if(currentLevel.getCurrentMap().getKey().getFound())
				mario = ImageIO.read(new File("images/mario_key_d.png"));
				else mario = ImageIO.read(new File("images/mario_d.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				updateMove('d');
				break;
			case KeyEvent.VK_UP:
			try {
				mario = ImageIO.read(new File("images/mario_w.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				updateMove('w');
				break;
			case KeyEvent.VK_DOWN:
			try {
				if(currentLevel.getCurrentMap().getKey().getFound())
					mario = ImageIO.read(new File("images/mario_key_s.png"));
					else mario = ImageIO.read(new File("images/mario_s.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				updateMove('s');
				break;
			case KeyEvent.VK_ENTER:
			{
				updateMove('e');
			}
				
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
