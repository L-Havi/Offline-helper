package Utilities;

import java.io.File;
import java.util.List;

public class FolderSize {
	
    public long getFolderSize(File folder){
        long length = 0;
        File[] files = folder.listFiles();
        int count = files.length;
        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            }
            else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }
    
    public long getSizeForList(List<String> files){
        long length = 0;
        for(String fileString : files) {
            File file = new File(fileString);
            if (file.isFile()) {
                length += file.length();
            }
        }
        return length;
    }
    
}
