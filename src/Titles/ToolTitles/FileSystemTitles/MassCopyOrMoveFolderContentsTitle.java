package Titles.ToolTitles.FileSystemTitles;

public class MassCopyOrMoveFolderContentsTitle {

	public void printTitle(String sourceFolder, String destinationFolder, String moveCopy){
		String moveOrCopy = "";
		if(moveCopy.toLowerCase().trim().equals("move")) {
			moveOrCopy = "Move";
		}
		if(moveCopy.toLowerCase().trim().equals("copy")) {
			moveOrCopy = "Copy";
		}

		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tMass Copy or Move Files to Another Folder");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source Folder");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2. 	Set Destination Folder");
		System.out.println("	Current Destination Folder: " + destinationFolder + "\n");
		System.out.println("3. 	Set to Move/Copy");
		System.out.println("	Move or Copy: " + moveOrCopy + "\n");
		System.out.println("4. 	Start\n");
		System.out.println("5. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}

}
