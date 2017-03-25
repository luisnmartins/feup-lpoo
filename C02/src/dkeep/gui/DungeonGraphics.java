package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dkeep.gui.GraphicsState.StateViewer;
import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level1Map;

import dkeep.logic.Map;

import dkeep.logic.Level.state;


public class DungeonGraphics extends JPanel implements KeyListener,MouseListener, MouseMotionListener{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

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
			

		
		
	}
	
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		variables.getLevel().printMap();
		char[][] maptoprint = variables.getMap();
		int sizeWidth = getWidth()/maptoprint[0].length;
		int sizeHeight = getHeight()/maptoprint.length;
		
		for(int i = 0; i < maptoprint.length; i++)
			for(int a= 0; a < maptoprint[i].length;a++)
			{
				g.drawImage(variables.getImage(' '), a*sizeWidth, i*sizeHeight, sizeWidth, sizeHeight, this);
				g.drawImage(variables.getImage(maptoprint[i][a]), a*sizeWidth, i*sizeHeight, sizeWidth, sizeHeight, this);
				
			}
		
	}
	

	public void updateMove(char move)
	{
		if(gameState == state.RUNNING)
		{
			Level currentLevel = variables.getLevel();
			gameState = currentLevel.updateGameStatus(move);
			
			if(gameState == state.CHANGELEVEL){
				variables.setLevel(currentLevel.nextLevel(variables.getOgrenmb()));
				JOptionPane.showMessageDialog(getRootPane(), "Go, go, go!! You're in the next level", "Next Level", JOptionPane.INFORMATION_MESSAGE);
				
				gameState = state.RUNNING;
			}
			repaint();
		}
		switch(gameState)
		{
			case LOSE:
				JOptionPane.showMessageDialog(getRootPane(), "You Lose!! Try Again", "Loser!!", JOptionPane.ERROR_MESSAGE);
			try {
				graphicsst.changeState(StateViewer.MENU);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
				break;
			case WIN:
				JOptionPane.showMessageDialog(getRootPane(), "Congratzz!! You're a Hero!!", "Good Job!!", JOptionPane.INFORMATION_MESSAGE);
				try {
					graphicsst.changeState(StateViewer.MENU);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				break;
		default:
			break;
		}
		
		repaint();
		
			
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
			
		if(gameState != state.LOSE && gameState != state.WIN)
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				variables.updateMario('a');
				updateMove('a');
				break;
				
			case KeyEvent.VK_RIGHT:
				variables.updateMario('d');
				updateMove('d');
				break;
				
			case KeyEvent.VK_UP:
			variables.updateMario('w');
				updateMove('w');
				break;
				
			case KeyEvent.VK_DOWN:
				variables.updateMario('s');
				updateMove('s');
				break;
				
			case KeyEvent.VK_ENTER:
				updateMove('e');
				break;
				
			case KeyEvent.VK_N:
				updateMove('n');
				break;
				
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



	@Override
	public void mouseDragged(MouseEvent e) {
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
	public void mouseMoved(MouseEvent e) {
		
		
	}
	


}
