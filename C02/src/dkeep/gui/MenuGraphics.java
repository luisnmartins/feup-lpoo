package dkeep.gui;

import javax.swing.JPanel;
import javax.swing.JButton;


import dkeep.gui.GraphicsState.StateViewer;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Graphics;


public class MenuGraphics extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraphicsVariables variables;
	private MapSize mapSettings;
	private GraphicsState graphicsst;
	
	/**
	 * Create the panel.
	 */
	public MenuGraphics(GraphicsVariables variables,GraphicsState graphicsst) {
		
		this.variables = variables;
		this.graphicsst = graphicsst;
		mapSettings = new MapSize(variables,this.graphicsst);
		
		
		JButton btnNewgame = new JButton("New Game");
		//btnNewgame.setBackground(Color.GRAY);
		btnNewgame.setBounds(94, 98, 116, 37);
		btnNewgame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnNewgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 
				try {
					graphicsst.changeState(StateViewer.GAME);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		setLayout(null);
		add(btnNewgame);
		setExitButton();
		setEditorButton();
		
		
		

	}
	
	public void setExitButton()
	{
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(94, 220, 116, 37);
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		add(btnExit);
	}
	
	public void setEditorButton()
	{
		JButton btnEditor = new JButton("Editor");
		btnEditor.setBounds(94, 156, 116, 37);
		btnEditor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				
				mapSettings.setVisible(true);
				
			}
		});
		add(btnEditor);
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(variables.getMenuScreen(), 0, 0, this);
	}
}
