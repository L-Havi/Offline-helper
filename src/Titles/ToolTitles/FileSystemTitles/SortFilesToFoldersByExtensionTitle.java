package Titles.ToolTitles.FileSystemTitles;

public class SortFilesToFoldersByExtensionTitle {

	public void printTitle(String sourceFolder, String[] extensions){
		String allExtensions = "";
		for(String extension : extensions) {
			if(allExtensions.equals("")) {
				allExtensions = allExtensions + extension;
			} else {
				allExtensions = allExtensions + ", " + extension;
			}
		}

		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tSort Files by Extension");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source Folder");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2. 	Set Extensions (Optional, default: all extensions)");
		System.out.println("	Current Extensions: " + allExtensions + "\n");
		System.out.println("3. 	Start\n");
		System.out.println("4. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}


}
