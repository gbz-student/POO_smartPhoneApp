package Caculate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Launcher.MainJFrame;


public class CalculateJPanel extends JPanel{
	
	
	private ActionsButton butpanel = new ActionsButton();
	
	
	public CalculateJPanel(){
		
		this.setVisible(true);
		
		setLayout(new BorderLayout());
		add(butpanel);
		
	}
	


	class ListerButtonBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
