package Titles.ToolTitles.CryptographyTitles.ActionTitles;

public class GeneratePasswordTitle {

	public void printTitle(int passwordLength, int includeLowercase, int includeUppercase, int includeNumbers, int includeSpecial){
	
		String includeLowercaseString = "No";
		if(includeLowercase == 1) {
			includeLowercaseString = "Yes";
		}		
		String includeUppercaseString = "No";
		if(includeUppercase == 1) {
			includeUppercaseString = "Yes";
		}		
		String includeNumbersString = "No";
		if(includeNumbers == 1) {
			includeNumbersString = "Yes";
		}		
		String includeSpecialString = "No";
		if(includeSpecial == 1) {
			includeSpecialString = "Yes";
		}
		
		String passwordLengthString = "";
		if(!(passwordLength == 0)) {
			passwordLengthString = String.valueOf(passwordLength);
		} 
		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tGenerate Random Password");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Password Length");
		System.out.println("	Current Password Length: " + passwordLengthString + "\n");
		System.out.println("2.	Set Lowercase Letter Usage (Optional, default: Yes)");
		System.out.println("	Current Lowercase Letter Usage: " + includeLowercaseString + "\n");
		System.out.println("3.	Set Uppercase Letter Usage (Optional, default: Yes)");
		System.out.println("	Current Uppercase Letter Usage: " + includeUppercaseString + "\n");
		System.out.println("4.	Set Number Usage (Optional, default: Yes)");
		System.out.println("	Current Number Usage: " + includeNumbersString + "\n");
		System.out.println("5.	Set Special Character Usage (Optional, default: Yes)");
		System.out.println("	Current  Special Character Usage: " + includeSpecialString + "\n");
		System.out.println("6. 	Start\n");			
		System.out.println("7. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
	
}
