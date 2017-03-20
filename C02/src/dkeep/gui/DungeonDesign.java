package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class DungeonDesign {

	private JFrame frmDungeonkeep;
	private JTextField ogresField;
	private JLabel lbStatus;
	private JButton gameStart;
	private JButton btnLeft ;
	private JButton btnRight ;
	private JButton btnUp ;
	private JButton btnDown ;
	
	
	private Level currentLevel;
	private state gameState;
	private Map gamemap;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		frmDungeonkeep = new JFrame();
		frmDungeonkeep.setTitle("DungeonKeep");
		frmDungeonkeep.setBounds(100, 100, 669, 499);
		frmDungeonkeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmDungeonkeep.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		
		ogresField = new JTextField();
		ogresField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		
		JTextArea GameScreen = new JTextArea();
		GameScreen.setFont(new Font("Prestige Elite Std", Font.BOLD, 30));
		GameScreen.setEditable(false);
		
		lbStatus = new JLabel("You can start a new Game");
		
		JComboBox comboBoxGuard = new JComboBox();
		comboBoxGuard.setModel(new DefaultComboBoxModel(new String[] {"1 - Drunken", "2 -Suspicious", "3 - Rookie"}));
		comboBoxGuard.setToolTipText(""); 
		
		
		
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					updateGame('w');
				  
					GameScreen.setText(currentLevel.getMap());
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateGame('s');
				
				GameScreen.setText(currentLevel.getMap());
			}
		});
		btnDown.setEnabled(false);
		
		 btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateGame('d');
				
				GameScreen.setText(currentLevel.getMap());
			}
		});
		btnRight.setEnabled(false);
		
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateGame('a');
				
				
				GameScreen.setText(currentLevel.getMap());
			}
		});
		btnLeft.setEnabled(false);
		
		
		 gameStart = new JButton("Start Game");
		gameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(ogresField.getText().equals("") || Integer.parseInt(ogresField.getText()) < 1 ||  Integer.parseInt(ogresField.getText()) > 5 )
				{
					return;
				}
								
				gamemap = new Level1Map();
				int guardType = comboBoxGuard.getSelectedIndex()+1; //1 - drunken , 2- suspicious, 3 - Rookie				
				currentLevel = new DungeonLevel(gamemap, guardType);
				lbStatus.setText("You can play now!");
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				GameScreen.setText(currentLevel.getMap());
				gameStart.setEnabled(false);
				
				
				
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
								.addComponent(ogresField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(496, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblGuardPersonality)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBoxGuard, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(GameScreen, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
										.addGap(41)))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(btnLeft)
										.addGap(18)
										.addComponent(btnRight))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(22)
										.addComponent(gameStart))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(43)
										.addComponent(btnUp))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(38)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(btnExit)
											.addComponent(btnDown))))
								.addGap(80)))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(90)
							.addComponent(gameStart)
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
								.addComponent(ogresField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGuardPersonality)
								.addComponent(comboBoxGuard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(GameScreen, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)))
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
		
		gameState = currentLevel.updateGame(move);
		
		switch(gameState)
		{
			case LOSE:
			{
				lbStatus.setText("You Lose!! Try Again");
				this.gameStart.setEnabled(true);
				btnLeft.setEnabled(false);
				btnRight.setEnabled(false);
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				break;
			}
			case NEXTLEVEL:
			{
				Level nextlevel;
				nextlevel = currentLevel.nextLevel(Integer.parseInt(ogresField.getText()));
				
				if(nextlevel == null)
				{
					gameState = state.WIN;
					lbStatus.setText("Congratzz!! You're a Hero!!");
					this.gameStart.setEnabled(true);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
				}
				else
				{
					
					currentLevel = nextlevel;
					lbStatus.setText("Go, go, go!! You're in the next level");
					
					gameState = state.RUNNING;			
					
				}
				break;
			}
			default:
				break;
		}
					
	}
	
	

	
}
