package dkeep.gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class DungeonGame {

	private static JFrame frmSuperMarioDungeon;
	private static DungeonGraphics game;
	private static MenuGraphics menu;
	private static SettingsDialog settings;
	private static GraphicsVariables variables;
	private static MapEditor editor;
	public enum StateViewer{ MENU, GAME, CUSTOM, SETTINGS};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonGame window = new DungeonGame();
					window.frmSuperMarioDungeon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DungeonGame() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException{
		
		//set elements
		variables = new GraphicsVariables();
		frmSuperMarioDungeon = new JFrame();
		frmSuperMarioDungeon.setTitle("Super Mario Dungeon");
		menu = new MenuGraphics(variables);
		settings = new SettingsDialog(variables);
		editor = new MapEditor();
		
		frmSuperMarioDungeon.setBounds(0, 0, 700, 700);
		frmSuperMarioDungeon.setFocusable(true);
		frmSuperMarioDungeon.requestFocusInWindow();
		frmSuperMarioDungeon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(menu);
		
		changeState(StateViewer.MENU);
		
		//frame.setVisible(true);
		
		
		
		/*DungeonGraphics game = new DungeonGraphics();
		
		
		game.setFocusable(true);
		game.requestFocusInWindow();*/
		
		
		//BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
		//JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		//add(picLabel);
	}
	
	
	public static void changeState(StateViewer state) throws IOException
	{
		
		Container contentpane = frmSuperMarioDungeon.getContentPane();
		switch(state)
		{
			case MENU:
			{
				contentpane.removeAll();
				contentpane.add(menu);
				contentpane.revalidate();
				contentpane.repaint();
				break;
			}
			case GAME:
			{
				game = new DungeonGraphics(variables);
				contentpane.removeAll();
				contentpane.add(game);
				contentpane.revalidate();
				contentpane.repaint();
				game.setBounds(0, 0, 700, 700);
				game.setFocusable(true);
				game.requestFocusInWindow();
				break;
			}
			case CUSTOM:
			{
				contentpane.removeAll();
				contentpane.add(editor);
				contentpane.revalidate();
				contentpane.repaint();
				break;
			}
			default:
				break;
				
		}
		
	}
	


	
	
	


}
