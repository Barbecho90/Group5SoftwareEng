package main;

import model.Account;
import model.ROLE;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		String test = "DEALER";
		ROLE role = ROLE.valueOf(test);
		
		Account acc = new Account("Kyle", "asd", role);
		
		System.out.println(acc.getUser());
	}

}
