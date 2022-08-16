package WindowsResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class CopyFilesToAnotherFolder {

	Scanner scanner = new Scanner(System.in);
	
	public void copy(String[][] destinationFiles) {
	    System.out.println("Do you wish to replace existing folder files with same name?\nIf not files wont be copied(y/n)");
	    String yesOrNo = scanner.nextLine();
	    if(yesOrNo.toLowerCase().equals("y") || yesOrNo.toLowerCase().equals("yes")) {
	    	System.out.println("Are you sure you want to replace existing folder files?\nThis will delete ALL existing files with same name from it(y/n)");
		    yesOrNo = scanner.nextLine();
		    for (int i = 0; i<destinationFiles.length; i++) {
			    	try {
			        	Files.copy(Paths.get(destinationFiles[i][0]), Paths.get(destinationFiles[i][1]), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
						System.out.println("Successully copied " + destinationFiles[i][0] + " to folder " + destinationFiles[i][1]);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Failed to copy " + destinationFiles[i][0] + " to folder " + destinationFiles[i][1]);
					}
		    }
	    } 
	}

}
