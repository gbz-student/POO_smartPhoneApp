package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Launcher.MainJFrame;
import Model.Contact;
/**
 * Cette vue affiche une liste de contact
 * @author ken
 *
 */
public class ContactListView extends JPanel {
	
	private JButton addContact = new JButton("add");
	private JPanel contactListTop = new JPanel(new BorderLayout());
	private JPanel gridPanel = new JPanel();
	private ContactInfoView contactInfoView;
	private static ContactController contactController = ContactView.getContactController();
	private ArrayList<Contact> contacts = contactController.getContacts();
	
	/**
	 * Construit ma liste de contact pour être affiché
	 */
	public ContactListView(){
		setLayout(new BorderLayout());  
		
		contactListTop.setBackground(new Color(40,50,70));
		
		addContact.addActionListener(new AddContact());
		contactListTop.add(addContact, BorderLayout.EAST);
		
		add(contactListTop, BorderLayout.NORTH);
		
		gridPanel.setLayout(new BoxLayout(gridPanel, BoxLayout.Y_AXIS));
		gridPanel.setBackground(new Color(255,255,255));		
		
		generateList();
		
		JScrollPane scrollPanel = new JScrollPane(gridPanel);
	    scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
		add(scrollPanel, BorderLayout.CENTER);	
	}
	
	/**
	 *  Génère ma liste avec le bon design
	 */
	private void generateList(){
		Border paddingBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		Border raisedetched = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199));
		for (Contact contact : contacts) {
			JButton label = new JButton(contact.getFirstName() + " " + contact.getLastName());
			label.setName(String.valueOf(contact.getId()));
			label.setBorder(BorderFactory.createCompoundBorder(raisedetched,paddingBorder));
			label.addActionListener(new ShowContactListener());
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setMaximumSize(new Dimension(MainJFrame.getWidthJFrame(), 70));
            gridPanel.add(label);
        }
	}
	
	/**
	 * Cette méthode permet de rafraichir la vue.
	 * Elle est appelé après l'enregistrement d'un nouveau contact
	 */
	public void updateList(){
		gridPanel.removeAll();
		contacts = contactController.getContacts();
		
		generateList();
		
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
	/**
	 * Listener pour les boutons permettant de voir les informations d'un contact
	 * @author ken
	 *
	 */
	class ShowContactListener implements ActionListener{
		public void actionPerformed(ActionEvent e) { 
			JButton button = ((JButton) e.getSource());
			
			contactInfoView = (ContactInfoView) ContactView.getCardsComponent(2);
			
			contactInfoView.setContact(Integer.parseInt(button.getName()));

			ContactView.changePanel("contactInfoView");	
		}
	}
	
	/**
	 * Listener pour le bouton d'ajout d'un contact
	 * @author ken
	 *
	 */
	class AddContact implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactFormView contactFormView = (ContactFormView) ContactView.getCardsComponent(1);
			contactFormView.formFunction = 0;
			contactFormView.resetField();
			ContactView.changePanel("contactFormView");
		}
	}
}
