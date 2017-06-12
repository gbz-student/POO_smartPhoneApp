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
public class ContactView extends JPanel{

	private TitleJPanel title = new TitleJPanel("Mes contacts");	
	private static JPanel cards;
	private static CardLayout cardLayout = new CardLayout();
	private ContactListView contactListView = new ContactListView();
	private ContactInfoView contactInfoView = new ContactInfoView();
	private ContactFormView contactFormView = new ContactFormView();
	private static ContactController contactController = new ContactController();
	
	/**
	 * Construit le JPanel de basse qui contient le cardLayout
	 */
	public ContactView(){
		setPreferredSize(new Dimension(480, 750));
		setLayout(new BorderLayout());
		 
		cards = new JPanel (cardLayout);
		cards.add(contactListView, "contactListView", 0);
		cards.add(contactFormView, "contactFormView", 1);
		cards.add(contactInfoView, "contactInfoView", 2);
		
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
}
