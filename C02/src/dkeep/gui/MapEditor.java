package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.gui.GraphicsState.StateViewer;
import dkeep.logic.Level;
import dkeep.logic.Map;
import dkeep.logic.OgreLevel;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.SystemColor;

public class MapEditor extends JPanel implements MouseListener {

	private char[][] new_map;
	private GraphicsVariables variables;
	private Level currentLevel;
	private Map newMap;
	private DungeonGraphics panel_1;
	char activatedElement;
	
	private GraphicsState graphicsst;
	

	/**
	 * Create the panel.
	 */
	public MapEditor(GraphicsVariables variables,GraphicsState graphicsst) {
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		this.graphicsst = graphicsst;
		
		
		this.variables = variables;
		
		
		
		new_map = new char[variables.getHorMapSize()][variables.getVerMapSize()];
		initializeMap();
		newMap = new Map(new_map);
		currentLevel = new Level(newMap);
		variables.setLevel(currentLevel);
		
		
		
		
		

		ButtonGroup choices = new ButtonGroup();
		
		
		JToggleButton tglbtnMario = new JToggleButton();
		tglbtnMario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activatedElement = 'H';
			}
		});
		//tglbtnMario.setBorder(BorderFactory.createEmptyBorder());
		panel.add(tglbtnMario);
		
		tglbtnMario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tglbtnMario.setIcon(new ImageIcon("images/mario_d.png"));

		
		JToggleButton tglbtnDoor = new JToggleButton();
		tglbtnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activatedElement = 'I';
			}
		});
		panel.add(tglbtnDoor);
		tglbtnDoor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tglbtnDoor.setIcon(new ImageIcon("images/plant.png"));
		
		
		
		JToggleButton tglbtnKey = new JToggleButton();
		tglbtnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activatedElement = 'k';
			}
		});
		panel.add(tglbtnKey);
		tglbtnKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnKey.setIcon(new ImageIcon("images/key.png"));
		
		
		JToggleButton tglbtnWall = new JToggleButton();
		tglbtnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activatedElement = 'X';
			}
		});
		panel.add(tglbtnWall);
		tglbtnWall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnWall.setIcon(new ImageIcon("images/wall.png"));
		

		JToggleButton tglbtnFloor = new JToggleButton();
		tglbtnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activatedElement = ' ';
			}
		});
		panel.add(tglbtnFloor);
		tglbtnFloor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnFloor.setIcon(new ImageIcon("images/floor.png"));

		JToggleButton tglbtnBowser = new JToggleButton();
		tglbtnBowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//activatedElement = 'O';
				variables.setSelectedElement('0');
			}
		});
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
					
					graphicsst.changeState(StateViewer.MENU);
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		try {
			System.out.println("variables: "+this.variables.getHorMapSize());
			System.out.println("variables: "+this.variables.getVerMapSize());
			this.variables.getLevel().printMap();
			panel_1 = new DungeonGraphics(this.variables,graphicsst);
			panel_1.setSize(new Dimension(300, 300));
			panel_1.setPreferredSize(new Dimension(300, 300));
			panel_1.setMinimumSize(new Dimension(100, 100));
			panel_1.setMaximumSize(new Dimension(400, 400));
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_1.setBackground(Color.WHITE);
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(137)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addGap(146))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnPlay)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap(193, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlay)
						.addComponent(btnCancel)))
		);
		setLayout(groupLayout);

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
