package WindowsResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveToFileExtensionFolder {
	
	public void move(String[][] destinationFiles) {
		for (int i = 0; i<destinationFiles.length; i++) {
			try {
				Files.move(Paths.get(destinationFiles[i][0]), Paths.get(destinationFiles[i][1]), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to move files from " + destinationFiles[i][0] + " to " + destinationFiles[i][1]);
			}
		}
	}
}
