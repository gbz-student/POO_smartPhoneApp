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
	
	private JPanel b = new JPanel();
	private JPanel champ = new JPanel();

	// création des éléments à afficher
	
	private String elements [] = {"CE","C","DEL","÷","7","8","9","×","4","5","6","-","1","2","3","+","+/-","0",".","="};
	
	// création des boutons par élément
	private JButton but[] = new JButton[elements.length];
	
	// création d'un champ texte
	private JTextField text = new JTextField();
	
	// création d'une dimension pour le champ de texte
	private Dimension dim = new Dimension(300,100);
	
	// définir la taille et la police du champ de texte
	private Font tp = new Font("Arial",Font.BOLD,60);
	
	// agrandissement du caractère du bouton
	private Font f = new Font("Serif", Font.PLAIN, 40);
	
	private GridLayout cr = new GridLayout();

	public CalculateJPanel(){
		
		setLayout(new BorderLayout());
		
	
		b.setLayout(new GridLayout());
		champ.setLayout(new BorderLayout());
		champ.setPreferredSize(dim);
		text.setFont(tp);
		
		
		// inisialisation des boutons
		
	   for (int i = 0; i < but.length; i++) {
			
			but[i] = new JButton(elements[i]);
			
			but[i].setSize(50, 50);
			
			but[i].setFont(f);
		
	   }
	   
	  // coloriage des boutons 
	   
	   for (int i = 0; i < elements.length; i++) {
		   
		   but[i].setBackground(new Color(128,128,125));
		   
		   if(elements[i]=="+"||elements[i]=="-"||elements[i]=="÷"||elements[i]=="×"||elements[i]=="="){
			
			 but[i].setBackground(new Color(255,170,7));
		   }
	}
	     
		// ajout des boutons dans le Panel b
	
		for (int i = 0; i < but.length; i++) {
		
			    b.add(but[i]);	   
		}
		
		cr.setColumns(4);
		cr.setRows(5);
		b.setLayout(cr);
		
		// gfixer des espaces entre les boutons 
		cr.setHgap(4);
		cr.setVgap(6);
		
		// ajout du champ de texte dans le panel champ
		champ.add(text);
		
		this.add(b, BorderLayout.CENTER);
		this.add(champ, BorderLayout.NORTH);
			
	}
	
	


	class ListerButtonBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
