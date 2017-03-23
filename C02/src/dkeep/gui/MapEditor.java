package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JToggleButton;



import javax.swing.ButtonGroup;



import dkeep.gui.GraphicsState.StateViewer;
import dkeep.logic.Level;
import dkeep.logic.Level2Map;
import dkeep.logic.Map;


import javax.swing.ImageIcon;

import javax.swing.JButton;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;
import java.awt.event.ActionEvent;

public class MapEditor extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[][] new_map;
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
		
		
		
		new_map = new char[variables.getHorMapSize()][variables.getVerMapSize()];
		initializeMap();
		newMap = new Map(new_map);
		currentLevel = new Level(newMap);
		variables.setLevel(currentLevel);
		
		
		
		
		

		ButtonGroup choices = new ButtonGroup();
		
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map setmap = new Level2Map();
				setmap.setSaticMap(variables.getMap());
				try {
					graphicsst.changeState(StateViewer.GAME);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPlay.setBounds(10, 545, 78, 23);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(115, 545, 100, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					try {
					
					graphicsst.changeState(StateViewer.MENU);
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		try {
			
			panel_1 = new DungeonGraphics(this.variables, this.graphicsst);
			panel_1.setSize(new Dimension(400, 400));
			panel_1.setMaximumSize(new Dimension(400, 400));
			panel_1.setLocation(191,127);
			add(panel_1);
			
			System.out.println("variables: "+this.variables.getHorMapSize());
			System.out.println("variables: "+this.variables.getVerMapSize());
			this.variables.getLevel().printMap();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLayout(null);
		add(btnPlay);
		add(btnCancel);
		
				
				
				JToggleButton tglbtnMario = new JToggleButton();
				tglbtnMario.setSize(new Dimension(70, 70));
				tglbtnMario.setBounds(115, 11, 70, 70);
				add(tglbtnMario);
				tglbtnMario.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//activatedElement = 'H';
						variables.setSelectedElement('H');
					}
				});
				
				tglbtnMario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
						tglbtnMario.setIcon(new ImageIcon("images/mario_d.png"));
						choices.add(tglbtnMario);
						
								
								JToggleButton tglbtnDoor = new JToggleButton();
								tglbtnDoor.setSize(new Dimension(70, 70));
								tglbtnDoor.setBounds(215, 11, 70, 70);
								add(tglbtnDoor);
								tglbtnDoor.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										//activatedElement = 'I';
										variables.setSelectedElement('I');
									}
								});
								tglbtnDoor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								
										tglbtnDoor.setIcon(new ImageIcon("images/plant.png"));
										choices.add(tglbtnDoor);
										
										
										
										JToggleButton tglbtnKey = new JToggleButton();
										tglbtnKey.setSize(new Dimension(70, 70));
										tglbtnKey.setBounds(312, 11, 70, 70);
										add(tglbtnKey);
										tglbtnKey.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												//activatedElement = 'k';
												variables.setSelectedElement('k');
											}
										});
										tglbtnKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										tglbtnKey.setIcon(new ImageIcon("images/key.png"));
										choices.add(tglbtnKey);
										
										
										JToggleButton tglbtnWall = new JToggleButton();
										tglbtnWall.setSize(new Dimension(70, 70));
										tglbtnWall.setBounds(417, 11, 70, 70);
										add(tglbtnWall);
										tglbtnWall.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												//activatedElement = 'X';
												variables.setSelectedElement('X');
											}
										});
										tglbtnWall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										tglbtnWall.setIcon(new ImageIcon("images/wall.png"));
										choices.add(tglbtnWall);
										

										JToggleButton tglbtnFloor = new JToggleButton();
										tglbtnFloor.setSize(new Dimension(70, 70));
										tglbtnFloor.setBounds(515, 11, 70, 70);
										add(tglbtnFloor);
										tglbtnFloor.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												//activatedElement = ' ';
												variables.setSelectedElement(' ');
											}
										});
										tglbtnFloor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										tglbtnFloor.setIcon(new ImageIcon("images/floor.png"));
										choices.add(tglbtnFloor);
										
												JToggleButton tglbtnBowser = new JToggleButton();
												tglbtnBowser.setSize(new Dimension(70, 70));
												tglbtnBowser.setBounds(623, 11, 78, 72);
												add(tglbtnBowser);
												tglbtnBowser.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														//activatedElement = 'O';
														variables.setSelectedElement('O');
														
													}
												});
												tglbtnBowser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
												
														tglbtnBowser.setIcon(new ImageIcon("images/bowser_s.png"));
														
																
																choices.add(tglbtnBowser);
		
		

	}
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		panel_1.mouseClicked(e);
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < map.length; i++)
		{
			for(int a = 0; a < map[i].length; a++)
			{
				if(map[i][a] == ' ')
				{
					g.drawImage(floor, a*70, i*70, this);
				}
				if(map[i][a] == 'O')
				{
					g.drawImage(bowser, a*70, i*70, this);
				}if(map[i][a] == 'H')
				{
					g.drawImage(mario, a*70, i*70, this);
				}if(map[i][a] == 'X')
				{
					g.drawImage(wall, a*70, i*70, this);
				}
				if(map[i][a] == 'I')
				{
					g.drawImage(plant, a*70, i*70, this);
				}if(map[i][a] == 'k')
				{
					g.drawImage(key, a*70, i*70, this);
				}
			}
		}
		
	}*/
	
	public void initializeMap()
	{
		
		
		for(int i = 0; i < new_map.length ; i++)
		{
			for(int a = 0; a < new_map[i].length; a++)
			{
				if(i == 0 || a == 0)
				{
					new_map[i][a] = 'X';
				}else if(i  == new_map.length-1 || a == new_map[i].length-1)
				{
					new_map[i][a] = 'X';
				}
				else new_map[i][a] = ' ';
				if(i == new_map.length-1 || a == new_map[i].length-1)
				{
					System.out.println(new_map[i][a]);
				}else System.out.print(new_map[i][a]);
				
			}
		}
	}
}
