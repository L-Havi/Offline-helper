package Cryptography.AESActions;

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
import Titles.ToolTitles.CryptographyTitles.ActionTitles.AESDecryptFileTitle;
import Utilities.UserInput.ConfirmPassword;
import Utilities.UserInput.EncryptOrDecrypt;
import Utilities.UserInput.FileOrText;
import Utilities.UserInput.HasPassword;
import Utilities.UserInput.SourceFolder;

public class AESDecryptFile {

	AESDecryptFileTitle aesDecryptFileTitle = new AESDecryptFileTitle();
	AESEncryptionAndDecryption aes = new AESEncryptionAndDecryption();
	ConfirmPassword confirmPassword = new ConfirmPassword();
	HasPassword hasPassword = new HasPassword();
	SourceFolder sourceFolder = new SourceFolder();
	String salt = "E1F53F35F353C253";
	String algorithm = "AES/CBC/PKCS5Padding";
	Scanner scanner = new Scanner(System.in);
	
	public void decryptFile() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
					
		String srcFolder = "";
		String password = "";
		
		String actionChoice;
		
		boolean run = true;
		
		while(run) {
			aesDecryptFileTitle.printTitle(srcFolder, password);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				srcFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				password = confirmPassword.getPassword();
			} else if(actionChoice.toLowerCase().trim().equals("3") && srcFolder != "" && srcFolder != "exit" && password != "exit") {
				if(!password.trim().equals("")) {
					SecretKey key = aes.getKeyFromPassword(password,salt);
				    IvParameterSpec ivParameterSpec = aes.generateIv();
				    File inputFile = new File(srcFolder);
					int i = srcFolder.lastIndexOf("\\");
		        	String[] a =  {srcFolder.substring(0, i), srcFolder.substring(i)};
		        	int j = a[1].lastIndexOf(".");
		        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
				    File decryptedFile = new File(a[0] + b[0] + ".decrypted");
				    aes.decryptFile(algorithm, key, ivParameterSpec, inputFile, decryptedFile);
				}else {
					SecretKey key = aes.generateKey(128);
				    IvParameterSpec ivParameterSpec = aes.generateIv();
				    File inputFile = new File(srcFolder);
					int i = srcFolder.lastIndexOf("\\");
		        	String[] a =  {srcFolder.substring(0, i), srcFolder.substring(i)};
		        	int j = a[1].lastIndexOf(".");
		        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
				    File decryptedFile = new File(a[0] + b[0] + ".decrypted");
				    aes.decryptFile(algorithm, key, ivParameterSpec, inputFile, decryptedFile);
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(password == "exit" || srcFolder == "exit") {
				run = false;
			}
		}
	}
	
}