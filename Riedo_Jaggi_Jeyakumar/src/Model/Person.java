package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable{
	
	private String FirstName;
	private String LastName;
	private ArrayList<PhoneNumber> PhoneNumbers;
	private String email;
	private String photo;
	
	
	public Person(String FirstName, String LastName, ArrayList<PhoneNumber> PhoneNumbers, String email, String photo ){
		
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.PhoneNumbers = PhoneNumbers;
		this.email = email;
		this.photo = photo;
		
		
	}


	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public ArrayList<PhoneNumber> getPhoneNumbers() {
		return PhoneNumbers;
	}


	public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
		PhoneNumbers = phoneNumbers;
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
	
	
	
	
	
	

}
