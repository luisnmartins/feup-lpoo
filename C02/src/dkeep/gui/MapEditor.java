package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JToggleButton;



import javax.swing.ButtonGroup;



import dkeep.gui.GraphicsState.StateViewer;
import dkeep.logic.Level;
import dkeep.logic.Level2Map;
import dkeep.logic.Map;
import dkeep.logic.OgreLevel;

import javax.swing.ImageIcon;

import javax.swing.JButton;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;


import java.io.IOException;
import java.awt.event.ActionEvent;

public class MapEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[][] new_map;
	//private boolean[][] visited;
	//private boolean foundExit;
	private GraphicsVariables variables;
	private DungeonGraphics panel_1; 
	private Level currentLevel;
	private Map newMap;
	char activatedElement;
	
	private GraphicsState graphicsst;
	

	/**
	 * Create the panel.
	 */
	public MapEditor(GraphicsVariables variables,GraphicsState graphicsst) {
		this.graphicsst = graphicsst;
		this.variables = variables;
		
		new_map = new char[variables.getVerMapSize()][variables.getHorMapSize()];
		//visited = new boolean[variables.getHorMapSize()][variables.getVerMapSize()];
		initializeMap();
		newMap = new Map(new_map);
		currentLevel = new OgreLevel(newMap);
		variables.setLevel(currentLevel);
		initialize();
		
	}
	
	public void initialize() {	
		
		try {
			
			panel_1 = new DungeonGraphics(this.variables, this.graphicsst);
			panel_1.setSize(new Dimension(400, 400));
			panel_1.setMaximumSize(new Dimension(400, 400));
			panel_1.setLocation(155,163);
			add(panel_1);
			this.variables.getLevel().printMap();
			
			
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
		setLayout(null);
		setButtons();
		setPlayButton();
		setCancelButton();
		setButtons();	
		
	}
	
	public void setButtons(){
		ButtonGroup choices = new ButtonGroup();
		setMarioChoiceButton(choices);
		setDoorChoiceButton(choices);
		setKeyChoiceButton(choices);
		setWallChoiceButton(choices);
		setFloorChoiceButton(choices);
		setBowserChoiceButton(choices);
		
	}
	
		
	public void setCancelButton()
	{
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(111, 631, 100, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					try{
					
					graphicsst.changeState(StateViewer.MENU);
				
					}catch (IOException e1){
					
					e1.printStackTrace();}
			}
		});
		add(btnCancel);
	}
	
	
		public void setPlayButton()
		{
			JButton btnPlay = new JButton("Play");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(!basicMapVerification())
					{
						return;
					}
					
					Map setmap = new Level2Map();
					setmap.setSaticMap(variables.getMap());
					try {
						
						graphicsst.changeState(StateViewer.GAME);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			});
			btnPlay.setBounds(6, 631, 78, 23);
			add(btnPlay);
		}
		
		
		public void setMarioChoiceButton(ButtonGroup choices)
		{
			JToggleButton tglbtnMario = new JToggleButton();
			tglbtnMario.setSize(new Dimension(70, 70));
			tglbtnMario.setBounds(54, 18, 70, 70);
			add(tglbtnMario);
			tglbtnMario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					variables.setSelectedElement('H');
				}
			});
			
			tglbtnMario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
					tglbtnMario.setIcon(new ImageIcon("images/mario_d.png"));
					choices.add(tglbtnMario);
		}
		
		
		public void setDoorChoiceButton(ButtonGroup choices)
		{
			JToggleButton tglbtnDoor = new JToggleButton();
			tglbtnDoor.setSize(new Dimension(70, 70));
			tglbtnDoor.setBounds(154, 18, 70, 70);
			add(tglbtnDoor);
			tglbtnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					variables.setSelectedElement('I');
			}
			});
			tglbtnDoor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							
			tglbtnDoor.setIcon(new ImageIcon("images/closedDoor.png"));
			choices.add(tglbtnDoor);
		}
		
		public void setKeyChoiceButton(ButtonGroup choices)
		{
			
			JToggleButton tglbtnKey = new JToggleButton();
			tglbtnKey.setSize(new Dimension(70, 70));
			tglbtnKey.setBounds(251, 18, 70, 70);
			add(tglbtnKey);
			tglbtnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
										
				variables.setSelectedElement('k');
			}
			});
			tglbtnKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			tglbtnKey.setIcon(new ImageIcon("images/key.png"));
			choices.add(tglbtnKey);
		}
		
		public void setWallChoiceButton(ButtonGroup choices)
		{
			JToggleButton tglbtnWall = new JToggleButton();
			tglbtnWall.setSize(new Dimension(70, 70));
			tglbtnWall.setBounds(356, 18, 70, 70);
			add(tglbtnWall);
			tglbtnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
											
				variables.setSelectedElement('X');
			}
			});
			tglbtnWall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			tglbtnWall.setIcon(new ImageIcon("images/wall.png"));
			choices.add(tglbtnWall);
		}
		
		public void setFloorChoiceButton(ButtonGroup choices)
		{
			JToggleButton tglbtnFloor = new JToggleButton();
			tglbtnFloor.setSize(new Dimension(70, 70));
			tglbtnFloor.setBounds(462, 18, 70, 70);
			add(tglbtnFloor);
			tglbtnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
											
				variables.setSelectedElement(' ');
			}
			});
			tglbtnFloor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			tglbtnFloor.setIcon(new ImageIcon("images/floor.png"));
			choices.add(tglbtnFloor);
		}
									
		public void setBowserChoiceButton(ButtonGroup choices)
		{
			JToggleButton tglbtnBowser = new JToggleButton();
			tglbtnBowser.setSize(new Dimension(70, 70));
			tglbtnBowser.setBounds(562, 18, 78, 72);
			add(tglbtnBowser);
			tglbtnBowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
													
				variables.setSelectedElement('O');
													
			}
			});
			tglbtnBowser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));								
			tglbtnBowser.setIcon(new ImageIcon("images/bowser_s.png"));
			choices.add(tglbtnBowser);
			
		}

	


	
	
	public void initializeMap()
	{
		
		
		for(int i = 0; i < new_map.length ; i++)
		{
			for(int a = 0; a < new_map[i].length; a++)
			{
				if(i == 0 || a == 0 || i  == new_map.length-1 || a == new_map[i].length-1)
				{
					new_map[i][a] = 'X';
				}else new_map[i][a] = ' ';	
			}
		}
	}
	
	
	
	public boolean basicMapVerification()
	{
		boolean hero_exist = false;
		boolean key_exist = false;
		boolean door_exist = false;
		boolean ogre_exist = false;
		char[][] aux = variables.getMap();
		for(int i = 0; i < aux.length ; i ++)
			for(int a = 0; a < aux[i].length; a++)
			{
				if(i == 0 || a == 0 || i  == aux.length-1 || a == aux[i].length-1)
				{
					if(aux[i][a] != 'X' && aux[i][a] != 'I')
						return false;
					if(aux[i][a] == 'I')
						door_exist = true;
				}else if(aux[i][a] == 'H')
				{
					if(!hero_exist)
						hero_exist = true;
					else return false;
						
				}else if(aux[i][a] == 'k'){
					
					if(!key_exist)
						key_exist = true;
					else  return false;	
				}
				else if(aux[i][a] == 'O'){
					if(!ogre_exist)
						ogre_exist = true;
					else return false;
				}					
			}
		
		if(door_exist && key_exist && hero_exist && ogre_exist)
			return true;
		else return false;
		
	}
	
}
	
