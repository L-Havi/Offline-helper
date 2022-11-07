package Utilities;

import java.io.File;
import java.util.List;

public class RenameFiles {

	public void renameFiles(String fileName, List<String> fileList) {

		int count = 1;
		String newExtension = "";
	    for(String file : fileList) {
	        File original = new File(file);
	        int getExtension = file.lastIndexOf(".");
	        String[] ext =  {file.substring(0, getExtension), file.substring(getExtension)};
	        newExtension = ext[1];
	        int i = file.lastIndexOf("\\");
	        String[] a =  {file.substring(0, i), file.substring(i)};
	        String newName = a[0] + "\\" +  fileName + count + newExtension;
	        File rename = new File(newName);
	        original.renameTo(rename);
	        count++;
	    }
	}

}
