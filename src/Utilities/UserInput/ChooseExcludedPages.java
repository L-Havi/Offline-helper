package Utilities.UserInput;

import java.util.Scanner;

public class ChooseExcludedPages {

	public String getExcludedPages() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidinput = false;
		String sourceString = "";

			while(!isValidinput) {
				System.out.println("Input range pages included (e.g. 1-5)");
				sourceString = scanner.nextLine();
				try {
					String[] array = sourceString.split("-");
					//Check if input is valid
					for(String item : array) {
						if(item.contains(",") && item.trim() != "") {
							long testInt = Long.parseLong(item);
						}
					}
					isValidinput = true;
				} catch(NumberFormatException e) {
					isValidinput = false;
					System.out.println("Inputted value is not valid. Please try again");
				}
			}
			return sourceString;
	}

}
