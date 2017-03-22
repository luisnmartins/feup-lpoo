package dkeep.gui;

import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;


public class GraphicsState {

	
	private Container framecontentpane;
	private DungeonDesign game;
	private MenuGraphics menu;
	private SettingsDialog settings;
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
		settings = new SettingsDialog(variables, this);
		
		
		changeState(StateViewer.MENU);
	}
	
	
	public StateViewer getState()
	{
		return currentState;
	}
	
	
	public void changeState(StateViewer state) throws IOException
	{
		
		currentState = state;
		switch(state)
		{
			case MENU:
			{
				framecontentpane.removeAll();
				framecontentpane.add(menu);
				framecontentpane.revalidate();
				framecontentpane.repaint();
				
				break;
			}
			case GAME:
			{
				game = new DungeonDesign(variables, this);
				framecontentpane.removeAll();
				framecontentpane.add(game);
				framecontentpane.revalidate();
				framecontentpane.repaint();
				game.setBounds(0, 0, 700, 700);
				game.setFocusable(true);
				game.requestFocusInWindow();
				break;
			}
			case CUSTOM:
			{
				editor = new MapEditor(variables, this);
				framecontentpane.removeAll();
				editor.setBounds(0,0,700,700);
				framecontentpane.add(editor);
				framecontentpane.revalidate();
				framecontentpane.repaint();
				break;
			}
			default:
				break;
				
		}
		
	}
	
}
