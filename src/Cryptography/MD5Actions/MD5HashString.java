package Cryptography.MD5Actions;

import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.MD5HashStringTitle;
import Utilities.UserInput.ChooseStringInput;

public class MD5HashString {

	ChooseStringInput chooseStringInput = new ChooseStringInput();
	MD5HashStringTitle md5HashStringTitle = new MD5HashStringTitle();

	public void hashString() {
		String actionChoice;
		String hashString = "";

		Scanner scanner = new Scanner(System.in);

		boolean run = true;

		while(run) {
			md5HashStringTitle.printTitle(hashString);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				hashString = chooseStringInput.getStringInput();
			}else if(actionChoice.toLowerCase().trim().equals("2") && hashString != "" && hashString != "exit") {
				hash(hashString);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(hashString == "exit") {
				run = false;
			}
		}
	}

	public String hash(String hashString){
		String hashedString = DigestUtils.md5Hex(hashString);
		return hashedString;
	}
}
