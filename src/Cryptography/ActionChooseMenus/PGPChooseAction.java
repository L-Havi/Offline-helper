package Cryptography.ActionChooseMenus;

import java.util.Scanner;

import Cryptography.PGP.PGPActions.PGPActions;
import Titles.ToolTitles.CryptographyTitles.PGPChooseActionTitle;

public class PGPChooseAction {

	PGPChooseActionTitle pgpchooseActionTitle = new PGPChooseActionTitle();
	Scanner scanner = new Scanner(System.in);
	PGPActions pgpActions = new PGPActions();

	public void pgpChoose() throws Exception {

		String actionChoice;
		boolean run = true;

		while(run) {
			pgpchooseActionTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				pgpActions.generateKeys();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				pgpActions.encrypt();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				pgpActions.decrypt();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				pgpActions.sign();
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				pgpActions.verify();
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}

}
