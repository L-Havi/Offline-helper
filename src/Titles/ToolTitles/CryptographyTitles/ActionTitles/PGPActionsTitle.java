package Titles.ToolTitles.CryptographyTitles.ActionTitles;

public class PGPActionsTitle {

	public void printGenerateKeypairTitle(String userID, String password, String srcFolder){
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tGenerate PGP Keypair");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set User ID");
		System.out.println("	Current User ID: " + userID + "\n");
		System.out.println("2. 	Set Password");
		System.out.println("	Current Password: " + password + "\n");
		System.out.println("3. 	Set Folder Where Keypairs are Stored");
		System.out.println("	Current Folder: " + srcFolder+ "\n");
		System.out.println("4. 	Start\n");			
		System.out.println("5. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}

	public void printEncryptTitle(String publicKeyLocation, String encryptedFile, String plainTextFile) {

	System.out.println("");
	System.out.println("------------------------------------------------------------------------");
	System.out.println("\t\t\tGenerate PGP Keypair");
	System.out.println("------------------------------------------------------------------------");
	System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
	System.out.println("\n1.	Set Public Key File Location");
	System.out.println("	Current Public Key File Location: " + publicKeyLocation + "\n");
	System.out.println("2. 	Set Folder Where to Save Encrypted File");
	System.out.println("	Current Folder: " + encryptedFile + "\n");
	System.out.println("3. 	Set Plain Text File Location");
	System.out.println("	Current Plain Text File Location: " + plainTextFile + "\n");
	System.out.println("4. 	Start\n");			
	System.out.println("5. 	Exit to Main Menu\n");					
	System.out.println("------------------------------------------------------------------------");
	System.out.println("");
	
	}
}
