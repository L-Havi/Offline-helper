package Titles.ToolTitles.FileSystemTitles;

public class UnzipAllZipFilesInFolderTitle {

	public void printTitle(String sourceFolder, String zippingDestination, String name){

		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tUnzip/Zip Files");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source Folder or File (Choose zip file path if you want to unzip)");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2.	Set Output Folder");
		System.out.println("	Current Folder: " + zippingDestination + "\n");
		System.out.println("3.	Set name for new zip file (Optional, Default: zipFile)");
		System.out.println("	Use only when zipping, not unzipping!");
		System.out.println("	Current Name: " + name + "\n");
		System.out.println("4. 	Start\n");
		System.out.println("5. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}


}
