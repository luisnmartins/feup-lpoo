package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.cli.DungeonKeep.state;
import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level1Map;
import dkeep.logic.Map;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class DungeonDesign {

	private JFrame frame;
	private JTextField textField;
	private JLabel lbStatus;
	
	public enum state{ RUNNING, WIN, LOSE, END, NEXTLEVEL};
	private Level currentLevel;
	private state gameState;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonDesign window = new DungeonDesign();
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
	public DungeonDesign() {
		initialize();
	}

	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 669, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		lbStatus = new JLabel("You can start a new Game");
		
		JComboBox comboBoxGuard = new JComboBox();
		comboBoxGuard.setModel(new DefaultComboBoxModel(new String[] {"1 - Drunken", "2 -Suspicious", "3 - Rookie"}));
		comboBoxGuard.setToolTipText("");
		
		
		
		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					currentLevel.updateGame('w');
			}
		});
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currentLevel.updateGame('s');
			}
		});
		btnDown.setEnabled(false);
		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currentLevel.updateGame('d');
			}
		});
		btnRight.setEnabled(false);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currentLevel.updateGame('a');
			}
		});
		btnLeft.setEnabled(false);
		
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
								
				Map maptouse = new Level1Map();
				int guardType = comboBoxGuard.getSelectedIndex()+1;
				currentLevel= new DungeonLevel(maptouse, guardType);
				lbStatus.setText("You can play now!");
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				
				
				
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbStatus)
							.addContainerGap())
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNumberOfOgres)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(484, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblGuardPersonality)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBoxGuard, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 197, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
										.addGap(41)))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(btnLeft)
										.addGap(18)
										.addComponent(btnRight)
										.addGap(16))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(56)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnDown)
											.addComponent(btnExit)))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(22)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnNewButton)
											.addComponent(btnUp))))
								.addGap(64)))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(90)
							.addComponent(btnNewButton)
							.addGap(52)
							.addComponent(btnUp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRight)
								.addComponent(btnLeft))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDown)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnExit))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumberOfOgres)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGuardPersonality)
								.addComponent(comboBoxGuard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbStatus)
					.addGap(10))
		);
		panel.setLayout(gl_panel);
	}
	
	/*
	 * Update Game Status
	 */
	public void updateGame(char move)
	{
		
		//gameState = currentLevel.updateGame(move);
		
		switch(gameState)
		{
			case LOSE:
			{
				lbStatus.setText("You Lose!! Try Again");
				break;
			}
			case NEXTLEVEL:
			{
				Level nextlevel = currentLevel.nextLevel();
				if(nextlevel == null)
				{
					gameState = state.WIN;
					lbStatus.setText("Congratzz!! You're a Hero!!");
				}
				else
				{
					
					currentLevel = nextlevel;
					lbStatus.setText("Go, go, go!! You're in the next level");
					gameState = state.RUNNING;			
					
				}
				break;
			}
		}
					
	}
	
	

	
}
