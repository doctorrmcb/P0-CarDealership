package com.revature.dao;

import com.revature.pojos.authentication.Account;

public interface AccountDAO {
	public boolean createAccount(Account account);
	public Account readAccount(String username);
}
