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
	private String SOURCE_DIR = "contacts/";
	
	public ArrayList<Contact> getContacts(){
		setContactsFromFiles();
		
		return this.contacts;
	}
	
	public void removeContact(int id){
		String filename = SOURCE_DIR + id + ".ser";
		
		try{
    		File file = new File(filename);

    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

    	}catch(Exception e){
    		e.printStackTrace();
    	}
		ContactListView contactListView = (ContactListView) ContactView.getCardsComponent(0);
		
		contactListView.updateList();
	}
	
	public void setContactsFromFiles(){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		File dir = new File(SOURCE_DIR);
		File[] files = dir.listFiles();
		
		if(files != null){
			for(File f : files){
				contacts.add(readContactFile(f.getPath()));
			}
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
		Contact contact = readContactFile(SOURCE_DIR + id + ".ser");
	    
		return contact;
	}
	
	public void createContact(String firstName, String lastName, String email, String photo, ArrayList<PhoneNumber> phoneNumbers){
		if(getContactByName(firstName, lastName) == null){
			Contact c = new Contact(firstName, lastName, phoneNumbers, email, photo);
			contacts.add(c);
			
			ContactListView contactListView = (ContactListView) ContactView.getCardsComponent(0);
				
			
			if(saveContactInFile(c)){
				contactListView.updateList();
				ContactView.changePanel("contactListView");
			}else{
				JOptionPane.showMessageDialog(new JPanel(), "Erreur d'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(new JPanel(), "Le contact existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editContact(int id, String firstName, String lastName, String email, String photo, ArrayList<PhoneNumber> phoneNumbers){
		Contact c = getContactById(id);
		
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(email);
		c.setPhoto(photo);
		c.setPhoneNumbers(phoneNumbers);
		
		ContactListView contactListView = (ContactListView) ContactView.getCardsComponent(0);
		ContactInfoView contactInfoView = (ContactInfoView) ContactView.getCardsComponent(2);
		
		if(saveContactInFile(c)){
			contactInfoView.setContact(c.getId());
			contactListView.updateList();
			ContactView.changePanel("contactInfoView");
		}else{
			JOptionPane.showMessageDialog(new JPanel(), "Erreur d'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Contact readContactFile(String filename){
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
		return c;
	}
	
	public boolean saveContactInFile(Contact c){
		ObjectOutputStream oos = null;
		boolean isSave = true;
		
		String filename = SOURCE_DIR + c.getId() + ".ser";
		
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
