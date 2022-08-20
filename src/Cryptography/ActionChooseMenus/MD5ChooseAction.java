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

import Cryptography.MD5Actions.CreateMD5Checksum;
import Cryptography.MD5Actions.MD5HashString;
import Titles.ToolTitles.CryptographyTitles.MD5ChooseActionTitle;

public class MD5ChooseAction {

	Scanner scanner = new Scanner(System.in);
	MD5ChooseActionTitle md5ChooseActionTitle = new MD5ChooseActionTitle();
	MD5HashString md5HashString = new MD5HashString();
	CreateMD5Checksum createMD5Checksum = new CreateMD5Checksum();
	
	public void md5Choose() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
		
		String actionChoice;
		boolean run = true;
		
		while(run) {
			md5ChooseActionTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				createMD5Checksum.createChecksum();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				md5HashString.hashString();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				createMD5Checksum.checkChecksum();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}
	
}
