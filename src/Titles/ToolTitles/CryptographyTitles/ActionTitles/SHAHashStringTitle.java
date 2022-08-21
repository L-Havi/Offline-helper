package Titles.ToolTitles.CryptographyTitles.ActionTitles;

public class SHAHashStringTitle {

	
	public void printTitle(String sourceString, String hashingAlgorithm, int salt) {
		String saltString = "No";
		if(salt == 1) {
			saltString = "Yes";
		}
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tHash Text with SHA");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set text to be hashed");
		System.out.println("	Current text to be hashed: " + sourceString + "\n");
		System.out.println("2.	Set hashing algorithm (Optional, Default: SHA-256)");
		System.out.println("	Current hashing algorithm: " + hashingAlgorithm + "\n");
		System.out.println("3.	Use salt (Optional, Default: No)");
		System.out.println("	Current salt: " + saltString + "\n");
		System.out.println("4. 	Start\n");			
		System.out.println("5. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}

}