package Cryptography.ActionChooseMenus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import Cryptography.AESEncryptionAndDecryption;
import Cryptography.ConfirmPassword;
import Cryptography.EncryptOrDecrypt;
import Cryptography.FileOrText;
import Cryptography.HasPassword;
import Cryptography.SourceFolder;
import Cryptography.AESActions.AESDecryptFile;
import Cryptography.AESActions.AESDecryptString;
import Cryptography.AESActions.AESEncryptFile;
import Cryptography.AESActions.AESEncryptString;
import Titles.ToolTitles.CryptographyTitles.AESChooseActionTitle;

public class AESChooseAction {
	
	Scanner scanner = new Scanner(System.in);
	AESChooseActionTitle aesChooseActionTitle = new AESChooseActionTitle();
	AESDecryptFile aesDecryptFile = new AESDecryptFile();
	AESDecryptString aesDecryptString = new AESDecryptString();
	AESEncryptFile aesEncryptFile = new AESEncryptFile();
	AESEncryptString aesEncryptString = new AESEncryptString();
	
	public void encryptOrDecrypt() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
		
		String actionChoice;
		boolean run = true;
		
		while(run) {
			aesChooseActionTitle.printTitle();
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				aesEncryptString.encryptString();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				aesEncryptFile.encryptFile();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				aesDecryptString.decryptString();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				aesDecryptFile.decryptFile();
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}
	
}
