package Contact;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Person;
import Model.PhoneNumber;

public class ContactController {
	ArrayList<Person> contacts = new ArrayList<Person>();
	
	public ArrayList<Person> getContacts(){
		if(this.contacts.isEmpty()){
			getContactsFromFile();
		}
		return this.contacts;
	}
	
	public void getContactsFromFile(){
		ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		
		phoneNumbers.add(new PhoneNumber("Privé", "0799999999"));
		phoneNumbers.add(new PhoneNumber("Maison", "0799999999"));
		
		for(int i = 0; i < 5; i++) {
			contacts.add(new Person("Test" + i, "Test", phoneNumbers, "test@gmail.com", "pet1.jpg"));
		}
		
	}
	
	public Person getContact(String firstName, String lastName){
		Person contact = null;

	    for (Person c : contacts) {
	        if (firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName())) {
	            contact = c;
	            break;
	        }
	    }

		return contact;
	}
	
	public void createContact(String firstName, String lastName, String email, String photo, ArrayList<PhoneNumber> phoneNumbers){
	
		if(getContact(firstName, lastName) == null){
			contacts.add(new Person(firstName, lastName, phoneNumbers, email, photo));
			
			ContactListJPanel contactListJPanel = (ContactListJPanel) ContactJPanel.cards.getComponent(0);
			contactListJPanel.updateContact();	
			
			ContactJPanel.goFirstPanel();
			
			//save contact to a file
		}else{
			JOptionPane.showMessageDialog(new JPanel(), "Le contact existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void SaveEditContact(String firstName, String lastName, String email, String photo, ArrayList<PhoneNumber> phoneNumbers){
		Person c = getContact(firstName, lastName);
		int index = contacts.indexOf(c);
		
		contacts.get(index).setFirstName(firstName);
		contacts.get(index).setLastName(lastName);
		contacts.get(index).setEmail(email);
		contacts.get(index).setPhoto(photo);
		contacts.get(index).setPhoneNumbers(phoneNumbers);
		
		ContactListJPanel contactListJPanel = (ContactListJPanel) ContactJPanel.cards.getComponent(0);
		contactListJPanel.updateContact();
		ContactInfoJPanel contactInfoJPanel = (ContactInfoJPanel) ContactJPanel.cards.getComponent(2);
		contactInfoJPanel.updateContact();
		
		ContactJPanel.changePanel("contactInfo");
		
		//to save to file
	}
}
