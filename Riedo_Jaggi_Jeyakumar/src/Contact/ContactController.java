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
import Model.Contact;
import Model.PhoneNumber;

public class ContactController {
	private ArrayList<Contact> contacts = new ArrayList<Contact>();
	private File repertoire = new File("contacts/");
	
	public ArrayList<Contact> getContacts(){
		
		getContactsFromFiles();
		
		return this.contacts;
	}
	
	public void getContactsFromFiles(){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		File[] files=repertoire.listFiles();
		
		for(File file : files){
			contacts.add(readFile(file.getPath()));
		}
		
		this.contacts = contacts;
	}
	
	public Contact getContactByName(String firstName, String lastName){
		Contact contact = null;

	    for (Contact c : contacts) {
	        if (firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName())) {
	            contact = c;
	            break;
	        }
	    }
		return contact;
	}
	
	public Contact getContactById(int id){
		Contact contact = readFile("contacts/" + id + ".ser");
	    
		return contact;
	}
	
	public void createContact(String firstName, String lastName, String email, String photo, ArrayList<PhoneNumber> phoneNumbers){
		if(getContactByName(firstName, lastName) == null){
			Contact c = new Contact(firstName, lastName, phoneNumbers, email, photo);
			contacts.add(c);
			
			ContactListJPanel contactListJPanel = (ContactListJPanel) ContactJPanel.getCardsComponent(0);
			contactListJPanel.updateList();	
			
			if(saveContact(c)){
				ContactJPanel.goFirstPanel();
			}else{
				JOptionPane.showMessageDialog(new JPanel(), "Erreur d'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(new JPanel(), "Le contact existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editContact(int id, String firstName, String lastName, String email, String photo, ArrayList<PhoneNumber> phoneNumbers){
//		Contact c = getContactByName(firstName, lastName);
//		int index = contacts.indexOf(c);
//		
//		contacts.get(index).setFirstName(firstName);
//		contacts.get(index).setLastName(lastName);
//		contacts.get(index).setEmail(email);
//		contacts.get(index).setPhoto(photo);
//		contacts.get(index).setPhoneNumbers(phoneNumbers);
		
		Contact c = getContactById(id);
		
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(email);
		c.setPhoto(photo);
		c.setPhoneNumbers(phoneNumbers);
		
		ContactListJPanel contactListJPanel = (ContactListJPanel) ContactJPanel.getCardsComponent(0);
		contactListJPanel.updateList();
		ContactInfoJPanel contactInfoJPanel = (ContactInfoJPanel) ContactJPanel.getCardsComponent(2);
		
		
		if(saveContact(c)){
			contactInfoJPanel.setContact(c.getId());
			ContactJPanel.changePanel("contactInfoJPanel");
		}else{
			JOptionPane.showMessageDialog(new JPanel(), "Erreur d'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Contact readFile(String filename){
		ObjectInputStream ois = null;
		Contact c = null;
		
		try {
			final FileInputStream fichier = new FileInputStream(filename);
			ois = new ObjectInputStream(fichier);
			 c = (Contact) ois.readObject();
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
		
		Contact.setIndex(c.getId());
		System.out.println(c.getId());
		return c;
	}
	
	public boolean saveContact(Contact c){
		ObjectOutputStream oos = null;
		boolean isSave = true;
		
		String filename = "contacts/" + c.getId() + ".ser";
		
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
