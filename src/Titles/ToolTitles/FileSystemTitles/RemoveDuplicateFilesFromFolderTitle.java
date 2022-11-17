package Titles.ToolTitles.FileSystemTitles;

public class RemoveDuplicateFilesFromFolderTitle {

	public void printTitle(String sourceFolder, int subfolderInt){
		String subfolderString = "No";
		if(subfolderInt == 1) {
			subfolderString = "Yes";
		}

		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tRemove Duplicate Files");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source Folder");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2. 	Include subfolders (Optional, default: No)");
		System.out.println("	Include SubFolders: " + subfolderString + "\n");
		System.out.println("3. 	Start\n");
		System.out.println("4. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}


}
