package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.revature.pojos.authentication.Account;

public class AccountDAOSerialization implements AccountDAO {
	@Override
	public boolean createAccount(Account account) {
		String fileName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		String directoryName = ".//src//main//resources//accounts//";
		Path accountPath = Paths.get(directoryName);
		if (Files.notExists(accountPath, LinkOption.NOFOLLOW_LINKS)) {
			new File(directoryName).mkdir();
		}
		if (account.getUsername() != null) {
			fileName = ".//src//main//resources//accounts//" + account.getUsername() + ".dat";
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
		try (FileInputStream fis = new FileInputStream(".//src//main//resources//accounts//" + username + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			account = (Account) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return account;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return account;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return account;
		}
		
		return account;
	}


}
