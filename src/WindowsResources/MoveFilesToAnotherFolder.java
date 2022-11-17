package WindowsResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MoveFilesToAnotherFolder {

	public void move(String[][] destinationFiles) {
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to move files to another folder?\nFiles with same name will be replaced", "Confirm moving Files", JOptionPane.YES_NO_OPTION);
		    if(reply == JOptionPane.YES_OPTION) {
			    for (String[] destinationFile : destinationFiles) {
				    	try {
				        	Files.move(Paths.get(destinationFile[0]), Paths.get(destinationFile[1]), StandardCopyOption.REPLACE_EXISTING);
							System.out.println("Successully moved files from " + destinationFile[0] + " to " + destinationFile[1]);
						} catch (IOException e) {
							e.printStackTrace();
							System.out.println("Failed to move files from " + destinationFile[0] + " to " + destinationFile[1]);
						}
		    	}
			    JOptionPane.showMessageDialog(null, "Successfully moved Files", "Move Files from a Folder to Another", JOptionPane.INFORMATION_MESSAGE);
		    }
	}
}
