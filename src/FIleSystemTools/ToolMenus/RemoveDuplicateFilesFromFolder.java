package FIleSystemTools.ToolMenus;

import java.util.Scanner;

import Titles.ToolTitles.FileSystemTitles.RemoveDuplicateFilesFromFolderTitle;
import Utilities.RemoveDuplicateFiles;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class RemoveDuplicateFilesFromFolder {

	private RemoveDuplicateFilesFromFolderTitle removeDuplicateFilesFromFolderTitle = new RemoveDuplicateFilesFromFolderTitle();
	private IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	private SourceFolder sourceFolder = new SourceFolder();
	private RemoveDuplicateFiles removeDuplicateFiles = new RemoveDuplicateFiles();

	public void removeDuplicates() {

		Scanner scanner = new Scanner(System.in);
		boolean executed = true;
		IncludeSubfolders includeSubfolders = new IncludeSubfolders();

		String actionChoice;

		String sourceString = "";
		int subfolders = 0;

		while(executed) {
			removeDuplicateFilesFromFolderTitle.printTitle(sourceString, subfolders);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				sourceString = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				subfolders = includeSubfolders.includeSubfolders();
			}  else if(actionChoice.toLowerCase().trim().equals("3")&& sourceString != "") {
				removeDuplicateFiles.removeDuplicates(sourceString, subfolders);
				executed = false;
			}else if(actionChoice.toLowerCase().trim().equals("4")) {
				executed = false;
			}else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(sourceString == "exit" || subfolders == 3) {
				executed = false;
			}
		}
	}
}
