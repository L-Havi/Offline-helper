package Utilities.UserInput;

import java.io.File;
import java.util.Scanner;

public class ZipOrUnzip {
	
	public String zipUnzip() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidInput = false;
		String sourceString = "";
		
			while(!isValidInput) {
				System.out.println("Do you want to zip or unzip files? (zip/unzip/exit)");
				sourceString = scanner.nextLine();
		        if (!sourceString.trim().equals("zip") && !sourceString.trim().equals("unzip") && !sourceString.trim().equals("exit")) {
		            System.out.println("Invalid input: \"" + sourceString + "\". Please type just zip, unzip or exit");
		        } else {
		        	isValidInput = true;
		        }
			}
	        return sourceString;
		
	}
	
}
