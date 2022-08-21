package Utilities.UserInput;

import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.SHAChooseActionTitle;

public class ChooseSHAAlgorithm {

	Scanner scanner = new Scanner(System.in);
	SHAChooseActionTitle shaChooseActionTitle = new SHAChooseActionTitle();
	
	public String chooseAlgorithm() {
		
		String actionChoice;
		String algorithm = "";
		boolean runChoose = true;
		
		while(runChoose) {
			shaChooseActionTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				algorithm = "SHA-224";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				algorithm = "SHA-256";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				algorithm = "SHA-384";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				algorithm = "SHA-512";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				algorithm = "SHA-512/224";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				algorithm = "SHA-512/256";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("7")) {
				algorithm = "SHA3-224";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("8")) {
				algorithm = "SHA3-256";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("9")) {
				algorithm = "SHA3-384";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("10")) {
				algorithm = "SHA3-512";
				runChoose = false;
			} else if(actionChoice.toLowerCase().trim().equals("11")) {
				algorithm = "exit";
				runChoose = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}
		return algorithm;
	}

}
