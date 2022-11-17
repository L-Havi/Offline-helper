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
	System.out.println("\t\t\tEncrypt with PGP");
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

	public void printDecryptTitle(String privateKeyLocation, String encryptedFile, String plainTextFile,
			String password) {
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tDecrypt with PGP");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Private Key File Location");
		System.out.println("	Current Private Key File Location: " + privateKeyLocation + "\n");
		System.out.println("2. 	Set Save Encrypted File Location");
		System.out.println("	Current Encrypted File Location: " + encryptedFile + "\n");
		System.out.println("3. 	Set Folder Where to Save Decrypted File");
		System.out.println("	Current Folder Location: " + plainTextFile + "\n");
		System.out.println("4. 	Set Private Key's Password");
		System.out.println("	Current Password: " + password + "\n");
		System.out.println("5. 	Start\n");
		System.out.println("6. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");

	}

	public void printSignTitle(String privateKeyLocation, String publicKeyLocation, String plainTextFile,
			String signatureFolder, String password) {
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tSign with PGP");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Private Key File Location");
		System.out.println("	Current Private Key File Location: " + privateKeyLocation + "\n");
		System.out.println("2.	Set Public Key File Location");
		System.out.println("	Current Public Key File Location: " + publicKeyLocation + "\n");
		System.out.println("3. 	Set File Location for File to be Signed");
		System.out.println("	Current File to be Signed: " + plainTextFile + "\n");
		System.out.println("4. 	Set Folder Where to Save Signature File");
		System.out.println("	Current Folder Location: " + signatureFolder + "\n");
		System.out.println("5. 	Set Private Key's Password");
		System.out.println("	Current Password: " + password + "\n");
		System.out.println("6. 	Start\n");
		System.out.println("7. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");

	}

	public void printVerifyTitle(String publicKeyLocation, String plainTextFile, String signatureFile) {
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tVerify Signature with PGP");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Public Key File Location");
		System.out.println("	Current Public Key File Location: " + publicKeyLocation + "\n");
		System.out.println("2. 	Set File Whose Signature needs verifying");
		System.out.println("	Current File: " + plainTextFile + "\n");
		System.out.println("3. 	Set Signature File Location");
		System.out.println("	Current Signature File Location: " + signatureFile + "\n");
		System.out.println("4. 	Start\n");
		System.out.println("5. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
}
