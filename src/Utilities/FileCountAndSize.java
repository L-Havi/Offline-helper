package Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import WindowsResources.SourceFolder;

public class FileCountAndSize {

	private FolderSize folderSize = new FolderSize();
	private CorrectSizeUnit correctSizeUnit = new CorrectSizeUnit();
	
	public void getFileCountAndSize(List<String> fileStrings, boolean save, String sourceFolder) throws IOException {
		long totalFileSize = folderSize.getSizeForList(fileStrings);
		String totalFileSizeString = correctSizeUnit.getCorrectSizeUnit(totalFileSize);
		if(!save) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("File Count & Size in Folder (And in Subfolders if chosen):\n" + sourceFolder);
			System.out.println("------------------------------------------------------------------------");
			System.out.println("File count: " + fileStrings.size());
			System.out.println("Total File Size: " + totalFileSizeString);
		} else{
			int i = sourceFolder.lastIndexOf("\\");
        	String[] a =  {sourceFolder.substring(0, i), sourceFolder.substring(i)};
			File textFile = new File(sourceFolder + "\\" + a[1] + "_count_and_size_output.txt");
			FileOutputStream fos = new FileOutputStream(textFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			bw.write("------------------------------------------------------------------------");
			bw.newLine();
			bw.write("File Count & Size in Folder (And in Subfolders if chosen): " + sourceFolder);
			bw.newLine();
			bw.write("------------------------------------------------------------------------");
			bw.newLine();
			bw.write("File count: " + fileStrings.size());
			bw.newLine();
			bw.write("Total File Size: " + totalFileSizeString);
			bw.newLine();
			bw.close();
		}
	}
}
