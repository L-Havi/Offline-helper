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

import Cryptography.PasswordActions.GeneratePassword;
import Titles.ToolTitles.CryptographyTitles.PasswordChooseActionTitle;

public class PasswordChooseAction {

	Scanner scanner = new Scanner(System.in);
	GeneratePassword generatePassword = new GeneratePassword();
	PasswordChooseActionTitle passwordChooseActionTitle = new PasswordChooseActionTitle();

	public void passwordChoose() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {

		String actionChoice;
		boolean run = true;

		while(run) {
			passwordChooseActionTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				generatePassword.generate();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				generatePassword.check();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}
}
