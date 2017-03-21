package dkeep.gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DungeonGame {

	private static JFrame frame;
	private static DungeonGraphics game;
	private static MenuGraphics menu;
	private static SettingsDialog settings;
	private static StateViewer viewState;
	static int Ogrenmb=2, GuardTypenmb=1;
	public enum StateViewer{ MENU, GAME, CUSTOM, SETTINGS};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonGame window = new DungeonGame();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		menu = new MenuGraphics();
		settings = new SettingsDialog();
		
		frame.setBounds(0, 0, 700, 700);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		viewState = state;
		Container contentpane = frame.getContentPane();
		switch(viewState)
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
				game = new DungeonGraphics();
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
				break;
			}
			default:
				break;
				
		}
		
	}
	

	
	
	


}
