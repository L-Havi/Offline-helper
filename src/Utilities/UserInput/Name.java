package Utilities.UserInput;

import java.util.Scanner;

public class Name {
	
	Scanner scanner = new Scanner(System.in);
	boolean isValidName = false;
	boolean hasIllegalCharacters = true;
	String sourceString;
	
	public String getName() {
		while(!isValidName) {
			System.out.println("Give new name for file/files");
			sourceString = scanner.nextLine();
			String[] illegalCharacters = {"/","<",">",":","\"","\\","|","?","*"};
			for(String illegalCharacter : illegalCharacters) {
				if(sourceString.contains(illegalCharacter)) {
					hasIllegalCharacters = true;
				} else {
					hasIllegalCharacters = false;
				}
			}
	        if (hasIllegalCharacters) {
	            System.out.println("Name can't contain illegal characters (/, <, >,, :, \", \\, |, ?, *");
	        } else {
	        	isValidName = true;
	        }
		}
        return sourceString;
	}
}
