package Cryptography.AESActions;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import Cryptography.AESEncryptionAndDecryption;
import Titles.ToolTitles.CryptographyTitles.ActionTitles.AESDecryptFileTitle;
import Titles.ToolTitles.CryptographyTitles.ActionTitles.AESDecryptStringTitle;
import Utilities.UserInput.ChooseStringInput;
import Utilities.UserInput.ConfirmPassword;
import Utilities.UserInput.HasPassword;
import Utilities.UserInput.SourceFolder;

public class AESDecryptString {

	AESDecryptStringTitle aesDecryptStringTitle = new AESDecryptStringTitle();
	Scanner scanner = new Scanner(System.in);
	ChooseStringInput chooseStringInput = new ChooseStringInput();
	ConfirmPassword confirmPassword = new ConfirmPassword();
	AESDecryptFileTitle aesDecryptFileTitle = new AESDecryptFileTitle();
	AESEncryptionAndDecryption aes = new AESEncryptionAndDecryption();
	SourceFolder sourceFolder = new SourceFolder();
	String salt = "E1F53F35F353C253";
	String algorithm = "AES/CBC/PKCS5Padding";
	
	public void decryptString() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
			
		String cipherText = "";
		String password = "";
		
		String actionChoice;
		
		boolean run = true;
		
		while(run) {
			aesDecryptStringTitle.printTitle(cipherText, password);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				cipherText = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				password = confirmPassword.getPassword();
			} else if(actionChoice.toLowerCase().trim().equals("3") && cipherText != "" && cipherText != "exit" && password != "exit") {
				if(!password.trim().equals("")) {
					SecretKey key = aes.generateKey(128);
					IvParameterSpec ivParameterSpec = aes.generateIv();
					String decryptedCipherText = aes.decrypt(algorithm, cipherText, key, ivParameterSpec);
					System.out.println("Decrypted text:");
					System.out.println(decryptedCipherText);
				}else {
				    IvParameterSpec ivParameterSpec = aes.generateIv();
				    SecretKey key = aes.getKeyFromPassword(password,salt);
				    String decryptedCipherText = aes.decryptPasswordBased(cipherText, key, ivParameterSpec);
					System.out.println("Decrypted text:");
					System.out.println(decryptedCipherText);
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(password == "exit" || cipherText == "exit") {
				run = false;
			}
		}
	}	
}
