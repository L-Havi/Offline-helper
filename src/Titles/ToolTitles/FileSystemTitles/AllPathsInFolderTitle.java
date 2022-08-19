package Titles.ToolTitles.FileSystemTitles;

public class AllPathsInFolderTitle {
	
	public void printTitle(String sourceFolder, String[] extensions, int subfolderInt, int textFileInt){
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
		
		String textFileString = "No";
		if(textFileInt == 1) {
			textFileString = "Yes";
		}
		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tGet Paths in Folder");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source Folder");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2. 	Set Extensions (Optional, default: all extensions)");
		System.out.println("	Current Extensions: " + allExtensions + "\n");
		System.out.println("3. 	Include subfolders (Optional, default: No)");
		System.out.println("	Include SubFolders: " + subfolderString + "\n");
		System.out.println("4. 	Set Save Results to .txt File (Optional, default: No)");
		System.out.println("	Save to text File Option: " + textFileString + "\n");
		System.out.println("5. 	Start\n");			
		System.out.println("6. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
}
