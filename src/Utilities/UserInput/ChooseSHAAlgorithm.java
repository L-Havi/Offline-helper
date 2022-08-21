package Utilities.UserInput;

import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.ChooseSHAAlgorithmTitle;

public class ChooseSHAAlgorithm {

	Scanner scanner = new Scanner(System.in);
	ChooseSHAAlgorithmTitle shaChooseActionTitle = new ChooseSHAAlgorithmTitle();
	String actionChoice;
	
	public String chooseAlgorithm() {
		

		boolean runChoose = true;
		
		while(runChoose) {
			shaChooseActionTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				return "SHA-224";
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				return "SHA-256";
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				return "SHA-384";
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				return "SHA-512";
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				return "SHA-512/224";
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				return "SHA-512/256";
			} else if(actionChoice.toLowerCase().trim().equals("7")) {
				return "SHA3-224";
			} else if(actionChoice.toLowerCase().trim().equals("8")) {
				return "SHA3-256";
			} else if(actionChoice.toLowerCase().trim().equals("9")) {
				return "SHA3-384";
			} else if(actionChoice.toLowerCase().trim().equals("10")) {
				return "SHA3-512";
			} else if(actionChoice.toLowerCase().trim().equals("11")) {
				return "exit";
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}
		return "";
	}

}
