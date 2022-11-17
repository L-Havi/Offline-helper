package WindowsResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CopyFilesToAnotherFolder {

	public void copy(String[][] destinationFiles) {
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to copy files to another folder?\nFiles with same name will be replaced", "Confirm copying Files", JOptionPane.YES_NO_OPTION);
	    if(reply == JOptionPane.YES_OPTION) {
		    for (String[] destinationFile : destinationFiles) {
			    	try {
			        	Files.copy(Paths.get(destinationFile[0]), Paths.get(destinationFile[1]), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
						System.out.println("Successully copied " + destinationFile[0] + " to folder " + destinationFile[1]);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Failed to copy " + destinationFile[0] + " to folder " + destinationFile[1]);
					}
		    }
		    JOptionPane.showMessageDialog(null, "Successfully copied Files", "Copy Files from a Folder to Another", JOptionPane.INFORMATION_MESSAGE);
	    }    
	}
}
