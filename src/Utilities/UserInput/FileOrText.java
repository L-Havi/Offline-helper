package Utilities.UserInput;

import java.util.Scanner;

public class FileOrText {
	
	public int getFileOrText() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidInput = false;
		String sourceString = "";
    	int choice = 0;
		
			while(!isValidInput) {
				System.out.println("Input is file or text? (file/text/exit)");
				sourceString = scanner.nextLine();
		        if (!sourceString.trim().equals("file") && !sourceString.trim().equals("text") && !sourceString.trim().equals("exit")) {
		            System.out.println("Invalid input: \"" + sourceString + "\". Please type just file, text or exit");
		        } else {
		        	if(sourceString.trim().equals("text")) {
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
