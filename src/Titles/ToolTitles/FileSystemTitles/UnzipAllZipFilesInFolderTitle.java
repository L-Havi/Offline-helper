package Titles.ToolTitles.FileSystemTitles;

public class UnzipAllZipFilesInFolderTitle {

	public void printTitle(String sourceFolder, String zipOrUnzip, String[] files){
		String moveOrCopy = "";
		if(zipOrUnzip.toLowerCase().trim().equals("zip")) {
			moveOrCopy = "Zip";
		}
		if(zipOrUnzip.toLowerCase().trim().equals("unzip")) {
			moveOrCopy = "Unzip";
		}
		
		String allFiles = "";
		for(String file : files) {
			if(allFiles.equals("")) {
				allFiles = allFiles + file;
			} else {
				allFiles = allFiles + ", " + file;
			}
		}
		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tUnzip/Zip Files");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source Folder");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2. 	Set to Zip/Unzip");
		System.out.println("	Zip or Unzip: " + moveOrCopy + "\n");
		System.out.println("3. 	Choose files (Optional, default: all Files)");
		System.out.println("	Current Files: " + allFiles + "\n");
		System.out.println("4. 	Start\n");			
		System.out.println("5. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
	
}
