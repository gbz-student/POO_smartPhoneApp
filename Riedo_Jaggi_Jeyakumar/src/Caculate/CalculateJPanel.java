package Caculate;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Launcher.MainJFrame;



public class CalculateJPanel extends JPanel{

	
	
	private JButton buttonBack = new JButton("Retour");
	
	// création des éléments à afficher
	
	String elements [] = {"1","2","3","4","5","6","7","8","9","0","+","-","*","/","="};
	
	// création des butons par élément
	private JButton but[] = new JButton[elements.length];
	
	public CalculateJPanel(){
		add(buttonBack);
		buttonBack.addActionListener(new ListerButtonBack());		
		
		
		// inisialisation des boutons 
		
	   for (int i = 0; i < but.length; i++) {
			
			but[i] = new JButton(elements[i]);
			
	   }
	   
	   
	   
		// positionnement des boutons
	
	   
		for (int i = 0; i < but.length; i++) {
			
			   this.setLayout(new BorderLayout());
			
			add(but[i],BorderLayout.CENTER);
		}
		
		
		
	
		
	
	}
	
	


	class ListerButtonBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
