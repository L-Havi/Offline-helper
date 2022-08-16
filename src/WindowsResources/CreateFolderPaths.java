package WindowsResources;

import java.util.ArrayList;
import java.util.List;

public class CreateFolderPaths {
	
	public List<String> createPathStringsForExtensionsFolders(String sourceFolder, List<String> extensionList) {
		List <String> folderPaths = new ArrayList<String>();
		for(String extension : extensionList) {
			String currentPath = sourceFolder + "\\" + extension.toUpperCase();
			folderPaths.add(currentPath);
		}
		
		return folderPaths;
		
	}
	
}
