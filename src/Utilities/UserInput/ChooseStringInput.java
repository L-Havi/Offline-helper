package Utilities.UserInput;

import java.util.Scanner;

public class ChooseStringInput {

	Scanner scanner = new Scanner(System.in);
	String sourceString;
	
	public String getStringInput() {

		System.out.println("Input your text");
		sourceString = scanner.nextLine();
        return sourceString;

	}
}
