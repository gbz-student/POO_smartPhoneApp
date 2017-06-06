package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import Model.Person;
import Model.PhoneNumber;

public class ContactListJPanel extends JPanel {
	
	private ContactInfoJPanel contactInfoJPanel;
	
	public ContactListJPanel(){
		setLayout(new BorderLayout());
		
		ArrayList<Person> contacts = getContacts();
		
		JPanel gridPanel = new JPanel(new GridLayout(0,1));
		gridPanel.setBackground(new Color(255,255,255));
		
		Border paddingBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		Border raisedetched = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199));
		for (Person contact : contacts) {
			JButton label = new JButton(contact.getFirstName() + " " + contact.getLastName());
			label.setBorder(BorderFactory.createCompoundBorder(raisedetched,paddingBorder));
			label.addActionListener(new ShowContactListener());
			label.setHorizontalAlignment(SwingConstants.LEFT);
            gridPanel.add(label);
        }
		
		JScrollPane scrollPanel = new JScrollPane(gridPanel);
	    scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
		add(scrollPanel);	
	}
	
	class ShowContactListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String string = ((JButton) e.getSource()).getText();
			String[] parts = string.split(" ");
			String firstName = parts[0]; 
			String lastName = parts[1]; 
			
			contactInfoJPanel = new ContactInfoJPanel(firstName, lastName);
			
			ContactJPanel.addPanel(contactInfoJPanel, "contactListJPanel");
			
			ContactJPanel.changePanel("contactListJPanel");	
		}
	}
	
	public ArrayList<Person> getContacts(){
		ArrayList<Person> contacts = new ArrayList();
		ArrayList<PhoneNumber> phoneNumbers = new ArrayList();
		
		phoneNumbers.add(new PhoneNumber("privé", "0799999999"));
		
		for(int i = 0; i < 50; i++) {
			contacts.add(new Person("Test" + i, "Test", phoneNumbers, "gdhdjk", "bbb"));
		}
		return contacts;
	}
	
	public Person getContact(String firstName, String lastName){
		ArrayList<Person> contacts = getContacts();
		Person contact = null;

	    for (Person c : contacts) {
	        if (firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName())) {
	            contact = c;
	            break;
	        }
	    }

		return contact;
	}
}
