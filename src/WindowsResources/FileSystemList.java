package WindowsResources;

import java.util.InputMismatchException;
import java.util.Scanner;

import OperatingSystem.Linux;
import OperatingSystem.Mac;
import OperatingSystem.Windows;
import Titles.FileSystemTitle;

public class FileSystemList {
	private boolean run = true;
	private FileSystemTitle fileSystemTitle = new FileSystemTitle();
	private Scanner scanner = new Scanner(System.in);
	private Windows windows = new Windows();
	private Linux linux = new Linux();
	private Mac mac = new Mac();
	
	public void getFileSystemList(int osInt){
        
		while (run) {

			fileSystemTitle.printTitle();
            try {
                int command = scanner.nextInt();
                if (command >= 1 && command <= 8) {
                	if(command >= 1 && command <= 7) {
                		if(osInt == 0) {
                    		windows.executeGivenCommand(command);
                		} else if(osInt == 1) {
                			linux.executeGivenCommand(command);
                		}else if(osInt == 2) {
                			mac.executeGivenCommand(command);
                		}
                		run = false;
                	} else if(command == 8) {
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
