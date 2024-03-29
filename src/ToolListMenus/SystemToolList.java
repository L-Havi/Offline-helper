package ToolListMenus;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                if (command >= 1 && command <= 6) {
                	if(command >= 1 && command <= 5) {
                		if(osInt == 0) {
							try {
								windows.executeGivenSystemInfoCommand(command);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                		} else if(osInt == 1) {
							try {
								linux.executeGivenSystemInfoCommand(command);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                		} else if(osInt == 2) {
							try {
								mac.executeGivenSystemInfoCommand(command);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                		}
                		run = false;
                	} else if(command == 6) {
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
