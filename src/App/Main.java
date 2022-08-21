package App;

import java.util.InputMismatchException;
import java.util.Scanner;

import OperatingSystem.ChooseOperatingSystem;
import Titles.TitleScreen;
import WindowsResources.EncryptionToolList;
import WindowsResources.FileSystemList;
import WindowsResources.SystemToolList;

public class Main {
    public static void main(String[] args) {
    	
    	TitleScreen title = new TitleScreen();
        ChooseOperatingSystem chooseOperatingSystem = new ChooseOperatingSystem();
        
    	Scanner scanner = new Scanner(System.in);
        boolean run = true;
        
        int osInt = chooseOperatingSystem.getOperatingSystem();
        
        while (run) {
            if (osInt >= 0 && osInt <= 2) {
            	title.printTitle();
            	try {
                	String command = scanner.nextLine();
                	if (!command.trim().toLowerCase().equals("exit")) {
                		if(command.trim().toLowerCase().equals("1")) {
                	        FileSystemList fileSystemList = new FileSystemList();
                			fileSystemList.getFileSystemList(osInt);
                		} else if(command.trim().toLowerCase().equals("2")) {
                			EncryptionToolList encryptionToolList = new EncryptionToolList();
                			encryptionToolList.getEncryptionToolList(osInt);
                		} else if(command.trim().toLowerCase().equals("3")) {
                			SystemToolList systemToolList = new SystemToolList();
                			systemToolList.getSystemToolList(osInt);
                		}
                	} else {
                		run = false;
                	}
            	} catch(InputMismatchException e) {
            		scanner.nextLine();
            		System.out.println("Please give valid input (Number greater than 0)");
            	}
            } else {
            	System.out.println("OS not supported yet");
            	run = false;
            }
        }
    	scanner.close();
        System.out.println("Exitted the program.");
    }
}
