package Caculate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Launcher.MainJFrame;

public class CalculateJPanel extends JPanel{
	private JButton buttonBack = new JButton("Retour");
		
	public CalculateJPanel(){
		add(buttonBack);
		
		buttonBack.addActionListener(new ListerButtonBack());
	}
	
	class ListerButtonBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");	
		}
	}
	
}
