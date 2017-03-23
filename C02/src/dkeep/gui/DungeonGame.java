package dkeep.gui;

import java.awt.Container;
import java.awt.EventQueue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class DungeonGame {



	private JFrame frmSuperMarioDungeon;

	


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
		
		frmSuperMarioDungeon = new JFrame();
		frmSuperMarioDungeon.setTitle("Super Mario Dungeon");
		
		
		frmSuperMarioDungeon.setBounds(0, 0, 700, 700);
		frmSuperMarioDungeon.setFocusable(true);
		frmSuperMarioDungeon.requestFocusInWindow();
		frmSuperMarioDungeon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(menu);
		
		
		GraphicsState temp_graphics = new GraphicsState(frmSuperMarioDungeon.getContentPane());
		


	}
	
	



	
	
	


}
