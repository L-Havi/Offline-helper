package WindowsResources;

import java.io.File;
import java.util.Scanner;

public class DestinationFolder {

	Scanner input = new Scanner(System.in);
	boolean isValidFolder;
	String destinationString;
	
	public String getDestinationFolder(String sourceString) {
		while(!isValidFolder) {
			System.out.println("Input destination folder path or type \"exit\" to leave  (e.g. C:\\Temp)");
			destinationString = input.nextLine();
			if(!destinationString.trim().equals("exit")) {
				if(!destinationString.trim().equals(sourceString)) {
			        File dest = new File(destinationString);
			        if (!dest.isDirectory()) {
			            System.out.println("Destination directory does not exist.");
			        } else {
			        	isValidFolder = true;
			        }
				} else {
					System.out.println("Destination folder can't be same as source folder!");
					input.nextLine();
				}
			} else {
				destinationString = "exit";
				isValidFolder = true;
			}
		}
        return destinationString;
	}
	
}
