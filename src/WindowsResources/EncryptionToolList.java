package WindowsResources;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import OperatingSystem.ChooseOperatingSystem;
import OperatingSystem.LinuxClasses.Linux;
import OperatingSystem.MacClasses.Mac;
import OperatingSystem.WindowsClasses.Windows;
import Titles.ToolCollectionTitles.CryptographyTitle;
import Titles.ToolCollectionTitles.FileSystemTitle;

public class EncryptionToolList {
	private boolean run = true;
	private CryptographyTitle cryptographyTitle = new CryptographyTitle();
	private Scanner scanner = new Scanner(System.in);
	private Windows windows = new Windows();
	private Linux linux = new Linux();
	private Mac mac = new Mac();
	private ChooseOperatingSystem chooseOperatingSystem = new ChooseOperatingSystem();
	
	public void getEncryptionToolList(int osInt){
        
		if(!(osInt > 0)) {
			osInt = chooseOperatingSystem.getOperatingSystem();
		}
		
		while (run) {

			cryptographyTitle.printTitle();
            try {
                int command = scanner.nextInt();
                if (command >= 1 && command <= 5) {
                	if(command >= 1 && command <= 4) {
                		if(osInt == 0) {
                    		try {
								windows.executeGivenEncryptionCommand(command);
							} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
									| InvalidAlgorithmParameterException | BadPaddingException
									| IllegalBlockSizeException | InvalidKeySpecException | IOException e) {
								e.printStackTrace();
							}
                		} else if(osInt == 1) {
                			try {
								linux.executeGivenEncryptionCommand(command);
							} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
									| InvalidAlgorithmParameterException | BadPaddingException
									| IllegalBlockSizeException | InvalidKeySpecException | IOException e) {
								e.printStackTrace();
							}
                		}else if(osInt == 2) {
                			try {
								mac.executeGivenEncryptionCommand(command);
							} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
									| InvalidAlgorithmParameterException | BadPaddingException
									| IllegalBlockSizeException | InvalidKeySpecException | IOException e) {
								e.printStackTrace();
							}
                		}
                		run = false;
                	} else if(command == 5) {
                		run = false;
                	}
                } else {
                	System.out.println("Please type valid command number (e.g. 1, 2, etc)");
                }
            } catch(InputMismatchException e) {
            	scanner.nextLine();
            	System.out.println("Please give valid input (Number greater than 0)");
            }
        }
	}
}
