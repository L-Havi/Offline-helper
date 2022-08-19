package Utilities.UserInput;

import java.util.Scanner;

public class ChooseMoveOrCopy {

	public String getMoveOrCopy() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidinput = false;
		String moveOrCopy = "";
		
		while(!isValidinput) {
			System.out.println("Do you wish to move or copy files?(move/copy/exit)");
		    moveOrCopy = scanner.nextLine();
		    if(moveOrCopy.toLowerCase().trim().equals("move") || moveOrCopy.toLowerCase().trim().equals("copy") || moveOrCopy.toLowerCase().trim().equals("exit")) {
		    	isValidinput = true;
		    }else {
		        System.out.println("Please give correct input (Type move, copy or exit)");
		    }
				
			
		}
		return moveOrCopy;
	}
	
}
