package WindowsResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class MoveFilesToAnotherFolder {

	Scanner scanner = new Scanner(System.in);

	public void move(String[][] destinationFiles) {
	    System.out.println("Do you wish to replace existing folder files with same name?\nIf not files wont be moved(y/n)");
	    String yesOrNo = scanner.nextLine();
	    if(yesOrNo.toLowerCase().equals("y") || yesOrNo.toLowerCase().equals("yes")) {
	    	System.out.println("Are you sure you want to replace existing folder files?\nThis will delete ALL existing files with same name from it(y/n)");
		    yesOrNo = scanner.nextLine();
		    if(yesOrNo.toLowerCase().equals("y") || yesOrNo.toLowerCase().equals("yes")) {
			    for (String[] destinationFile : destinationFiles) {
				    	try {
				        	Files.move(Paths.get(destinationFile[0]), Paths.get(destinationFile[1]), StandardCopyOption.REPLACE_EXISTING);
							System.out.println("Successully moved files from " + destinationFile[0] + " to " + destinationFile[1]);
						} catch (IOException e) {
							e.printStackTrace();
							System.out.println("Failed to move files from " + destinationFile[0] + " to " + destinationFile[1]);
						}
		    	}
		    }
	    }
	}
}
