package application;

import java.io.FileReader;
import java.util.Scanner;

public abstract class Account {
	protected String username;
	protected String password;
	
	//getters
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
