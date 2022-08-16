package Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapSubFolders {

	public List<File> findAllSubdirs(File file) {
	    List<File> subdirs = Arrays.asList(file.listFiles(File::isDirectory));
	    subdirs = new ArrayList<File>(subdirs);

	    List<File> deepSubdirs = new ArrayList<File>();
	    for(File subdir : subdirs) {
	        deepSubdirs.addAll(findAllSubdirs(subdir)); 
	    }
	    subdirs.addAll(deepSubdirs);
	    return subdirs;
	}
	
}
