package Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileExtension {

	public List <String> getUniqueFileExtensions(String sourcePath) {
		List <String> extensionList = new ArrayList<String>();
	    String[] pathnames;
	    File f = new File(sourcePath);
	    pathnames = f.list();
	    
	    for(String pathname : pathnames) {
	    	String extension = "";

	    	int i = pathname.lastIndexOf('.');
	    	int p = Math.max(pathname.lastIndexOf('/'), pathname.lastIndexOf('\\'));

	    	if (i > p) {
	    	    extension = pathname.substring(i+1);
	    	}	
	    
	    	if (!extensionList.contains(extension) && extension != null && extension != "") {
	    		extensionList.add(extension);
	    	}
	    }
	    
		return extensionList;
	}
	
}
