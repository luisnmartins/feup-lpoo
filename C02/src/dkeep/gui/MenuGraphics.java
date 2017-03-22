package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.gui.DungeonGame.StateViewer;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

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
		btnNewgame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnNewgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 settings.setVisible(true);
				//DungeonGame.changeState(StateViewer.SETTINGS);
			}
		});
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		
		JButton btnEditor = new JButton("Editor");
		btnEditor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					
					DungeonGame.changeState(StateViewer.CUSTOM);
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(300)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewgame, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditor, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(300)
					.addComponent(btnNewgame)
					.addGap(17)
					.addComponent(btnEditor)
					.addGap(17)
					.addComponent(btnExit))
		);
		setLayout(groupLayout);
		
		

	}
}
