package Utilities.UserInput;

import java.util.Scanner;

public class HasSpecialCharacters {

	public int hasSpecialCharacters() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidInput = false;
		String sourceString = "";
    	int choice = 0;

			while(!isValidInput) {
				System.out.println("Include Special Characters? (y/n/exit)");
				sourceString = scanner.nextLine();
		        if (!sourceString.trim().equals("y") && !sourceString.trim().equals("yes") && !sourceString.trim().equals("n") && !sourceString.trim().equals("no") && !sourceString.trim().equals("exit")) {
		            System.out.println("Invalid input: \"" + sourceString + "\". Please type just yes, no or exit");
		        } else {
		        	if(sourceString.trim().equals("yes") && sourceString.trim().equals("y")) {
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
