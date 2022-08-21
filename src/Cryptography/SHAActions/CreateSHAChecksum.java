package Cryptography.SHAActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.CreateSHAChecksumTitle;
import Utilities.UserInput.ChooseSHAAlgorithm;
import Utilities.UserInput.ChooseStringInput;
import WindowsResources.SourceFolder;

public class CreateSHAChecksum {
	
	CreateSHAChecksumTitle createSHAChecksumTitle = new CreateSHAChecksumTitle();
	SourceFolder sourceFolder = new SourceFolder();
	ChooseSHAAlgorithm chooseSHAAlgorithm = new ChooseSHAAlgorithm();
	ChooseStringInput chooseStringInput = new ChooseStringInput();
	
	public void createChecksum() {
		String actionChoice;
		String srcFolder = "";
		String algorithm = "SHA-256";
		
		Scanner scanner = new Scanner(System.in);
		
		boolean run = true;
		
		while(run) {
			createSHAChecksumTitle.printTitle(srcFolder, algorithm);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				srcFolder = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				algorithm = chooseSHAAlgorithm.chooseAlgorithm();
			} else if(actionChoice.toLowerCase().trim().equals("3") && srcFolder != "" && srcFolder != "exit" && algorithm != "" && algorithm != "exit") {
				printFileChecksum(srcFolder, algorithm);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(srcFolder == "exit") {
				run = false;
			}
		}
	}
	
    private static byte[] checksum(String filePath, String algorithm) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }

        try (InputStream is = new FileInputStream(filePath);
             DigestInputStream dis = new DigestInputStream(is, md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return md.digest();

    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	
    public static void printFileChecksum(String srcFolder, String algorithm) {;

        byte[] hashInBytes = checksum(srcFolder, algorithm);
        
        System.out.println("File: " + srcFolder);
        System.out.println("Hashing algorithm: " + algorithm);
        System.out.println("Checksum: " + bytesToHex(hashInBytes));
    }

	public void verifyChecksum() {
		String actionChoice;
		String srcFolder = "";
		String algorithm = "";
		String checkSum = "";
		
		Scanner scanner = new Scanner(System.in);
		
		boolean run = true;
		
		while(run) {
			createSHAChecksumTitle.printCheckChecksumTitle(srcFolder, checkSum, algorithm);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				srcFolder = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				checkSum = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				algorithm = chooseSHAAlgorithm.chooseAlgorithm();
			} else if(actionChoice.toLowerCase().trim().equals("4") && srcFolder != "" && srcFolder != "exit" && algorithm != "" && algorithm != "exit"&& checkSum != "" && checkSum != "exit") {
				verifyFileChecksum(srcFolder, algorithm, checkSum);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(srcFolder == "exit" || checkSum == "exit" || algorithm == "exit") {
				run = false;
			}
		}
	}
    
    public static void verifyFileChecksum(String srcFolder, String algorithm, String checkSum) {

        byte[] hashInBytes = checksum(srcFolder, algorithm);
        
        if(checkSum.equals(bytesToHex(hashInBytes))) {
            System.out.println("Checksum " + bytesToHex(hashInBytes) + " matches to File " + srcFolder);
        }else {
        	System.out.println("Checksum " + bytesToHex(hashInBytes) + " does not match to File " + srcFolder);
        }
    }
}
