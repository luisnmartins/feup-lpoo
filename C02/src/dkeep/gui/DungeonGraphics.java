package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;



import javax.swing.JPanel;

import dkeep.gui.GraphicsState.StateViewer;
import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level1Map;

import dkeep.logic.Map;

import dkeep.logic.Level.state;


public class DungeonGraphics extends JPanel implements KeyListener,MouseListener{

	
	
	private Level currentLevel;

	private state gameState= state.RUNNING;
	private GraphicsVariables variables;
	private GraphicsState graphicsst;
	


	
	/**
	 * Create the panel.
	 */
	public DungeonGraphics(GraphicsVariables variables, GraphicsState graphicsst) throws IOException {

		this.variables = variables;
		
		this.graphicsst = graphicsst;
		addMouseListener(this);
		this.requestFocusInWindow();
		this.setFocusable(true);

		addKeyListener(this);

		
		if(graphicsst.getState() == StateViewer.GAME)
		{	
			Map newMap = new Level1Map();	
			variables.setLevel(new DungeonLevel(newMap,this.variables.getGuardTypenmb()));
		}
			this.currentLevel = this.variables.getLevel();

		
		
	}
	
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		variables.getLevel().printMap();
		char[][] maptoprint = variables.getMap();
		int sizeWidth = getWidth()/maptoprint.length;
		
		
		for(int i = 0; i < maptoprint.length; i++)
			for(int a= 0; a < maptoprint[i].length;a++)
			{
				
				g.drawImage(variables.getFloor(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				
				if(maptoprint[i][a] == 'X')
				{
					g.drawImage(variables.getWall(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
					
					
				}else if (maptoprint[i][a] == 'H')
				{
					g.drawImage(variables.getMario(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}
				else if(maptoprint[i][a] == 'I')
				{
					g.drawImage(variables.getDoor(),a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'G')
				{
					
					g.drawImage(variables.getGuard(),a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'O')
				{
					
					g.drawImage(variables.getBowser(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'k')
				{
					g.drawImage(variables.getKey(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'g')
				{
					g.drawImage(variables.getGuardSleep(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == '*')
				{
					g.drawImage(variables.getBowserFire(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'K')
				{
					g.drawImage(variables.getMario(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == 'A')
				{
					g.drawImage(variables.getMario(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if (maptoprint[i][a] == '8')
				{
					g.drawImage(variables.getBowserStunned(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
				}else if(maptoprint[i][a] == '$')
				{
					g.drawImage(variables.getCoin(), a*sizeWidth, i*sizeWidth, sizeWidth, sizeWidth, this);
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
				graphicsst.changeState(StateViewer.MENU);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else if(move == 'n')
			{
				try{
					graphicsst.changeState(StateViewer.GAME);
				} catch (IOException e) {
					
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
			variables.updateMario('a', false);
				updateMove('a');
				break;
			case KeyEvent.VK_RIGHT:
			if(currentLevel.getCurrentMap().getKey().getFound())
			variables.updateMario('d', true);
			else variables.updateMario('d', false);
				updateMove('d');
				break;
			case KeyEvent.VK_UP:
			variables.updateMario('w', false);
				updateMove('w');
				break;
			case KeyEvent.VK_DOWN:
			if(currentLevel.getCurrentMap().getKey().getFound())
				variables.updateMario('s', true);
				else variables.updateMario('s', false);
				updateMove('s');
				break;
			case KeyEvent.VK_ENTER:
			{
				updateMove('e');
				break;
			}
			case KeyEvent.VK_N:
			{
				updateMove('n');
				break;
			}
				
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(graphicsst.getState() != StateViewer.CUSTOM)
			return;
		int mouseX=e.getX();
		 int mouseY = e.getY();
		int x = mouseX/(this.getWidth()/variables.getHorMapSize());
		int y = mouseY/(this.getHeight()/variables.getVerMapSize());
		if(x < variables.getHorMapSize() &&  y < variables.getVerMapSize())
		{
			variables.editMap(y, x, this.variables.getSelectedElement());
			variables.getLevel().printMap();
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
	


}
