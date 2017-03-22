package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.gui.DungeonGame.StateViewer;

import javax.swing.JSlider;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class SettingsDialog extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private static GraphicsVariables variables;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			SettingsDialog dialog = new SettingsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public SettingsDialog(GraphicsVariables variables) {
		setTitle("Settings");
		
		this.variables = variables;
		
		
		setBounds(100, 100, 450, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNumberOfOgres = new JLabel("Guard Type");
		lblNumberOfOgres.setBounds(24, 60, 71, 16);
		
		JLabel lblNumberOfOgres_1 = new JLabel("Number of Ogres");
		lblNumberOfOgres_1.setBounds(24, 117, 108, 16);
		
		JComboBox GuardcomboBox = new JComboBox();
		GuardcomboBox.setBounds(171, 60, 161, 27);
		GuardcomboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GuardcomboBox.setModel(new DefaultComboBoxModel(new String[] {"1 - Drunken", "2 -Suspicious", "3 - Rookie"}));
		
		JSlider Ogreslider = new JSlider();
		Ogreslider.setBounds(171, 108, 190, 52);
		Ogreslider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Ogreslider.setMajorTickSpacing(1);
		Ogreslider.setMinorTickSpacing(1);
		Ogreslider.setPaintLabels(true);
		Ogreslider.setPaintTicks(true);
		Ogreslider.setMaximum(5);
		Ogreslider.setMinimum(1);
		Ogreslider.setValue(2);
		contentPanel.setLayout(null);
		contentPanel.add(lblNumberOfOgres);
		contentPanel.add(lblNumberOfOgres_1);
		contentPanel.add(GuardcomboBox);
		contentPanel.add(Ogreslider);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						variables.setOgrenmb(Ogreslider.getValue());
						variables.setGuardTypenmb(GuardcomboBox.getSelectedIndex()+1);
						try {
							
							DungeonGame.changeState(StateViewer.GAME);
						
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
