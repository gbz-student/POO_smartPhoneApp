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
	
	public void createContact(String firstName, String lastName, String email, String photo, JComboBox<String>[] typeNumberLabel, JTextField[] phoneField){
		
//		JOptionPane.showMessageDialog(new JPanel(), "meessage", "Warning", JOptionPane.ERROR_MESSAGE);
		
		Boolean statut = true;
		ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		
		for (int i=0; i < phoneField.length; i++){
			if(!phoneField[i].getText().isEmpty()){
				phoneNumbers.add(new PhoneNumber(typeNumberLabel[i].getSelectedItem().toString(), phoneField[i].getText()));
			}
		}
		
		if((firstName.isEmpty()) && (lastName.isEmpty())){
			statut = false;
		}
		
		if(statut){
			contacts.add(new Person(firstName, lastName, phoneNumbers, email, photo));
			ContactJPanel.goFirstPanel();
		}else{
		    JOptionPane.showMessageDialog(new JPanel(), "Veuillez remplir les champs nom et prénom", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		
		ContactListJPanel contactListJPanel = (ContactListJPanel) ContactJPanel.cards.getComponent(0);
		contactListJPanel.updateContact();
		
	}
	
	

}
