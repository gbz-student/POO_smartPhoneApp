package Model;

import java.io.Serializable;

/**
 * Model pour l'object numéro de télphone qui implémente la sérialisation
 * 
 * @author ken
 *
 */
public class PhoneNumber implements Serializable{
	
	private String typePhoneNumber;
	private String phoneNumber;
	private String[] typesPhoneNumber = { "", "Privé", "Maison", "Bureau" };
	
	public PhoneNumber(){
		
	}
	
	public PhoneNumber(String typePhoneNumber, String phoneNumber){
		this.typePhoneNumber = typePhoneNumber;
		this.phoneNumber = phoneNumber;
	}
	
	public String getTypePhoneNumber() {
		return typePhoneNumber;
	}
	public void setTypePhoneNumber(String typePhoneNumber) {
		this.typePhoneNumber = typePhoneNumber;
	}
	public String[] getTypesPhoneNumber() {
		return typesPhoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
		
}
