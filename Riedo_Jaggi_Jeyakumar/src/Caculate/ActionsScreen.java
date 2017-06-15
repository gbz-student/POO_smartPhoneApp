package Caculate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * classe permettant de créer un écran pour afficher les résultats des opérations
 * @author ashan
 *
 */

public class ActionsScreen extends JPanel {

	// creation d'un champ de texte
	protected JLabel text = new JLabel();
	
	// creation d'une dimension pour le champ de texte
	private Dimension dim = new Dimension(300,100);
	
	// definir la taille et la police du champ de texte
	private Font tp = new Font("Arial",Font.PLAIN,80);
	

	/**
	 * constructeur permettant de personaliser le JLabel
	 */
	public ActionsScreen(){
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		
		text = new JLabel("0");
		this.setPreferredSize(dim);
		text.setFont(tp);
		text.setForeground(Color.white);
		
		
		// alignement des chiffres à droit dans le JLabel
		text.setHorizontalAlignment(JLabel.RIGHT);
		
	    // ajout du JLabel dans le Jpanel
		this.add(text);
				
		
	}
	
	
	

	
	
	
}
