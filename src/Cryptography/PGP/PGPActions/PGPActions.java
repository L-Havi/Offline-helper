package Cryptography.PGP.PGPActions;

import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.PGPActionsTitle;
import Utilities.UserInput.ChooseStringInput;
import WindowsResources.SourceFolder;

public class PGPActions {
	
	PGPActionsTitle pgpActionsTitle = new PGPActionsTitle();
	Scanner scanner = new Scanner(System.in);
	GenerateKeyPair generateKeyPair = new GenerateKeyPair();
	ChooseStringInput chooseStringInput = new ChooseStringInput();
	SourceFolder sourceFolder = new SourceFolder();
	PGPEncrypt pgpEncrypt = new PGPEncrypt();
	PGPDecrypt pgpDecrypt = new PGPDecrypt();
	PGPCreateSignature pgpCreateSignature = new PGPCreateSignature();
	PGPVerifySignature pgpVerifySignature = new PGPVerifySignature();
	
	public void generateKeys() throws Exception{
		String actionChoice;
		String userId = "";
		String password = "";
		String srcFolder = "";
		boolean run = true;
		
		while(run) {
			pgpActionsTitle.printGenerateKeypairTitle(userId, password, srcFolder);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				userId = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				password = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				srcFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("4") && password != "" && userId != "" && srcFolder != "") {
				generateKeyPair.genKeyPair(userId, password, srcFolder);
				System.out.println("Generated public & private key to Folder: " + srcFolder);
				System.out.println("Keypair User ID: " + userId);
				System.out.println("Keypair password: " + password);
				System.out.println("\n");
				System.out.println("Remember to never share your Private Key!");
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			} 
			if(userId == "exit" || password == "exit" || srcFolder == "exit") {
				run = false;
			}
		}

	}
	
	public void encrypt() throws Exception{
		String actionChoice;
		String publicKeyLocation = "";
		String encryptedFile = "";
		String plainTextFile = "";
		boolean run = true;
		
		while(run) {
			pgpActionsTitle.printEncryptTitle(publicKeyLocation, encryptedFile, plainTextFile);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				publicKeyLocation = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				encryptedFile = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				plainTextFile = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("4") && publicKeyLocation != "" && encryptedFile != "" && plainTextFile != "") {
				pgpEncrypt.encrypt(publicKeyLocation, encryptedFile, plainTextFile);
				System.out.println("Encrypted Plain Text File " + plainTextFile + " to Encrypted File " + encryptedFile + "\\encrypted.dat");
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			} 
			if(publicKeyLocation == "exit" || encryptedFile == "exit" || plainTextFile == "exit") {
				run = false;
			}
		}
	}

	public void decrypt() throws Exception{
		String actionChoice;
		String privateKeyLocation = "";
		String encryptedFile = "";
		String plainTextFile = "";
		String password = "";
		boolean run = true;
		
		while(run) {
			pgpActionsTitle.printDecryptTitle(privateKeyLocation, encryptedFile, plainTextFile, password);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				privateKeyLocation = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				encryptedFile = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				plainTextFile = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				password = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("5") && privateKeyLocation != "" && encryptedFile != "" && plainTextFile != "" && password != "") {
				pgpDecrypt.decrypt(privateKeyLocation, encryptedFile, plainTextFile, password);
				System.out.println("DEcrypted File " + encryptedFile + " to Decrypted File " + plainTextFile + "\\decrypted.dat");
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			} 
			if(privateKeyLocation == "exit" || encryptedFile == "exit" || plainTextFile == "exit" || password == "exit") {
				run = false;
			}
		}
	}
	
	public void sign() throws Exception{
		String actionChoice;
		String privateKeyLocation = "";
		String publicKeyLocation = "";
		String plainTextFile = "";
		String signatureFolder = "";
		String password = "";
		boolean run = true;
		
		while(run) {
			pgpActionsTitle.printSignTitle(privateKeyLocation, publicKeyLocation, plainTextFile, signatureFolder, password);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				privateKeyLocation = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				publicKeyLocation = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				plainTextFile = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				signatureFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				password = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("6") && privateKeyLocation != "" && publicKeyLocation != "" && plainTextFile != "" && signatureFolder != "" && password != "") {
				pgpCreateSignature.signAction(signatureFolder, plainTextFile, privateKeyLocation, publicKeyLocation, password);
				System.out.println("Creted a signature " + signatureFolder + "\\signature.txt for File " + plainTextFile);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("7")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			} 
			if(privateKeyLocation == "exit" || publicKeyLocation == "exit" || plainTextFile == "exit" || password == "exit" || signatureFolder == "exit") {
				run = false;
			}
		}
	}
	
	public void verify() throws Exception{
		String actionChoice;
		String publicKeyLocation = "";
		String plainTextFile = "";
		String signatureFile = "";
		boolean run = true;
		
		while(run) {
			pgpActionsTitle.printVerifyTitle(publicKeyLocation, plainTextFile, signatureFile);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				publicKeyLocation = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				plainTextFile = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				signatureFile = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("4") && publicKeyLocation != "" && plainTextFile != "" && signatureFile != "") {
				pgpVerifySignature.verifyAction(publicKeyLocation, plainTextFile, signatureFile);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			} 
			if(publicKeyLocation == "exit" || plainTextFile == "exit" || signatureFile == "exit") {
				run = false;
			}
		}
	}
	
}
