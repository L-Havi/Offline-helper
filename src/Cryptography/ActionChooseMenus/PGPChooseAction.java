package Cryptography.ActionChooseMenus;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
				pgpActions.signAndVerify();
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}
	
}
