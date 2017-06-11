package Contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Model.Person;
import Model.PhoneNumber;

public class ContactController {
	private ArrayList<Person> contacts = new ArrayList<Person>();
	
	public ArrayList<Person> getContacts(){
		if(this.contacts.isEmpty()){
			getContactsFromFiles();
		}
		return this.contacts;
	}
	
	public void getContactsFromFiles(){
		File repertoire = new File("contacts/");
		File[] files=repertoire.listFiles();
		
		for(File file : files){
			contacts.add(readFile(file.getPath()));
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
			Person c = new Person(firstName, lastName, phoneNumbers, email, photo);
			contacts.add(c);
			
			ContactListJPanel contactListJPanel = (ContactListJPanel) ContactJPanel.cards.getComponent(0);
			contactListJPanel.updateContact();	
			
			if(saveContact(c)){
				ContactJPanel.goFirstPanel();
			}else{
				JOptionPane.showMessageDialog(new JPanel(), "Erreur d'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(new JPanel(), "Le contact existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editContact(String firstName, String lastName, String email, String photo, ArrayList<PhoneNumber> phoneNumbers){
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
		
		if(saveContact(c)){
			ContactJPanel.goFirstPanel();
		}else{
			JOptionPane.showMessageDialog(new JPanel(), "Erreur d'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Person readFile(String filename){
		ObjectInputStream ois = null;
		Person c = null;
		
		try {
			final FileInputStream fichier = new FileInputStream(filename);
			ois = new ObjectInputStream(fichier);
			 c = (Person) ois.readObject();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return c;
	}
	
	public boolean saveContact(Person c){
		ObjectOutputStream oos = null;
		boolean isSave = true;
		
		String filename = "contacts/" + c.getFirstName().toLowerCase() + "_" + c.getLastName().toLowerCase() + ".ser";
		
		try {
			final FileOutputStream fichier = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(c);
			oos.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
			isSave = false;
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return isSave;
	}
}
