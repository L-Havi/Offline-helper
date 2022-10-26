package Titles.ToolTitles.FileSystemTitles;

public class MassChangeFileTypeTitle {

	public void printTitle(String sourceFolder, String[] extensions, int subfolderInt, String newFileType){
		String allExtensions = "";
		for(String extension : extensions) {
			if(allExtensions.equals("")) {
				allExtensions = allExtensions + extension;
			} else {
				allExtensions = allExtensions + ", " + extension;
			}
		}
		String subfolderString = "No";
		if(subfolderInt == 1) {
			subfolderString = "Yes";
		}
			
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tMass Change Filetypes");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("NOTE! Changing file extension might have unexpected consequences!\nPlease use this feature only if you know what you are doing");
		System.out.println("\n1.	Set Source Folder");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2. 	Set Extensions (Optional, default: all extensions)");
		System.out.println("	Current Extensions: " + allExtensions + "\n");
		System.out.println("3. 	Include subfolders (Optional, default: No)");
		System.out.println("	Include SubFolders: " + subfolderString + "\n");
		System.out.println("4. 	Set new Extension for Chosen Files");
		System.out.println("	New Extension: " + newFileType + "\n");
		System.out.println("5. 	Start\n");			
		System.out.println("6. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
}
