package Cryptography.MD5Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.digest.DigestUtils;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.CreateMD5ChecksumTitle;
import WindowsResources.SourceFolder;

public class CreateMD5Checksum {

	SourceFolder sourceFolder = new SourceFolder();
	CreateMD5ChecksumTitle createMD5ChecksumTitle = new CreateMD5ChecksumTitle();
	
	public void createChecksum() {
		String actionChoice;
		String srcFolder = "";
		
		Scanner scanner = new Scanner(System.in);
		
		boolean run = true;
		
		while(run) {
			createMD5ChecksumTitle.printTitle(srcFolder);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				srcFolder = sourceFolder.getSourceFile();
			}else if(actionChoice.toLowerCase().trim().equals("2") && srcFolder != "" && srcFolder != "exit") {
				checksum(srcFolder);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(srcFolder == "exit") {
				run = false;
			}
		}
	}
	
	public void checksum(String filepath){
		  try (InputStream is = new FileInputStream(filepath)) {
		      String checksum = DigestUtils.md5Hex(is);
		      System.out.println("Input File: " + filepath);
		      System.out.println("Checksum: " + checksum);
		  } catch (IOException e) {
		      e.printStackTrace();
		  }
	}
	
}
