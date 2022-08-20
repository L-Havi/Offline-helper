package Utilities.UserInput;

import java.util.Scanner;

public class EncryptOrDecrypt {

	public int getEncryptOrDecrypt() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidInput = false;
		String sourceString = "";
		int choice = 0;
		
			while(!isValidInput) {
				System.out.println("Encrypt or decrypt? (encrypt/decrypt/exit)");
				sourceString = scanner.nextLine();
		        if (!sourceString.trim().equals("encrypt") && !sourceString.trim().equals("decrypt") && !sourceString.trim().equals("exit")) {
		            System.out.println("Invalid input: \"" + sourceString + "\". Please type just encrypt, decrypt or exit");
		        } else {
		        	if(sourceString.trim().equals("decrypt")) {
		        		choice = 1;
		        	} else if(sourceString.trim().equals("exit")) {
		        		choice = 3;
		        	}
		        	isValidInput = true;
		        }
			}
		return choice;
	}
	
}
