package Utilities.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Utilities.FileExtension;
import Utilities.Lists.ExtensionList;

public class ChooseExtensions {
	
	Scanner scanner = new Scanner(System.in);
	boolean isValidExtension;
	boolean supportedExtension;
	ExtensionList extensionList = new ExtensionList();
	FileExtension fileExtension = new FileExtension();
	
	public String[] getExcludedExtensions(String srcString){
		
		String sourceString = "";
		List<String> chosenExtensions = new ArrayList<String>();
		List<String> validExtensions = new ArrayList<String>();
		String[] finalFilePaths = new String[0];
		
		while(!isValidExtension) {
			System.out.println("Input file extensions without leading dot separated by comma (e.g. jpg, png) or type \"exit\" to leave\nInput \"*\" to choose all enxtensions");
			sourceString = scanner.nextLine();
			if(!sourceString.trim().equals("exit")) {
				if(sourceString.trim().equals("*")) {
					validExtensions = fileExtension.getUniqueFileExtensions(srcString);
			        if (!(validExtensions.size() > 0)) {
			            System.out.println("Source folder is empty.");
			        } else {
			        	finalFilePaths = new String[validExtensions.size()];
			        	validExtensions.toArray(finalFilePaths);
			        	isValidExtension = true;
			        }
				}else {
					chosenExtensions = Arrays.asList(sourceString.split(","));
			        List<String> extensions = extensionList.getAllExtensions();
			        for(String chosenExtension : chosenExtensions) {
				        	if (extensions.contains(chosenExtension)) {
				        		validExtensions.add(chosenExtension);
				        	}
			        }
			        if (!(validExtensions.size() > 0)) {
			            System.out.println("Inputted file extensions are not valid.");
			        } else {
			        	finalFilePaths = new String[chosenExtensions.size()];
			        	chosenExtensions.toArray(finalFilePaths);
			        	isValidExtension = true;
			        }
				}
			}else {
				finalFilePaths = new String[1];
				finalFilePaths[0] = "exit";
				isValidExtension = true;
			} 
		}
		return finalFilePaths;
	}
}
