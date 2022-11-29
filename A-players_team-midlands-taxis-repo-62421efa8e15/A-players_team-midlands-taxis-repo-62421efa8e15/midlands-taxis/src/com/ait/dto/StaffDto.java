package com.ait.dto;

public class StaffDto {

	private int staffId;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private UserCategory userCategory;
	private String password;
	
	public StaffDto() {
	}
	
	public StaffDto(int staffId, String firstName, String lastName, String username, String email, String userCategory) {
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.userCategory = UserCategory.valueOf(userCategory);
	}
	
	public StaffDto(String firstName, String lastName, String username, String email, String userCategory, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.userCategory = UserCategory.valueOf(userCategory);
		this.password = password;
	}
	
	public boolean checkCategory(String category) {
		return userCategory.name().equals(category);
	}
	
	public int getStaffId() {
		return staffId;
	}
	
	public void setStaffId(int staffId) {
		this.staffId = staffId;
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "StaffDto [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", email=" + email + ", userCategory=" + userCategory + ", password=" + password + "]";
	}
}
