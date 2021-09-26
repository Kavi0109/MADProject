package com.example.toyzfor;

public class UserInfo {
	
	public String name;
	public String phone;
	public String email;
	public String password;
	
	public UserInfo(String name, String phone, String email, String password) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
}
