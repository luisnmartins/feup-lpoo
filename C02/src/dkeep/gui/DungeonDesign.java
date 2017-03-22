package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level.state;
import dkeep.logic.Level1Map;
import dkeep.logic.Map;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import java.awt.Color;

public class DungeonDesign extends JPanel{

	
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JSlider slider;
	private JComboBox comboBox;
	private DungeonGraphics panel; 
	private JButton btnNewGame;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	
	/*private JLabel lbStatus;
	private JButton gameStart;
	private JButton btnLeft ;
	private JButton btnRight ;
	private JButton btnUp ;
	private JButton btnDown ;*/
	
	
	
	private state gameState;
	private GraphicsVariables variables;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DungeonDesign window = new DungeonDesign();
					window.frmDungeonkeep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public DungeonDesign(GraphicsVariables variables) throws IOException {
		
		this.variables = variables;
		setLayout(null);
		
		lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(32, 30, 121, 16);
		add(lblNumberOfOgres);
		
		lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(32, 83, 115, 16);
		add(lblGuardPersonality);
		
		slider = new JSlider();
		slider.setBounds(159, 30, 199, 37);
		add(slider);
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMaximum(5);
		slider.setMinimum(1);
		slider.setValue(2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1 - Drunken", "2 -Suspicious", "3 - Rookie"}));
		comboBox.setBounds(164, 79, 173, 27);
		add(comboBox);
		
		
		
		
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.updateMove('a');
				panel.requestFocusInWindow();
			}
		});
		btnLeft.setBounds(410, 249, 117, 29);
		add(btnLeft);
		btnLeft.setEnabled(false);
		
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.updateMove('w');
				panel.requestFocusInWindow();
			}
		});
		btnUp.setBounds(464, 208, 117, 29);
		add(btnUp);
		btnUp.setEnabled(false);
		
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.updateMove('d');
				panel.requestFocusInWindow();
			}
		});
		btnRight.setBounds(531, 249, 117, 29);
		add(btnRight);
		btnRight.setEnabled(false);
		
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateMove('s');
				panel.requestFocusInWindow();
			}
		});
		btnDown.setBounds(464, 290, 117, 29);
		add(btnDown);
		btnDown.setEnabled(false);
		
		
		initialize();
		
		
		
		
	}

	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		
		

		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				variables.setGuardTypenmb(comboBox.getSelectedIndex()+1);	
				variables.setOgrenmb(slider.getValue());
				try {
					panel = new DungeonGraphics(variables);
					panel.setSize(400, 400);
					panel.setLocation(25, 120);
					add(panel);
					panel.setEnabled(true);
					panel.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//lbStatus.setText("You can play now!");
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnNewGame.setEnabled(false);
				
				
			}
		});
		btnNewGame.setBounds(464, 95, 117, 29);
		add(btnNewGame);
		
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		
		
		
	}
}
