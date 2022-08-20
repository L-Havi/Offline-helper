package Cryptography.PasswordActions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.GeneratePasswordTitle;
import Utilities.Lists.PasswordList;
import Utilities.UserInput.ChoosePasswordLength;
import Utilities.UserInput.ChooseStringInput;
import Utilities.UserInput.HasLowercaseLetters;
import Utilities.UserInput.HasNumbers;
import Utilities.UserInput.HasSpecialCharacters;
import Utilities.UserInput.HasUppercaseLetters;

public class GeneratePassword {

	GeneratePasswordTitle generatePasswordTitle = new GeneratePasswordTitle();
	HasLowercaseLetters hasLowercaseLetters = new HasLowercaseLetters();
	HasUppercaseLetters hasUppercaseLetters = new HasUppercaseLetters();
	HasNumbers hasNumbers = new HasNumbers();
	HasSpecialCharacters hasSpecialCharacters = new HasSpecialCharacters();
	ChoosePasswordLength choosePasswordLength = new ChoosePasswordLength();
	ChooseStringInput chooseStringInput = new ChooseStringInput();
	PasswordList passwordList = new PasswordList();
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	public void generate() {
		
		String actionChoice;
		
		Scanner scanner = new Scanner(System.in);
		
		int passwordLength = 0;
		int includeLowercase = 1;
		int includeUppercase = 1;
		int includeNumbers = 1;
		int includeSpecial = 1;
		
		boolean run = true;
		
		while(run) {
			generatePasswordTitle.printTitle(passwordLength, includeLowercase, includeUppercase, includeNumbers, includeSpecial);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				passwordLength = choosePasswordLength.getLength();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				includeLowercase = hasLowercaseLetters.hasLowercaseLetters();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				includeUppercase = hasUppercaseLetters.hasUppercaseLetters();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				includeNumbers = hasNumbers.hasNumbers();
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				includeSpecial = hasSpecialCharacters.hasSpecialCharacters();
			} else if(actionChoice.toLowerCase().trim().equals("6") && passwordLength > 0 && (includeLowercase != 0 || includeUppercase != 0 || includeNumbers != 0 || includeSpecial != 0)) {
				String finalPassword = generatePassword(passwordLength, includeLowercase, includeUppercase, includeNumbers, includeSpecial);
				System.out.println("Your new randomly generated password: " + finalPassword);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("7")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(includeLowercase == 3 || includeUppercase == 3 || includeNumbers == 3 || includeSpecial == 3) {
				run = false;
			}
		}
		
	}

	private String generatePassword(int passwordLength, int includeLowercase, int includeUppercase, int includeNumbers, int includeSpecial) {
		String[] lowerCase = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] upperCase = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
		String[] specialCharacters = {"!","@","#","¤","$","%","€","/","{","(","[",")","]","=","}","?","*","'","^","¨",".",":",",",";","-","_","<",">"};
		String[] allNumbers = new String[0];
		String password = "";
		List<String> allNumbersList = new ArrayList<String>();
		if(includeLowercase == 1) {
			for(String c : lowerCase) {
				allNumbersList.add(c);
			}
		}
		if(includeUppercase == 1) {
			for(String c : upperCase) {
				allNumbersList.add(c);
			}
		}
		if(includeNumbers == 1) {
			for(String c : numbers) {
				allNumbersList.add(c);
			}
		}
		if(includeSpecial == 1) {
			for(String c : specialCharacters) {
				allNumbersList.add(c);
			}
		}
		if(allNumbersList.size() > 0) {
			allNumbers = new String[allNumbersList.size()];
			allNumbers = allNumbersList.toArray(allNumbers);
			
			Random rand = new Random();
			
			for(int i = 0; i < passwordLength; i++) {
				int choose = rand.nextInt((89 - 1) + 1) + 1;
				
				password = password + allNumbers[choose];
			}
		}
	return password;
	}

	public void check() {
		
		String actionChoice;
		
		Scanner scanner = new Scanner(System.in);
		
		String password = "";
		
		boolean run = true;
		
		while(run) {
			generatePasswordTitle.printCheckTitle(password);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				password = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("2") && password != "" && password != "exit") {
				checkPasswordStrength(password);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(password == "exit") {
				run = false;
			}
		}
		
	}
	
	private void checkPasswordStrength(String password) {
		String[] lowerCase = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		List<String> lowerCaseList = Arrays.asList(lowerCase);
		String[] upperCase = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		List<String> upperCaseList = Arrays.asList(upperCase);
		String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
		List<String> numbersList = Arrays.asList(numbers);
		String[] specialCharacters = {"!","@","#","¤","$","%","€","/","{","(","[",")","]","=","}","?","*","'","^","¨",".",":",",",";","-","_","<",">"};
		List<String> specialCharactersList = Arrays.asList(specialCharacters);
		
		boolean hasLowercase = false;
		boolean hasUppercase = false;
		boolean hasNumbers = false;
		boolean hasSpecials = false;
		
		int passwordLength = password.length();
		
		for(String item : lowerCaseList) {
			if(password.contains(item)) {
				hasLowercase = true;
				break;
			}
		}
		for(String item : upperCaseList) {
			if(password.contains(item)) {
				hasUppercase = true;
				break;
			}
		}
		for(String item : numbersList) {
			if(password.contains(item)) {
				hasNumbers = true;
				break;
			}
		}
		for(String item : specialCharactersList) {
			if(password.contains(item)) {
				hasSpecials = true;
				break;
			}
		}
		int infoLength;
		int complexity = 0;
		String bruteTime;
		String isCommonPassword = "";
		
		if(hasLowercase) {
			complexity++;
		}
		if(hasUppercase) {
			complexity++;
		}
		if(hasNumbers) {
			complexity++;
		}
		if(hasSpecials) {
			complexity++;
		}
		complexity = complexity - 1;
		
		String[][] info = 	{
				{"0 seconds","0 seconds","0 seconds","0 seconds"},
				{"0 seconds","0 seconds","0 seconds","0 seconds"},
				{"0 seconds","0 seconds","1 second"," 5 seconds"},
				{"0 seconds","25 secsonds","1 minute","6 minutes"},
				{"5 seconds","22 minutes","1 hour","8 hours"},
				{"2 minutes","19 hours","3 days","3 weeks"},
				{"58 minutes","1 month","7 months","5 years"},
				{"1 day","5 years","41 years","400 years"},
				{"3 weeks","300 years","2 0000 years","34 000 years"},
				{"1 year","16 000 years","100 000 years","2 000 000 years"},
				{"51 years","800 000 years","9 000 000 years","200 000 000 years"},
				{"1 000 years","43 000 000 years","600 000 000 years","15 000 000 000 years"},
				{"34 000 years","2 000 000 000 years","37 000 000 000 years","1 000 000 000 000 years"},
				{"800 000 years","100 000 000 000 years","2 000 000 000 000 years","93 000 000 000 000 years"},
				{"23 000 000 years","6 000 000 000 000 years","100 000 000 000 years","7 000 000 000 000 000 years"}
				};
		
		if(passwordLength <= 4) {
			infoLength = 0;
			bruteTime = "\nIt would take about " + info[infoLength][complexity] + " to brute force this password";
		}else if(passwordLength > 4 && passwordLength <= 18){
			infoLength = (passwordLength - 4);
			bruteTime = "\nIt would take about " + info[infoLength][complexity] + " to brute force this password";
		} else {
			bruteTime = "\nIt would take more than " + info[14][3] + " to brute force this password";
		}
		
		List<String> passwordList1 = passwordList.getCommonPasswords1();
		List<String> passwordList2 = passwordList.getCommonPasswords2();
		
		if(passwordList1.contains(password.trim()) || passwordList2.contains(password.trim())) {
			isCommonPassword = ANSI_RED + "[-]" + ANSI_RESET + "Password is in 10 000 most common passwords";
			bruteTime = "\nIt would take about 0 seconds to brute force this password";
		}
		
		System.out.println("Password: " + password);
		if(passwordLength < 8) {
			System.out.println(ANSI_RED + "[-]" + ANSI_RESET + "Password Length: " + passwordLength + " characters\n");
		} else {
			System.out.println(ANSI_GREEN + "[+]" + ANSI_RESET + "Password Length: " + passwordLength + " characters\n");
		}

		if(hasLowercase) {
			System.out.println(ANSI_GREEN + "[+]" + ANSI_RESET + "Password contains lowercase letters");
		} else {
			System.out.println(ANSI_RED + "[-]" + ANSI_RESET + "Password does not contain lowercase letters");	
		}
		if(hasUppercase) {
			System.out.println(ANSI_GREEN + "[+]" + ANSI_RESET + "Password contains uppercase letters");
		} else {
			System.out.println(ANSI_RED + "[-]" + ANSI_RESET + "Password does not contain uppercase letters");	
		}
		if(hasNumbers) {
			System.out.println(ANSI_GREEN + "[+]" + ANSI_RESET + "Password contains numbers");
		} else {
			System.out.println(ANSI_RED + "[-]" + ANSI_RESET + "Password does not contain numbers");	
		}
		if(hasSpecials) {
			System.out.println(ANSI_GREEN + "[+]" + ANSI_RESET + "Password contains special characters");
		} else {
			System.out.println(ANSI_RED + "[-]" + ANSI_RESET + "Password does not contain special characters");	
		}
		System.out.println("\n");	
		if(isCommonPassword != "") {
			System.out.println(isCommonPassword + "\n");	
		}
		System.out.println(bruteTime + "\n");	
		
	}
}
