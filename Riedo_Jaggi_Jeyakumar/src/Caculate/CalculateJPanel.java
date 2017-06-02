package Caculate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Launcher.MainJFrame;



public class CalculateJPanel extends JPanel{

	
	// création des éléments à afficher
	
	private String elements [] = {"1","2","3","4","5","6","7","8","9","0","+","-","*","/","="};
	
	// création des butons par élément
	private JButton but[] = new JButton[elements.length];
	
	// création d'un champ texte
	
	private JTextField text = new JTextField();
	
	
	
    // positionnement des boutons en colonnes

	private GridLayout cr = new GridLayout();
	
	
	
	public CalculateJPanel(){
		
	 
		
		// inisialisation des boutons 
		
	   for (int i = 0; i < but.length; i++) {
			
			but[i] = new JButton(elements[i]);
			
			but[i].setBackground(Color.gray);
			
			
			
	   }
	   
	   
	   
		// positionnement des boutons
	
	   
		for (int i = 0; i < but.length; i++) {
			
			// this.setLayout(new BorderLayout());
			
			    add(but[i]);
			   
		}
		
		cr.setColumns(2);
		cr.setRows(3);
		this.setLayout(cr);
		
		// fixer des espaces entre les boutons
		cr.setHgap(4);
		cr.setVgap(6);
		
		
		add(text, BorderLayout.NORTH);
		
	
		
		
	}
	
	


	class ListerButtonBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
