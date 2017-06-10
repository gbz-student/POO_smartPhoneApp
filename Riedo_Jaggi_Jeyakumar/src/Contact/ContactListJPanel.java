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
import Model.Person;

public class ContactListJPanel extends JPanel {
	
	private JButton addContact = new JButton("add");
	private JPanel contactListTop = new JPanel(new BorderLayout());
	private static ContactController contactController = ContactJPanel.getContactController();
	private JPanel contactInfoJPanel;
	
	JPanel gridPanel = new JPanel();
	
	ArrayList<Person> contacts = contactController.getContacts();
	
		
	public ContactListJPanel(){
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
	
	private void generateList(){
		Border paddingBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		Border raisedetched = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199));
		for (Person contact : contacts) {
			JButton label = new JButton(contact.getFirstName() + " " + contact.getLastName());
			label.setBorder(BorderFactory.createCompoundBorder(raisedetched,paddingBorder));
			label.addActionListener(new ShowContactListener());
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setMaximumSize(new Dimension(480, 70));
            gridPanel.add(label);
        }
	}
	
	public void updateContact(){
		gridPanel.removeAll();
		contacts = contactController.getContacts();
		
		generateList();
		
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
	class ShowContactListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String string = ((JButton) e.getSource()).getText();
			String[] parts = string.split(" ");
			String firstName = parts[0]; 
			String lastName = parts[1]; 
			
			contactInfoJPanel = new ContactInfoJPanel(firstName, lastName);
			
			ContactJPanel.removeLastPanel();
			
			ContactJPanel.addPanel(contactInfoJPanel, "contactInfo");
			
			ContactJPanel.changePanel("contactInfo");	
		}
	}
	
	class AddContact implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactJPanel.changePanel("contactForm");
		}
	}
	
}
