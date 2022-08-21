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
import Titles.ToolCollectionTitles.SystemTitle;

public class SystemToolList {
	private boolean run = true;
	private SystemTitle systemTitle = new SystemTitle();
	private Scanner scanner = new Scanner(System.in);
	private Windows windows = new Windows();
	private Linux linux = new Linux();
	private Mac mac = new Mac();
	private ChooseOperatingSystem chooseOperatingSystem = new ChooseOperatingSystem();
	
	public void getSystemToolList(int osInt){
        
		if(!(osInt > 0)) {
			osInt = chooseOperatingSystem.getOperatingSystem();
		}
		
		while (run) {

			systemTitle.printTitle();
            try {
                int command = scanner.nextInt();
                if (command >= 1 && command <= 3) {
                	if(command >= 1 && command <= 2) {
                		if(osInt == 0) {
							windows.executeGivenSystemInfoCommand(command);
                		} else if(osInt == 1) {
							linux.executeGivenSystemInfoCommand(command);
                		} else if(osInt == 2) {
							mac.executeGivenSystemInfoCommand(command);
                		}
                		run = false;
                	} else if(command == 3) {
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
