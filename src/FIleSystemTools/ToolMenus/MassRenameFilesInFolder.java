package FIleSystemTools.ToolMenus;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Titles.ToolTitles.FileSystemTitles.MassRenameFilesInFolderTitle;
import Utilities.RenameFiles;
import Utilities.UserInput.ChooseExtensions;
import Utilities.UserInput.Name;
import Utilities.UserOutput.FindFiles;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class MassRenameFilesInFolder {

	private SourceFolder sourceFolder = new SourceFolder();
	private ChooseExtensions chooseExtensions = new ChooseExtensions();
	private Name name = new Name();
	private FindFiles findFiles = new FindFiles();
	private RenameFiles renameFiles = new RenameFiles();
	private IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	private MassRenameFilesInFolderTitle massRenameFilesInFolderTitle = new MassRenameFilesInFolderTitle();
	
	public void massRenameFiles() {
		String srcFolder = "";
		String[] extensions = {"*"};
		int subfolders = 0;
		String newName = "";
		String actionChoice;
		
		Scanner scanner = new Scanner(System.in);
		
		boolean run = true;
		
		while(run) {
			massRenameFilesInFolderTitle.printTitle(srcFolder, extensions, subfolders, newName);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				srcFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2") && srcFolder != "" && srcFolder != "exit") {
				extensions = chooseExtensions.getExcludedExtensions(srcFolder);
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				subfolders = includeSubfolders.includeSubfolders();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				newName = name.getName();
			} else if(actionChoice.toLowerCase().trim().equals("5") && srcFolder != "" && srcFolder != "exit" && newName != "" && newName != "exit") {
				try {
					List<String> fileList = findFiles.findFiles(Paths.get(srcFolder), extensions, subfolders);
					renameFiles.renameFiles(newName, fileList);
				}catch (IOException e) {
		            e.printStackTrace();
		        }
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(subfolders == 3 || newName == "exit" || (extensions.length == 1 && extensions[0].equals("exit")) || srcFolder == "exit") {
				run = false;
			}
		}
	}
	
}
