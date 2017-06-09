package Contact;

import java.util.ArrayList;

import Model.Person;
import Model.PhoneNumber;

public class ContactController {
	
	public ArrayList<Person> getContacts(){
		ArrayList<Person> contacts = new ArrayList<Person>();
		ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		
		phoneNumbers.add(new PhoneNumber("privé", "0799999999"));
		phoneNumbers.add(new PhoneNumber("Maison", "0799999999"));
		
		for(int i = 0; i < 50; i++) {
			contacts.add(new Person("Test" + i, "Test", phoneNumbers, "test@gmail.com", "pet1.jpg"));
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
