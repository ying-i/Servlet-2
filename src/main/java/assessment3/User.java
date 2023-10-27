package assessment3;

public class User {
	private int userId;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String role;
	
	public User() {
		
	}
	
//	public User(String firstName, String lastName, String phone) {
//		
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.phone = phone;
//	}

	public User(int userId, String password, String firstName, String lastName, String phone, String role) {
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
