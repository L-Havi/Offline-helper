package Titles.ToolTitles.CryptographyTitles.ActionTitles;

public class AESDecryptStringTitle {

	public void printTitle(String sourceString, String password){

		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tAES Decrypt String");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source String");
		System.out.println("	Current Source String: " + sourceString + "\n");
		System.out.println("2. 	Set Password (Optional, default: no password)");
		System.out.println("	Current Password: " + password + "\n");
		System.out.println("3. 	Start\n");			
		System.out.println("4. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
}
