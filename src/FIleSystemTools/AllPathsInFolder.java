package FIleSystemTools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Utilities.ChooseExtensions;
import Utilities.ChooseSaveMethod;
import Utilities.FindFiles;
import Utilities.OutputPaths;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class AllPathsInFolder {
	
	SourceFolder sourceFolder = new SourceFolder();
	ChooseExtensions chooseExtensions = new ChooseExtensions();
	IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	FindFiles findFiles = new FindFiles();
	ChooseSaveMethod chooseSaveMethod = new ChooseSaveMethod();
	OutputPaths outputPaths = new OutputPaths();
	
	public void getAllPathsInFolder() throws IOException {
		String srcFolder = sourceFolder.getSourceFolder();
		if(!srcFolder.equals("exit")) {
			String[] extensions = chooseExtensions.getExcludedExtensions(srcFolder);
			if(!(extensions.length == 1 && extensions[0].equals("exit"))){
				int subfolders = includeSubfolders.includeSubfolders();
				if(!(subfolders == 3)) {
					int saveToTextFile = chooseSaveMethod.getSaveMethod();
					if(!(saveToTextFile == 3)) {
						List<String> fileStrings = new ArrayList<String>();
						boolean save = false;
						if(saveToTextFile == 1) {
							save = true;
						}
						try {
							fileStrings = findFiles.findFiles(Paths.get(srcFolder), extensions, subfolders);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(fileStrings.size() > 0) {
							outputPaths.outputPaths(fileStrings, save, srcFolder);
						}
					}
				}
			}
		}
	}
	
}
