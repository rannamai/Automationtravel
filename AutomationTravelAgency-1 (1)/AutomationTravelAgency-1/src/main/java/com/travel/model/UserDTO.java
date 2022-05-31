package com.travel.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {
	
	
	private int userId;
	
	@NotNull
	private String name;
	
	@NotNull
	private String contactNo;
	
	@NotNull
	private String address;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	private String role;

	public UserDTO() {
		super();
	}

	

	public UserDTO(int userId, @NotNull String name, @NotNull String contactNo, @NotNull String address,
			@NotNull String userName, @NotNull String password, @NotNull @Email String email, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.contactNo = contactNo;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", contactNo=" + contactNo + ", address=" + address
				+ ", userName=" + userName + ", password=" + password + ", email=" + email + ", role=" + role + "]";
	}

}
