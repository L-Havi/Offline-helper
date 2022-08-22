package ToolListMenus;

import java.util.InputMismatchException;
import java.util.Scanner;

import OperatingSystem.ChooseOperatingSystem;
import OperatingSystem.LinuxClasses.Linux;
import OperatingSystem.MacClasses.Mac;
import OperatingSystem.WindowsClasses.Windows;
import Titles.ToolCollectionTitles.FileSystemTitle;

public class FileSystemList {
	private boolean run = true;
	private FileSystemTitle fileSystemTitle = new FileSystemTitle();
	private Scanner scanner = new Scanner(System.in);
	private Windows windows = new Windows();
	private Linux linux = new Linux();
	private Mac mac = new Mac();
	private ChooseOperatingSystem chooseOperatingSystem = new ChooseOperatingSystem();
	
	public void getFileSystemList(int osInt){
        
		if(!(osInt > 0)) {
			osInt = chooseOperatingSystem.getOperatingSystem();
		}
		
		while (run) {

			fileSystemTitle.printTitle();
            try {
                int command = scanner.nextInt();
                if (command >= 1 && command <= 10) {
                	if(command >= 1 && command <= 9) {
                		if(osInt == 0) {
                    		windows.executeGivenCommand(command);
                		} else if(osInt == 1) {
                			linux.executeGivenCommand(command);
                		}else if(osInt == 2) {
                			mac.executeGivenCommand(command);
                		}
                		run = false;
                	} else if(command == 10) {
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
