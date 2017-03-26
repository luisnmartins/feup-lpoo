package dkeep.gui;

import java.awt.Container;
import java.io.IOException;



public class GraphicsState {

	
	private Container framecontentpane;
	private DungeonDesign game;
	private MenuGraphics menu;
	
	private	GraphicsVariables variables;
	private MapEditor editor;
	
	public enum StateViewer{ MENU, GAME, CUSTOM, SETTINGS};
	private StateViewer currentState;
	
	
	public GraphicsState(Container framecontentpane) throws IOException
	{
		//set elements
		this.framecontentpane = framecontentpane;
		variables = new GraphicsVariables();
		menu = new MenuGraphics(variables,this);
		
		
		
		changeState(StateViewer.MENU);
	}
	
	
	public StateViewer getState()
	{
		return currentState;
	}
	
	
	public void changeState(StateViewer state) throws IOException{
		
		currentState = state;
		framecontentpane.removeAll();
		switch(state){
		
			case MENU:
				
				framecontentpane.add(menu);
				break;
				
			case GAME:
				variables.resetMarioimage();
				game = new DungeonDesign(variables, this);
				framecontentpane.add(game);
				game.setBounds(0, 0, 700, 700);
				game.setFocusable(true);
				game.requestFocusInWindow();
				break;
			
			case CUSTOM:
				variables.resetMarioimage();
				editor = new MapEditor(variables, this);
				framecontentpane.add(editor);
				editor.setBounds(0,0,700,700);
				break;
						default:
				break;
				
		}
		framecontentpane.revalidate();
		framecontentpane.repaint();	
	}
	
}
