package Utilities;

public class CorrectSizeUnit {

	public String getCorrectSizeUnit(long size){
		String sizeString;
        
		if(((double)size / (1024 * 1024 * 1024)) > 1) {
			// Size of folder in GigaBytes
			sizeString = ((double)size / (1024 * 1024 * 1024)) + " GB";
		} else if(((double)size / (1024 * 1024)) > 1) {
			// Size of folder in MegaBytes
			sizeString = ((double)size / (1024 * 1024)) + " MB";
		} else if(((double)size / 1024) > 1) {
			// Size of folder in KiloBytes
			sizeString = ((double)size / (1024 * 1024)) + " KB";
		} else {
			// Size of folder in Bytes
			sizeString = ((double)size) + " B";
		}
		return sizeString;
	}
}
