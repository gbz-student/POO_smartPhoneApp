package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable{
	
	private static int index = 0;
	private int id;
	private String firstName;
	private String lastName;
	private ArrayList<PhoneNumber> phoneNumbers;
	private String email;
	private String photo;
	
	public Contact(String FirstName, String LastName, ArrayList<PhoneNumber> PhoneNumbers, String email, String photo ){	
		this.firstName = FirstName;
		this.lastName = LastName;
		this.phoneNumbers = PhoneNumbers;
		this.email = email;
		this.photo = photo;
		index++;
		this.id = index;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public static void setIndex(int index) {
		if(Contact.index < index){
			Contact.index = index;
		}
	}
}
