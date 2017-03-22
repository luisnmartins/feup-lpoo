package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.gui.DungeonGame.StateViewer;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MapEditor extends JPanel {

	private char[][] map;
	private GraphicsVariables variables;
	/**
	 * Create the panel.
	 */
	public MapEditor() {
		
		JPanel panel = new JPanel();
		
		
		ButtonGroup choices = new ButtonGroup();
		
		
		JToggleButton tglbtnMario = new JToggleButton();
		//tglbtnMario.setBorder(BorderFactory.createEmptyBorder());
		panel.add(tglbtnMario);
		
		tglbtnMario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tglbtnMario.setIcon(new ImageIcon("images/mario_d.png"));

		
		JToggleButton tglbtnDoor = new JToggleButton();
		panel.add(tglbtnDoor);
		tglbtnDoor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tglbtnDoor.setIcon(new ImageIcon("images/plant.png"));

		
		
		JToggleButton tglbtnKey = new JToggleButton();
		panel.add(tglbtnKey);
		tglbtnKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnKey.setIcon(new ImageIcon("images/key.png"));
		
		
		JToggleButton tglbtnWall = new JToggleButton();
		panel.add(tglbtnWall);
		tglbtnWall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnWall.setIcon(new ImageIcon("images/wall.png"));
		

		JToggleButton tglbtnFloor = new JToggleButton();
		panel.add(tglbtnFloor);
		tglbtnFloor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnFloor.setIcon(new ImageIcon("images/floor.png"));

		JToggleButton tglbtnBowser = new JToggleButton();
		panel.add(tglbtnBowser);
		tglbtnBowser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tglbtnBowser.setIcon(new ImageIcon("images/bowser_s.png"));

		
		choices.add(tglbtnBowser);
		choices.add(tglbtnFloor);
		choices.add(tglbtnWall);
		choices.add(tglbtnKey);
		choices.add(tglbtnDoor);
		choices.add(tglbtnMario);
		
		
		JButton btnPlay = new JButton("Play");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					try {
					
					DungeonGame.changeState(StateViewer.MENU);
				
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
					.addGap(36)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(312, Short.MAX_VALUE)
					.addComponent(btnPlay)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlay)
						.addComponent(btnCancel)))
		);
		setLayout(groupLayout);

	}
}
