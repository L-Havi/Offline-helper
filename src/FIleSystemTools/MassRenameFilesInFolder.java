package FIleSystemTools;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import Utilities.ChooseExtensions;
import Utilities.FindFiles;
import Utilities.Name;
import Utilities.RenameFiles;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class MassRenameFilesInFolder {

	private SourceFolder sourceFolder = new SourceFolder();
	private ChooseExtensions chooseExcludedExtension = new ChooseExtensions();
	private Name name = new Name();
	private FindFiles findFiles = new FindFiles();
	private RenameFiles renameFiles = new RenameFiles();
	private IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	
	public void massRenameFiles() {
		String sourceString = sourceFolder.getSourceFolder();
		if(!sourceString.equals("exit")) {
			String[] extensions = chooseExcludedExtension.getExcludedExtensions(sourceString);
			if(!(extensions.length == 1 && extensions[0].equals("exit"))) {
				int includeSubfolder = includeSubfolders.includeSubfolders();
				if(includeSubfolder != 3) {
					String newName = name.getName();
					try {
						List<String> fileList = findFiles.findFiles(Paths.get(sourceString), extensions, includeSubfolder);
						renameFiles.renameFiles(newName, fileList);
					}catch (IOException e) {
			            e.printStackTrace();
			        }
				}
			}
		}
	}
	
}
