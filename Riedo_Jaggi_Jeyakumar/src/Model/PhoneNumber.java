package Model;

public class PhoneNumber {
	
	private String typePhoneNumber;
	private String phoneNumber;
	
	public PhoneNumber(String typePhoneNumber, String PhoneNumber){
		this.typePhoneNumber = typePhoneNumber;
		this.phoneNumber = phoneNumber;
	}
	
	public String getTypePhoneNumber() {
		return typePhoneNumber;
	}
	public void setTypePhoneNumber(String typePhoneNumber) {
		this.typePhoneNumber = typePhoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		phoneNumber = phoneNumber;
	}
		
}
