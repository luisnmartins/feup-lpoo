package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.gui.DungeonGame.StateViewer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuGraphics extends JPanel {

	private SettingsDialog settings;
	
	/**
	 * Create the panel.
	 */
	public MenuGraphics() {
		
		
		
		settings = new SettingsDialog();
		
		
		JButton btnNewgame = new JButton("New Game");
		btnNewgame.setBounds(300, 300, 95, 23);
		
		btnNewgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 settings.setVisible(true);
				//DungeonGame.changeState(StateViewer.SETTINGS);
			}
		});
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(315, 340, 65, 23);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		setLayout(null);
		add(btnExit);
		add(btnNewgame);
		
		

	}
	
	
}
