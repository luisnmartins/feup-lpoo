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
	private GraphicsVariables variables;
	
	/**
	 * Create the panel.
	 */
	public MenuGraphics(GraphicsVariables variables) {
		
		this.variables = variables;
		
		settings = new SettingsDialog(this.variables);
		
		
		JButton btnNewgame = new JButton("New Game");
		
		btnNewgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 settings.setVisible(true);
				//DungeonGame.changeState(StateViewer.SETTINGS);
			}
		});
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(300)
					.addComponent(btnNewgame, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(315)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(300)
					.addComponent(btnNewgame, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
		
		

	}
	
	
}
