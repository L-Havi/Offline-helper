package Utilities.UserInput;

import java.util.Scanner;

public class ChoosePasswordLength {

	public int getLength() {
		
		Scanner scanner = new Scanner(System.in);
		boolean isValidinput = false;
		int length = 0;
		
		while(!isValidinput) {
			System.out.println("Input password length");
			length = scanner.nextInt();
		    if(length > 0) {
		    	isValidinput = true;
		    }else {
		        System.out.println("Please give correct input (Type positive number)");
		        scanner.next();
		    }
				
			
		}
		return length;
		
	}
	
}
