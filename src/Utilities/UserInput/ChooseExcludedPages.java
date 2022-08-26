package Utilities.UserInput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Utilities.UserOutput.FindFiles;
import WindowsResources.IncludeSubfolders;

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
