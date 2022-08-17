package Utilities;

import java.util.Scanner;

public class ChooseSaveMethod {
	
	Scanner scanner = new Scanner(System.in);
	boolean isValidInput = false;
	String sourceString;
	int yesOrNo = 0;
	
	public int getSaveMethod() {
		while(!isValidInput) {
			System.out.println("Save Output to Text File? (y/n)");
			sourceString = scanner.nextLine();
	        if (!sourceString.trim().equals("y") && !sourceString.trim().equals("yes") && !sourceString.trim().equals("n") && !sourceString.trim().equals("no") && !sourceString.trim().equals("exit")) {
	            System.out.println("Please input \"y/yes\" or \n/no\"");
	        } else {
	        	if(sourceString.trim().equals("y") || sourceString.trim().equals("yes")) {
	        		yesOrNo = 1;
	        	} else if(sourceString.trim().equals("exit")) {
	        		yesOrNo = 3;
	        	}
	        	isValidInput = true;
	        }
		}
        return yesOrNo;
	}
	
}
