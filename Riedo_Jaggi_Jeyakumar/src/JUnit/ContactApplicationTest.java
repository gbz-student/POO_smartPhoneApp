package JUnit;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import Contact.ContactController;
import Launcher.MainJFrame;
import Model.PhoneNumber;

public class ContactApplicationTest {

	private ContactController contactController = new ContactController();
	private String firstName;
	private String lastName;
	private String email;
	private String photo;
	private MainJFrame mainJFrame = new MainJFrame();
	private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
	private String CONTACTPATH = "contacts/";
	
	@Test
	public void testAddContact() {
		//Delete all contact file
		removeAllContact();
		
		//Try to add a contact with a first name, a lastname and a phone numbers
		firstName = "Ken";
		lastName = "Jäggi";
		email = "";
		photo = "";
		phoneNumbers.add(new PhoneNumber("", "0799430071"));
		
		contactController.createContact(firstName, lastName, email, photo, phoneNumbers);
		
		File dir = new File(CONTACTPATH);
		File[] files = dir.listFiles();
		
		if(files.length != 1){
			fail("Pas de nouveau fichier, contact pas sérialisé");
		}
		
		//Try to add the same contact
		files = dir.listFiles();
		
		if(files.length != 1){
			fail("Le contact a été ajouté alors qu'il existe déja");
		}
		
		//ajout d'un contact avec un numéro non valide
		phoneNumbers.add(new PhoneNumber("", "0799430071ddddd"));
		
		//Try to add the same contact
		files = dir.listFiles();
		
		if(files.length != 1){
			fail("Le contact avec le numéro non valide à été ajouté");
		}
	}
	
	@Test
	public void testSetContacts() {
		removeAllContact();
		
		contactController.setContactsFromFiles();
		
		if(contactController.getContacts().size() != 0){
			fail("Il ne devrait pas avoir de contact...");
		}
		
		firstName = "Ken";
		lastName = "Jäggi";
		email = "";
		photo = "";
		phoneNumbers.add(new PhoneNumber("", "0799430071"));
		
		contactController.createContact(firstName, lastName, email, photo, phoneNumbers);
		
		contactController.setContactsFromFiles();
		
		if(contactController.getContacts().size() != 1){
			fail("Il devrait avoir un contact...");
		}
	}
	

	public void removeAllContact(){
		File dir = new File(CONTACTPATH);
		File[] files = dir.listFiles();
		
		for(File file : files){
			try{
	    		file.delete();

	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}	
	}
}
