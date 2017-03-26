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
import javax.swing.JLabel;

import javax.swing.JSlider;

public class MapSize extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private  GraphicsVariables map_variables;
	private GraphicsState graphicsst;
	private JSlider slider;


	/**
	 * Create the dialog.
	 */
	public MapSize(GraphicsVariables variables,GraphicsState graphicsst) {
		
		this.map_variables = variables;
		this.graphicsst = graphicsst;
		initialize();
		
	}
	
	public void initialize(){
		
		setTitle("Map Editor Settings"); 
		setBounds(100, 100, 451, 178);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setMinimum(5);
		slider.setMaximum(20);
		slider.setValue(10);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBounds(102, 32, 328, 52);
		contentPanel.add(slider);
		
		JLabel lblSize = new JLabel("Matrix Size:");
		lblSize.setBounds(22, 45, 95, 16);
		contentPanel.add(lblSize);{
			
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
				try {
					
					map_variables.setMapSizes(slider.getValue(), slider.getValue());
						
					graphicsst.changeState(StateViewer.CUSTOM);
				
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				dispose();
				}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
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
