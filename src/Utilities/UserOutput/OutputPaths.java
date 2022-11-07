package Utilities.UserOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class OutputPaths {

	public void outputPaths(List<String> paths, boolean outputToFile, String sourceFolder) throws IOException {
		if(!outputToFile) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("All File Paths in Folder (And in Subfolders if chosen):\n" + sourceFolder);
			System.out.println("------------------------------------------------------------------------");
			for(String path : paths) {
				System.out.println(path);
			}
		} else{
			int i = sourceFolder.lastIndexOf("\\");
        	String[] a =  {sourceFolder.substring(0, i), sourceFolder.substring(i)};
			File textFile = new File(sourceFolder + "\\" + a[1] + "_paths_output.txt");
			FileOutputStream fos = new FileOutputStream(textFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			bw.write("------------------------------------------------------------------------");
			bw.newLine();
			bw.write("All File Paths in Folder (And in Subfolders if chosen): " + sourceFolder);
			bw.newLine();
			bw.write("------------------------------------------------------------------------");
			bw.newLine();

			for (String path : paths) {
				bw.write(path);
				bw.newLine();
			}
			bw.close();
		}
	}

}
