package Utilities.UserInput;

import java.util.Random;
import java.util.Scanner;

public class ConfirmDelete {

	public boolean getDeleteConfirmation() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidinput = false;
		boolean delete = false;

		String actionChoice;

		while(!isValidinput) {
			System.out.println("Input two digits below to confirm deleting ALL Files listed above\nOr type \"exit\" to exit\n");
			Random r = new Random();
			int low = 10;
			int high = 99;
			int result = r.nextInt(high-low) + low;
			System.out.println("Confirmation Digits: " + result);
			actionChoice = scanner.nextLine();
			if(!actionChoice.trim().equals("exit")) {
				int userInput = 0;
				try {
					userInput = Integer.parseInt(actionChoice);
				}catch(NumberFormatException e) {

				}
				if(result == userInput) {
					delete = true;
					isValidinput = true;
				} else{
					System.out.println("Your input did not match Confirmation Digits. Please try again");
				}
			}else {
				isValidinput = true;
			}
		}
		return delete;
	}

}
