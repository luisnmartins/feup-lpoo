package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.gui.GraphicsState.StateViewer;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class MapSize extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  GraphicsVariables map_variables;
	private JLabel lblHorizontalSize;
	private JTextField horSize;
	private JTextField verSize;
	private GraphicsState graphicsst;


	/**
	 * Create the dialog.
	 */
	public MapSize(GraphicsVariables variables,GraphicsState graphicsst) {
		
		this.map_variables = variables;
		this.graphicsst = graphicsst;
		initialize();
		
	}
	
	public void initialize()
	{
		setTitle("Map Editor Settings"); 
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblHorizontalSize = new JLabel("Horizontal size");
			lblHorizontalSize.setBounds(39, 56, 122, 50);
			lblHorizontalSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		
		horSize = new JTextField();
		horSize.setBounds(167, 69, 130, 26);
		horSize.setColumns(10);
		
		JLabel lblVerticalSize = new JLabel("Vertical size");
		lblVerticalSize.setBounds(39, 137, 100, 20);
		lblVerticalSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		verSize = new JTextField();
		verSize.setBounds(167, 135, 130, 26);
		verSize.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(lblHorizontalSize);
		contentPanel.add(lblVerticalSize);
		contentPanel.add(verSize);
		contentPanel.add(horSize);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			setOKButton(buttonPane);
			setCancelButton(buttonPane);
		}
	}
	
	public void setOKButton(JPanel buttonPane)
	{
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!horSize.getText().equals("") && !verSize.getText().equals(""))
				if(Integer.parseInt(horSize.getText()) >= 5 && Integer.parseInt(verSize.getText()) >= 5)
				{
			
				try {
					
					map_variables.setMapSizes(Integer.parseInt(horSize.getText()),Integer.parseInt(verSize.getText()));
					
					
					graphicsst.changeState(StateViewer.CUSTOM);
				
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				dispose();
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	public void setCancelButton(JPanel buttonPane)
	{
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
					
					graphicsst.changeState(StateViewer.MENU);
				
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
					dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
