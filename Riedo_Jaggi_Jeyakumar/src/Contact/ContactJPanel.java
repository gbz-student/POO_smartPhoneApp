package Contact;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import MainPackage.TitleJPanel;

public class ContactJPanel extends JPanel{

	private JTextField searchField = new JTextField();
	private JButton searchButton = new JButton("recherche");
	private JButton addContact = new JButton("add");
	private JButton back = new JButton("back");
	private JPanel searchPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private TitleJPanel title = new TitleJPanel("Mes contacts");	
	public static JPanel cards;
	private JPanel contactListJPanel = new ContactListJPanel();
	public static CardLayout cardLayout;
	
	public ContactJPanel(){
		setPreferredSize(new Dimension(480, 750));
		setLayout(new BorderLayout());
		
		cards = new JPanel (cardLayout = new CardLayout());
		cards.add(contactListJPanel, "contactListJPanel");
		
        topPanel.setLayout(new BorderLayout());
        
		searchPanel.setBackground(new Color(40,50,70));
		
		searchField.setColumns(15);
		
		searchPanel.add(title, BorderLayout.NORTH);
		
		back.addActionListener(new Back());
		searchPanel.add(back);
		
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		searchPanel.add(addContact);
		
		topPanel.add(title, BorderLayout.NORTH);
		topPanel.add(searchPanel, BorderLayout.CENTER);
		
		add(topPanel, BorderLayout.NORTH);
		
		cards.setBackground(new Color(0,0,0,0));
		add(cards, BorderLayout.CENTER);
	}
	
	class Back implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			removeLastPanel();
			cardLayout.first(cards);
		}
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
	
}
