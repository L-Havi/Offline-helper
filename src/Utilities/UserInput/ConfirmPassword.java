package Utilities.UserInput;

import java.io.Console;
import java.util.Scanner;

public class ConfirmPassword {
	Scanner scanner = new Scanner(System.in);
	boolean isValidFolder = false;
	String password = "";
	String confirm = "";
	Console console = System.console();

	public String getPassword() {
		while(!isValidFolder) {
		     if (console == null)
		     {
		         System.out.print("Enter password: ");
		         password = scanner.nextLine();
		         System.out.print("Confirm password: ");
		         confirm = scanner.nextLine();
		     }
		     else
		     {
		    	 password = new String(console.readPassword("Enter password: "));
		    	 confirm = new String(console.readPassword("Confirm password: "));
		     }
		     if(password.equals(confirm)) {
		    	 isValidFolder = true;
		     } else {
		    	 System.out.print("Passwords did not match. Please try again");
		     }
		}
        return password;
	}
}
