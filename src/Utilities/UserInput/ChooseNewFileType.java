package Utilities.UserInput;

import java.util.List;
import java.util.Scanner;

import Utilities.Lists.ExtensionList;

public class ChooseNewFileType {

	Scanner scanner = new Scanner(System.in);
	boolean isValidName = false;
	boolean hasIllegalCharacters = true;
	String sourceString;
	String newFileType;
	ExtensionList extensionList = new ExtensionList();
	List<String> allExtension = extensionList.getAllExtensions();

	public String getFileExtension() {

		while(!isValidName) {
			System.out.println("Input new filetype for file without leading comma(e.g. txt)");
			newFileType = scanner.nextLine();
			if(allExtension.contains(newFileType.trim()) || newFileType.trim().equals("exit")) {
				isValidName = true;
			} else {
				System.out.println("Extension was not recognized. Please try again");
			}
		}
        return newFileType;
	}

}
