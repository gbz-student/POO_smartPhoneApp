package Contact;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import MainPackage.TitleJPanel;

/**
 * Panel de base pour l'application contact. Comprend un cardlayout avec les différents panel.
 * 
 * @author ken
 *
 */
public class ContactJPanel extends JPanel{

	private TitleJPanel title = new TitleJPanel("Mes contacts");	
	private static JPanel cards;
	private static CardLayout cardLayout = new CardLayout();
	private ContactListJPanel contactListJPanel = new ContactListJPanel();
	private ContactInfoJPanel contactInfoJPanel = new ContactInfoJPanel();
	private JPanel contactForm = new ContactForm();
	private static ContactController contactController = new ContactController();
	
	/**
	 * Construit le JPanel de basse qui contient le cardLayout
	 */
	public ContactJPanel(){
		setPreferredSize(new Dimension(480, 750));
		setLayout(new BorderLayout());
		 
		cards = new JPanel (cardLayout);
		cards.add(contactListJPanel, "contactListJPanel", 0);
		cards.add(contactForm, "contactForm", 1);
		cards.add(contactInfoJPanel, "contactInfoJPanel", 2);
		
		add(title, BorderLayout.NORTH);
		
		cards.setBackground(new Color(0,0,0,0));
		add(cards, BorderLayout.CENTER);
	}
	
	/**
	 * @return ContactController contactController
	 */
	public static ContactController getContactController(){
		return contactController;
	}
	
	/**
	 * Retourne le composant du cardLayout correspondant à l'index donnée en paramètre
	 * 
	 * @param int index
	 * @return JPanel 
	 */
	public static JPanel getCardsComponent(int index){
		return (JPanel) cards.getComponent(index);
	}
	
	public static void changePanel(String name){
		cardLayout.show(cards, name);
    }
	
	public static void goFirstPanel(){
		cardLayout.first(cards);
    }
}
