package ToolListMenus;

import java.util.InputMismatchException;
import java.util.Scanner;

import OperatingSystem.ChooseOperatingSystem;
import OperatingSystem.LinuxClasses.Linux;
import OperatingSystem.MacClasses.Mac;
import OperatingSystem.WindowsClasses.Windows;
import Titles.ToolCollectionTitles.FileSystemTitle;
import Titles.ToolCollectionTitles.PdfToolTitle;

public class PdfToolList {
	private boolean run = true;
	private PdfToolTitle pdfToolTitle = new PdfToolTitle();
	private Scanner scanner = new Scanner(System.in);
	private Windows windows = new Windows();
	private Linux linux = new Linux();
	private Mac mac = new Mac();
	private ChooseOperatingSystem chooseOperatingSystem = new ChooseOperatingSystem();
	
	public void getPdfToolList(int osInt){
        
		if(!(osInt > 0)) {
			osInt = chooseOperatingSystem.getOperatingSystem();
		}
		
		while (run) {

			pdfToolTitle.printTitle();
            try {
                int command = scanner.nextInt();
                if (command >= 1 && command <= 7) {
                	if(command >= 1 && command <= 6) {
                		if(osInt == 0) {
                    		windows.executeGivenPdfCommand(command);
                		} else if(osInt == 1) {
                			linux.executeGivenPdfCommand(command);
                		}else if(osInt == 2) {
                			mac.executeGivenPdfCommand(command);
                		}
                		run = false;
                	} else if(command == 7) {
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
