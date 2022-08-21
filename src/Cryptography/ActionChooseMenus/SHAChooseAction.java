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

import Cryptography.MD5Actions.MD5HashString;
import Cryptography.SHAActions.SHAHashString;
import Titles.ToolTitles.CryptographyTitles.SHAChooseActionTitle;

public class SHAChooseAction {


	Scanner scanner = new Scanner(System.in);
	SHAChooseActionTitle shaChooseActionTitle = new SHAChooseActionTitle();
	SHAHashString shaHashString = new SHAHashString();
	
	public void shaChoose() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
		
		String actionChoice;
		boolean run = true;
		
		while(run) {
			shaChooseActionTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				shaHashString.hashString();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {

			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}
	
	
}
