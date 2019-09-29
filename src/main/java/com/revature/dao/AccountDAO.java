package com.revature.dao;

import com.revature.pojos.authentication.Account;

public interface AccountDAO {
	public void createAccount(Account account);
	public Account readAccount(String username);
}
