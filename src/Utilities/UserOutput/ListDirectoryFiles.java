package Utilities.UserOutput;

import java.io.File;

import Utilities.CorrectSizeUnit;
import Utilities.FolderSize;
import Utilities.MapSubFolders;

public class ListDirectoryFiles {
	
	MapSubFolders mapSubFolders = new MapSubFolders();
	FolderSize folderSize = new FolderSize();
	CorrectSizeUnit csu = new CorrectSizeUnit();
	
	public String[] getDirectoryFiles(String sourceFolder){
	    String[] pathnames;
	    File f = new File(sourceFolder);
		    pathnames = f.list();
		    if(f.list().length > 0) {
		    	long size = folderSize.getFolderSize(f);
		    	String sizeString = csu.getCorrectSizeUnit(size);
				System.out.println("------------------------------------------------------------------------");
			    System.out.println("Found " + f.list().length + " files from folder " + sourceFolder);
			    System.out.println("Size of the files in " + sourceFolder + " is " + sizeString);
				System.out.println("------------------------------------------------------------------------");
		    } else {
			    System.out.println("Found 0 files from folder " + sourceFolder);
		    }

	    return pathnames;
	}

	public String[][] getFolderPaths(String sourceFolder, String destinationString, String[] extensions){
		String folderPaths[][] = new String[extensions.length][2];
	    for (int i=0;i < extensions.length; i++) {
		    folderPaths[i][0] = sourceFolder + "\\\\" + extensions[i];
		    folderPaths[i][1] = destinationString + "\\\\" + extensions[i];
	    }
	    return folderPaths;
	}
}
