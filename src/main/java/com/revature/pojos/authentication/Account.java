package com.revature.pojos.authentication;

import java.io.Serializable;

import com.revature.pojos.user.User;

public class Account implements Serializable {
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", accountStatus=" + accountStatus + "]";
	}
	public String username;
	public String password;
	//public User user;
	/*public enum userType {
		EMPLOYEE, CUSTOMER
	};*/
	public String accountStatus;
	//public boolean adminRights;
	
	public Account(String username, String password, String accountStatus) {
		super();
		this.username = username;
		this.password = password;
		this.accountStatus = accountStatus;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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
	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	/*public boolean isAdminRights() {
		return adminRights;
	}
	public void setAdminRights(boolean adminRights) {
		this.adminRights = adminRights;
	}*/
}
