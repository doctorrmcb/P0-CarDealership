package com.revature.pojos.authentication;

import com.revature.pojos.user.User;

public class Account {
	public String username;
	public String password;
	public User user;
	public enum userType {
		EMPLOYEE, CUSTOMER
	};
}
