package com.revature.pojos.authentication;

public class LoginAttempt {
	public LoginAttempt(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginAttempt() {
		
	}
	public String username;
	public String password;
}
