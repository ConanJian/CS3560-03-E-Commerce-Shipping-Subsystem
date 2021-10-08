package shipping;

public class CustomerInfo {
	private int customerInformationId;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String email;
	
	/* CustomerInfo(int customer_information_id, String first_name,
				 String last_name, String[] address, String phone_number,
				 String email){
		this.customer_information_id = customer_information_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
	}*/
	
	public String[] getCustomerName() {
		String[] full_name = new String[2];
		full_name[0] = firstName;
		full_name[1] = lastName;
		
		return full_name;
	}
	
	public int getCustomerID() {
		return customerInformationId;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
}
	