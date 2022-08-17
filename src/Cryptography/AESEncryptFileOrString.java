package Cryptography;

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

public class AESEncryptFileOrString {
	
	EncryptOrDecrypt encryptOrDecrypt = new EncryptOrDecrypt();
	AESEncryptionAndDecryption aes = new AESEncryptionAndDecryption();
	ConfirmPassword confirmPassword = new ConfirmPassword();
	FileOrText fileOrText = new FileOrText();
	HasPassword hasPassword = new HasPassword();
	SourceFolder sourceFolder = new SourceFolder();
	String salt = "E1F53F35F353C253";
	String algorithm = "AES/CBC/PKCS5Padding";
	Scanner scanner = new Scanner(System.in);
	
	public void encryptOrDecrypt() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
		int encryptChoice = encryptOrDecrypt.getEncryptOrDecrypt();
		if(encryptChoice == 0){
			//Encrypt
			int fileChoice = fileOrText.getFileOrText();
			if(fileChoice == 0){
				//Encrypt file
				String src = sourceFolder.getSourceFolder();
				if(!src.equals("exit")) {
					//Encrypt file
					int isPasswordProtected = hasPassword.hasPassword();
					if(isPasswordProtected == 0){
						//Encrypt file w/ no password
						SecretKey key = aes.generateKey(128);
					    IvParameterSpec ivParameterSpec = aes.generateIv();
					    File inputFile = new File(src);
						int i = src.lastIndexOf("\\");
			        	String[] a =  {src.substring(0, i), src.substring(i)};
			        	int j = a[1].lastIndexOf(".");
			        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
					    File encryptedFile = new File(a[0] + b[0] + ".encrypted");
					    aes.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
					}else if(isPasswordProtected == 1){
						//Decrypt password protected file
						String password = confirmPassword.getPassword();
						SecretKey key = aes.getKeyFromPassword(password,salt);
					    IvParameterSpec ivParameterSpec = aes.generateIv();
					    File inputFile = new File(src);
						int i = src.lastIndexOf("\\");
			        	String[] a =  {src.substring(0, i), src.substring(i)};
			        	int j = a[1].lastIndexOf(".");
			        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
					    File encryptedFile = new File(a[0] + b[0] + ".encrypted");
					    aes.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
					}
				}
			}else if(fileChoice == 1) {
				//Encrypt text
				int isPasswordProtected = hasPassword.hasPassword();
				if(isPasswordProtected == 0){
					//Encrypt text w/ no password
					System.out.println("Input text to encrypt");
					String input = scanner.nextLine();
					SecretKey key = aes.generateKey(128);
					IvParameterSpec ivParameterSpec = aes.generateIv();
					String encryptedCipherText = aes.encrypt(algorithm, input, key, ivParameterSpec);
					System.out.println("Encrypted text:");
					System.out.println(encryptedCipherText);
				}else if(isPasswordProtected == 1){
					//Encrypt password protected text
					String password = confirmPassword.getPassword();
					System.out.println("Input encrupted text to decrypt");
					String plainText = scanner.nextLine();
				    IvParameterSpec ivParameterSpec = aes.generateIv();
				    SecretKey key = aes.getKeyFromPassword(password,salt);
				    String encryptedCipherText = aes.encryptPasswordBased(plainText, key, ivParameterSpec);
					System.out.println("Encrypted text:");
					System.out.println(encryptedCipherText);
				}
			}
		} else if (encryptChoice == 1) {
			//Decrypt
			int fileChoice = fileOrText.getFileOrText();
			if(fileChoice == 0){
				String src = sourceFolder.getSourceFolder();
				if(!src.equals("exit")) {
					//Decrypt file
					int isPasswordProtected = hasPassword.hasPassword();
					if(isPasswordProtected == 0){
						//Decrypt file w/ no password
						SecretKey key = aes.generateKey(128);
					    IvParameterSpec ivParameterSpec = aes.generateIv();
					    File inputFile = new File(src);
						int i = src.lastIndexOf("\\");
			        	String[] a =  {src.substring(0, i), src.substring(i)};
			        	int j = a[1].lastIndexOf(".");
			        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
					    File encryptedFile = new File(a[0] + b[0] + ".encrypted");
					    File decryptedFile = new File(a[0] + b[0] + ".decrypted");
					    aes.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
					}else if(isPasswordProtected == 1){
						//Decrypt password protected file
						String password = confirmPassword.getPassword();
						SecretKey key = aes.getKeyFromPassword(password,salt);
					    IvParameterSpec ivParameterSpec = aes.generateIv();
					    File inputFile = new File(src);
						int i = src.lastIndexOf("\\");
			        	String[] a =  {src.substring(0, i), src.substring(i)};
			        	int j = a[1].lastIndexOf(".");
			        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
					    File encryptedFile = new File(a[0] + b[0] + ".encrypted");
					    File decryptedFile = new File(a[0] + b[0] + ".decrypted");
					    aes.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
					}
				}
			}else if(fileChoice == 1) {
				//Decrypt text
				int isPasswordProtected = hasPassword.hasPassword();
				if(isPasswordProtected == 0){
					//Decrypt text w/ no password
					System.out.println("Input encrupted text to decrypt");
					String cipherText = scanner.nextLine();
					SecretKey key = aes.generateKey(128);
					IvParameterSpec ivParameterSpec = aes.generateIv();
					String decryptedCipherText = aes.decrypt(algorithm, cipherText, key, ivParameterSpec);
					System.out.println("Decrypted text:");
					System.out.println(decryptedCipherText);
				}else if(isPasswordProtected == 1){
					//Decrypt password protected text
					String password = confirmPassword.getPassword();
					System.out.println("Input encrupted text to decrypt");
					String cipherText = scanner.nextLine();
				    IvParameterSpec ivParameterSpec = aes.generateIv();
				    SecretKey key = aes.getKeyFromPassword(password,salt);
				    String decryptedCipherText = aes.decryptPasswordBased(cipherText, key, ivParameterSpec);
					System.out.println("Decrypted text:");
					System.out.println(decryptedCipherText);
				}
			}
		}
	}
	
}
