package Contact;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import MainPackage.TitleJPanel;

public class ContactJPanel extends JPanel{

	private TitleJPanel title = new TitleJPanel("Mes contacts");	
	public static JPanel cards;
	private JPanel contactListJPanel = new ContactListJPanel();
	public static CardLayout cardLayout;
	
	public ContactJPanel(){
		setPreferredSize(new Dimension(480, 750));
		setLayout(new BorderLayout());
		
		cards = new JPanel (cardLayout = new CardLayout());
		cards.add(contactListJPanel, "contactListJPanel");
		
		add(title, BorderLayout.NORTH);
		
		cards.setBackground(new Color(0,0,0,0));
		add(cards, BorderLayout.CENTER);
	}
	
	public static void removeLastPanel(){
		if(cards.getComponentCount() > 1){
			cards.remove(cards.getComponentCount() - 1);
		}
	}
	
	public static void addPanel(JPanel panel, String constraint){
		cards.add(panel, constraint, cards.getComponentCount());
		cards.validate();
    }
	
	public static void changePanel(String name){
		cardLayout.show(cards, name);
    }
	
	public static void goFirstPanel(){
		cardLayout.first(cards);
    }
}
