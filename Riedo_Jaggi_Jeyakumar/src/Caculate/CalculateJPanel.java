package Caculate;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


import Launcher.MainJFrame;

/**
 * classe permettant d'appeler Une classe ActionsButton et de l'afficher
 * @author ashan
 *
 */
public class CalculateJPanel extends JPanel{
	
	
	private ActionsButton butpanel = new ActionsButton();
	
	/**
	 * constructeur permettant d'afficher la calculatrice
	 */
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
