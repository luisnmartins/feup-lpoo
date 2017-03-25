package dkeep.gui;

import javax.swing.JLabel;

import java.awt.Cursor;

import javax.swing.JPanel;

import dkeep.gui.GraphicsState.StateViewer;
import dkeep.logic.Level;


import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;


public class DungeonDesign extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JSlider slider;
	private JComboBox<String> comboBox;
	private DungeonGraphics panel; 
	private JButton btnNewGame;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	private JButton btnReturn;

	
	
	private GraphicsVariables variables;
	private GraphicsState graphicsst;
	private JButton btnSaveGame;
	private JButton btnLoadGame;
	

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public DungeonDesign(GraphicsVariables variables, GraphicsState graphicsst) throws IOException {
		
		this.variables = variables;
		this.graphicsst = graphicsst;
		setLayout(null);
		
		
		this.settingInitialize();
		this.commandButtonsInitialize();
		this.mainButtonsInitialize();
		initialize();
		
		
		
		
	}

	private void settingInitialize()
	{
		lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(32, 30, 121, 16);
		add(lblNumberOfOgres);
		
		lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(32, 98, 115, 16);
		add(lblGuardPersonality);
		
		slider = new JSlider();
		slider.setBounds(159, 30, 206, 37);
		add(slider);
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMaximum(5);
		slider.setMinimum(1);
		slider.setValue(1);
		
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1 - Drunken", "2 -Suspicious", "3 - Rookie"}));
		comboBox.setBounds(164, 94, 173, 27);
		add(comboBox);
		}
	
	private void commandButtonsInitialize()
	{
		
		setUpButton();
		setLeftButton();
		setDownButton();
		setRightButton();	
		
		
	}
	
	public void setUpButton()
	{
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.updateMove('w');
				panel.requestFocusInWindow();
			}
		});
		btnUp.setBounds(499, 246, 117, 29);
		add(btnUp);
		btnUp.setEnabled(false);
	}
	
	public void setLeftButton()
	{
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.updateMove('a');
				panel.requestFocusInWindow();
			}
		});
		btnLeft.setBounds(429, 287, 117, 29);
		add(btnLeft);
		btnLeft.setEnabled(false);
	}
	
	public void setRightButton()
	{
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.updateMove('d');
				panel.requestFocusInWindow();
			}
		});
		btnRight.setBounds(558, 287, 117, 29);
		add(btnRight);
		btnRight.setEnabled(false);
	}
	
	public void setDownButton()
	{
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateMove('s');
				panel.requestFocusInWindow();
			}
		});
		btnDown.setBounds(499, 328, 117, 29);
		add(btnDown);
		btnDown.setEnabled(false);
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		
		setSaveButton();
		setLoadButton();
		
		
	}
	
	private void setSaveButton()
	{
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			         FileOutputStream fileOut =
			         new FileOutputStream("images/SavedGame");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(variables.getLevel());
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in /images/SavedGame");
			      }catch(IOException i) {
			         i.printStackTrace();
			      }catch(NullPointerException s)
					{
			    	  System.out.println("Game not found");
					}
				 panel.requestFocusInWindow();
				
			}
		});
		btnSaveGame.setBounds(503, 497, 117, 44);
		add(btnSaveGame);
	}
	
	private void setLoadButton()
	{
		btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					panel = new DungeonGraphics(variables, graphicsst);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				panel.setBounds(25,160, 400, 400);

				add(panel);
				panel.setEnabled(true);
				
				try {
			         FileInputStream fileIn = new FileInputStream("images/SavedGame");
			         ObjectInputStream in = new ObjectInputStream(fileIn);
			         variables.setLevel( (Level) in.readObject());
			         System.out.println("load game");
			         in.close();
			         fileIn.close();
			      }catch(IOException i) {
			         i.printStackTrace();
			         return;
			      }catch(ClassNotFoundException c) {
			         System.out.println("Level class not Found");
			         c.printStackTrace();
			         return;
			      }
				 panel.requestFocusInWindow();
				 panel.repaint();
			}
		});
		btnLoadGame.setBounds(499, 410, 121, 44);
		add(btnLoadGame);
		
		
	}
	
	
	
	
	private void mainButtonsInitialize()
	{
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				variables.setGuardTypenmb(comboBox.getSelectedIndex()+1);
				variables.setOgrenmb(slider.getValue());
				try {
					panel = new DungeonGraphics(variables, graphicsst);
					add(panel);
					comboBox.setEnabled(false);
					slider.setEnabled(false);
					panel.setBounds(25,160, 400, 400);
					panel.setEnabled(true);
					panel.requestFocusInWindow();
					panel.repaint();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnNewGame.setEnabled(false);
				}
		});
		btnNewGame.setBounds(495, 145, 121, 44);
		add(btnNewGame);
		
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					graphicsst.changeState(StateViewer.MENU);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});	
		btnReturn.setBounds(503, 580, 117, 44);
		add(btnReturn);
	}
}
