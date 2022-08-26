package Utilities.UserInput;

import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.ChooseSHAAlgorithmTitle;
import Titles.ToolTitles.PdfTitles.ChooseConvertFormatTitle;

public class ChooseConvertFormat {

	Scanner scanner = new Scanner(System.in);
	ChooseConvertFormatTitle chooseConvertFormatTitle = new ChooseConvertFormatTitle();
	String actionChoice;
	
	public String chooseAlgorithm() {
		

		boolean runChoose = true;
		
		while(runChoose) {
			chooseConvertFormatTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				return "html";
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				return "jpeg";
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				return "jpg";
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				return "gif";
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				return "tiff";
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				return "png";
			} else if(actionChoice.toLowerCase().trim().equals("7")) {
				return "docx";
			} else if(actionChoice.toLowerCase().trim().equals("8")) {
				return "exit";
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}
		return "";
	}

}
