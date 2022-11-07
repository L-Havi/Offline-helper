package Titles.ToolTitles.CryptographyTitles.ActionTitles;

public class CreateSHAChecksumTitle {

	public void printTitle(String sourceString, String algorithm){


		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tCreate SHA Checksum");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source File");
		System.out.println("	Current Source File: " + sourceString + "\n");
		System.out.println("2.	Set hashing algorithm (Optional, Default: SHA-256)");
		System.out.println("	Current hashing algorithm: " + algorithm + "\n");
		System.out.println("3. 	Start\n");
		System.out.println("4. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}

	public void printCheckChecksumTitle(String sourceString, String checkSum, String algorithm){


		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tCheck SHA Checksum");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Source File");
		System.out.println("	Current Source File: " + sourceString + "\n");
		System.out.println("2.	Set Checksum");
		System.out.println("	Current Checksum: " + checkSum + "\n");
		System.out.println("3.	Set hashing algorithm");
		System.out.println("	Current hashing algorithm: " + algorithm + "\n");
		System.out.println("4. 	Start\n");
		System.out.println("5. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}

}
