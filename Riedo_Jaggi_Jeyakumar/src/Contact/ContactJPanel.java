package Contact;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import MainPackage.TitleJPanel;

public class ContactJPanel extends JPanel{

	private TitleJPanel title = new TitleJPanel("Mes contacts");	
	public static JPanel cards;
	private JPanel contactListJPanel = new ContactListJPanel();
	private JPanel contactForm = new ContactForm();
	public static CardLayout cardLayout = new CardLayout();
	
	public ContactJPanel(){
		setPreferredSize(new Dimension(480, 750));
		setLayout(new BorderLayout());
		 
		cards = new JPanel (cardLayout);
		cards.add(contactListJPanel);
		cards.add(contactForm, "contactForm");
		
		add(title, BorderLayout.NORTH);
		
		cards.setBackground(new Color(0,0,0,0));
		add(cards, BorderLayout.CENTER);
	}
	
	public static void removeLastPanel(){
		if(cards.getComponentCount() > 2){
			cards.remove(cards.getComponentCount() - 1);
		}
	}
	
	public static void backPanel(String source){
		if(source == "contactForm"){
			System.out.println(cards.getComponentCount());
			if(cards.getComponentCount() == 2){
				goFirstPanel();
			}else{
				changePanel("contactInfo");
			}
		}else if (source == "contactInfo") {
			ContactJPanel.removeLastPanel();
			ContactJPanel.goFirstPanel();
		}
	}
	
	public static void addPanel(JPanel panel, String constraint){
		cards.add(panel, constraint, 2);
    }
	
	public static void changePanel(String name){
		cardLayout.show(cards, name);
    }
	
	public static void goFirstPanel(){
		cardLayout.first(cards);
    }
}
