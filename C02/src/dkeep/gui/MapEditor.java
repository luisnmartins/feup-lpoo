package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Cursor;

public class MapEditor extends JPanel {

	/**
	 * Create the panel.
	 */
	public MapEditor() {
		
		JToggleButton tglbtnMario = new JToggleButton("Mario");
		tglbtnMario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JToggleButton tglbtnDoor = new JToggleButton("Door");
		tglbtnDoor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JToggleButton tglbtnKey = new JToggleButton("Key");
		tglbtnKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JToggleButton tglbtnFloor = new JToggleButton("Floor");
		tglbtnFloor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JToggleButton tglbtnBowser = new JToggleButton("Bowser");
		tglbtnBowser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JToggleButton tglbtnWall = new JToggleButton("Wall");
		tglbtnWall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(tglbtnMario);
		add(tglbtnDoor);
		add(tglbtnKey);
		add(tglbtnWall);
		add(tglbtnFloor);
		add(tglbtnBowser);

	}

}
