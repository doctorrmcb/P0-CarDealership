package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.revature.pojos.authentication.Account;

public class AccountDAOSerialization implements AccountDAO {
	@Override
	public boolean createAccount(Account account) {
		String fileName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		if (account.getUsername() != null) {
			fileName = account.getUsername() + ".dat";
		} else {
			return false;
			//fileName = null;
		}

		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(account);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return true;
	}

	@Override
	public Account readAccount(String username) {
		
		Account account = null;
		
		//try with resources
		try (FileInputStream fis = new FileInputStream(username + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			account = (Account) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
	}


}
