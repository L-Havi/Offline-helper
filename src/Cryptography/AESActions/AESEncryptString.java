package Cryptography.AESActions;

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
import Cryptography.ConfirmPassword;
import Cryptography.HasPassword;
import Cryptography.SourceFolder;
import Titles.ToolTitles.CryptographyTitles.ActionTitles.AESEncryptStringTitle;
import Utilities.UserInput.ChooseStringInput;

public class AESEncryptString {

	AESEncryptStringTitle aesEncryptStringTitle = new AESEncryptStringTitle();
	ChooseStringInput chooseStringInput = new ChooseStringInput();
	AESEncryptionAndDecryption aes = new AESEncryptionAndDecryption();
	ConfirmPassword confirmPassword = new ConfirmPassword();
	HasPassword hasPassword = new HasPassword();
	SourceFolder sourceFolder = new SourceFolder();
	String salt = "E1F53F35F353C253";
	String algorithm = "AES/CBC/PKCS5Padding";
	Scanner scanner = new Scanner(System.in);
	
	public void encryptString() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
		String plainText = "";
		String password = "";
		
		String actionChoice;
		
		boolean run = true;
		
		while(run) {
			aesEncryptStringTitle.printTitle(plainText, password);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				plainText = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				password = confirmPassword.getPassword();
			} else if(actionChoice.toLowerCase().trim().equals("3") && plainText != "" && plainText != "exit" && password != "exit") {
				if(!password.trim().equals("")) {
					SecretKey key = aes.generateKey(128);
					IvParameterSpec ivParameterSpec = aes.generateIv();
					String encryptedCipherText = aes.encrypt(algorithm, plainText, key, ivParameterSpec);
					System.out.println("Encrypted text:");
					System.out.println(encryptedCipherText);
				}else {
				    IvParameterSpec ivParameterSpec = aes.generateIv();
				    SecretKey key = aes.getKeyFromPassword(password,salt);
				    String encryptedCipherText = aes.encryptPasswordBased(plainText, key, ivParameterSpec);
					System.out.println("Encrypted text:");
					System.out.println(encryptedCipherText);
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(password == "exit" || plainText == "exit") {
				run = false;
			}
		}
	}	
}
