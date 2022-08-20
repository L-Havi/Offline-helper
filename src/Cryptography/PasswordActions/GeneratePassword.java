package Cryptography.PasswordActions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.GeneratePasswordTitle;
import Utilities.UserInput.ChoosePasswordLength;
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
	
}
