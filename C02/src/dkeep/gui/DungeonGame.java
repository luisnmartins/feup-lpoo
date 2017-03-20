package dkeep.gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DungeonGame {

	private JFrame frame;
	

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
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DungeonGraphics game = new DungeonGraphics();
		
		frame.getContentPane().add(game);
		
		
		//BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
		//JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		//add(picLabel);
	}

}
