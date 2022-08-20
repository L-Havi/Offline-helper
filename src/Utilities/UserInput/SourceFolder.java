package Utilities.UserInput;

import java.io.File;
import java.util.Scanner;

import App.Main;
import OperatingSystem.WindowsClasses.Windows;

public class SourceFolder {
	
	Scanner scanner = new Scanner(System.in);
	boolean isValidFolder = false;
	String sourceString;
	
	public String getSourceFolder() {
		while(!isValidFolder) {
			System.out.println("Input source file path or type \"exit\" to leave (e.g. C:\\Temp)");
			sourceString = scanner.nextLine();
			if(!sourceString.trim().equals("exit")) {
		        File src = new File(sourceString);
		        if (!src.isFile()) {
		            System.out.println("Source file does not exist.");
		        } else {
		        	isValidFolder = true;
		        }
			} else {
				sourceString = "exit";
				isValidFolder = true;
			}

		}
        return sourceString;
	}
}
