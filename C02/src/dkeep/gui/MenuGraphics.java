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
		btnNewgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 settings.setVisible(true);
				//DungeonGame.changeState(StateViewer.SETTINGS);
			}
		});
		
		
		JButton btnExit = new JButton("Exit");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(158)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnExit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewgame, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(182, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(btnNewgame)
					.addGap(91)
					.addComponent(btnExit)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		

	}
	
	
}
