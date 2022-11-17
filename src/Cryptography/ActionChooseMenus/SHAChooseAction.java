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

import Cryptography.SHAActions.CreateSHAChecksum;
import Cryptography.SHAActions.SHAHashString;
import Titles.ToolTitles.CryptographyTitles.SHAChooseActionTitle;

public class SHAChooseAction {


	Scanner scanner = new Scanner(System.in);
	SHAChooseActionTitle shaChooseActionTitle = new SHAChooseActionTitle();
	SHAHashString shaHashString = new SHAHashString();
	CreateSHAChecksum createSHAChecksum = new CreateSHAChecksum();

	public void shaChoose() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {

		String action;
		boolean run = true;

		while(run) {
			shaChooseActionTitle.printTitle();
			action = scanner.nextLine();
			if(action.toLowerCase().trim().equals("1")) {
				createSHAChecksum.createChecksum();
			} else if(action.toLowerCase().trim().equals("2")) {
				shaHashString.hashString();
			} else if(action.toLowerCase().trim().equals("3")) {
				createSHAChecksum.verifyChecksum();
			} else if(action.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}


}
