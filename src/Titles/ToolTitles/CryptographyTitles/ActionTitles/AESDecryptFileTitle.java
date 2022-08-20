package Titles.ToolTitles.CryptographyTitles.ActionTitles;

public class AESDecryptFileTitle {

	public void printTitle(String sourceFolder, String password){

		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tAES Decrypt File");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source Folder");
		System.out.println("	Current Source Folder: " + sourceFolder + "\n");
		System.out.println("2. 	Set Password (Optional, default: no password)");
		System.out.println("	Current Password: " + password + "\n");
		System.out.println("3. 	Start\n");			
		System.out.println("4. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
	
}
