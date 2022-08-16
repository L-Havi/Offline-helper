package App;

import java.util.InputMismatchException;
import java.util.Scanner;

import OperatingSystem.ListLinuxOS;
import OperatingSystem.ListMacOS;
import OperatingSystem.ListWindowsOS;
import OperatingSystem.OperatingSystem;
import Titles.TitleScreen;
import WindowsResources.FileSystemList;

public class Main {
    public static void main(String[] args) {
    	
    	TitleScreen title = new TitleScreen();
        OperatingSystem operatingSystem = new OperatingSystem();
        ListWindowsOS listWindowsOS = new ListWindowsOS();
        ListLinuxOS listLinuxOS = new ListLinuxOS();
        ListMacOS listMacOS = new ListMacOS();
        
    	Scanner scanner = new Scanner(System.in);
    	
        String operatingSystemString = operatingSystem.getOperatingSystem().trim();
        boolean run;
        boolean isWindows = false;
        boolean isLinux = false;
        boolean isMac = false;
        
        int osInt = 3;
        
        if (operatingSystemString != null) {
        	run = true;
        	isWindows = listWindowsOS.getWindowsOS(operatingSystemString);
        	isLinux = listLinuxOS.getLinuxOS(operatingSystemString);
        	isMac = listMacOS.getMacOS(operatingSystemString);
        	if(isWindows) {
        		osInt = 0;
        	}else if(isLinux) {
        		osInt = 1;
        	}else if(isMac) {
        		osInt = 2;
        	}
        	} else {
        	run = false;
        }
        
        while (run) {
            if (osInt >= 0 && osInt <= 2) {
            	title.printTitle();
            	try {
                	int command = scanner.nextInt();
                	if (command > 0 && command < 999) {
                		if(command == 1) {
                	        FileSystemList fileSystemList = new FileSystemList();
                			fileSystemList.getFileSystemList(osInt);
                		}
                	} else if(command == 999){
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
