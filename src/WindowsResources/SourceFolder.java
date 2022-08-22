package WindowsResources;

import java.io.File;
import java.util.Scanner;

import App.Main;
import OperatingSystem.WindowsClasses.Windows;

public class SourceFolder {
	
	Scanner scanner = new Scanner(System.in);


	
	public String getSourceFolder() {
		boolean isValidFolder = false;
		String sourceString = "";
		while(!isValidFolder) {
			System.out.println("Input source folder path or type \"exit\" to leave (e.g. C:\\Temp)");
			sourceString = scanner.nextLine();
			if(!sourceString.trim().equals("exit")) {
		        File src = new File(sourceString);
		        if (!src.isDirectory()) {
		            System.out.println("Source directory does not exist.");
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
	
	public String getSourceFile() {
		boolean isValidFolder = false;
		String sourceString = "";
		while(!isValidFolder) {
			System.out.println("Input source file path or type \"exit\" to leave (e.g. C:\\Temp)");
			sourceString = scanner.nextLine();
			if(!sourceString.trim().equals("exit")) {
		        File src = new File(sourceString);
		        if (!src.isFile()) {
		            System.out.println("Source directory does not exist.");
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
	
	public String getSourceFileOrFolder() {
		boolean isValidFolder = false;
		String sourceString = "";
		while(!isValidFolder) {
			System.out.println("Input source file or folder path or type \"exit\" to leave (e.g. C:\\Temp)");
			sourceString = scanner.nextLine();
			if(!sourceString.trim().equals("exit")) {
		        File src = new File(sourceString);
		        if (!src.isFile() && !src.isDirectory()) {
		            System.out.println("Source directory does not exist.");
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
