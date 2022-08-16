package FIleSystemTools;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import Utilities.CreateFolder;
import Utilities.FileExtension;
import Utilities.FindFiles;
import WindowsResources.CreateFolderPaths;
import WindowsResources.MoveToFileExtensionFolder;
import WindowsResources.SourceFolder;


public class SortFilesToFoldersByExtension {
	
	private SourceFolder sourceFolder = new SourceFolder();
	private FileExtension fileExtension = new FileExtension();
	private CreateFolderPaths createFolderPaths = new CreateFolderPaths();
	private CreateFolder createFolder = new CreateFolder();
	private FindFiles findFiles = new FindFiles();
	private MoveToFileExtensionFolder moveToFileExtensionFolder = new MoveToFileExtensionFolder();
	
	public void sortFiles() {
		String sourceString = sourceFolder.getSourceFolder();
		if(!sourceString.equals("exit")) {
			List <String> extensions = fileExtension.getUniqueFileExtensions(sourceString);
			List <String> extensionPaths = createFolderPaths.createPathStringsForExtensionsFolders(sourceString, extensions);
			if(extensionPaths != null) {
				createFolder.createFolders(extensionPaths);
				for(String extension : extensions) {
					try {
						String[][] files = findFiles.findFilesByExtension(Paths.get(sourceString), extension);
				        moveToFileExtensionFolder.move(files);
				    } catch (IOException e) {
				    	e.printStackTrace();
				    }
				}
			}
		}
	}
	
}
