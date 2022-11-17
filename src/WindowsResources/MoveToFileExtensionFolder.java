package WindowsResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveToFileExtensionFolder {

	public void move(String[][] destinationFiles) {
		for (String[] destinationFile : destinationFiles) {
			try {
				Files.move(Paths.get(destinationFile[0]), Paths.get(destinationFile[1]), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to move files from " + destinationFile[0] + " to " + destinationFile[1]);
			}
		}
	}
}
