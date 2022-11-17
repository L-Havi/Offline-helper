package FIleSystemTools.ToolMenus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Titles.ToolTitles.FileSystemTitles.MassChangeFileTypeTitle;
import Utilities.UserInput.ChooseExtensions;
import Utilities.UserInput.ChooseNewFileType;
import Utilities.UserOutput.FindFiles;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class MassChangeFileType {

	private SourceFolder sourceFolder = new SourceFolder();
	private ChooseExtensions chooseExtensions = new ChooseExtensions();
	private IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	private FindFiles findFiles = new FindFiles();
	private Scanner scanner = new Scanner(System.in);
	MassChangeFileTypeTitle massChangeFileTypeTitle = new MassChangeFileTypeTitle();
	ChooseNewFileType chooseNewFileType = new ChooseNewFileType();


	public void changeAllFileExtensions() {

		String actionChoice;

		String sourceString = "";
		String[] extensions = {"*"};
		int subFolders = 0;
		String newFileType = "";

		boolean run = true;

		while(run) {
			massChangeFileTypeTitle.printTitle(sourceString, extensions, subFolders, newFileType);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				sourceString = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2") && sourceString != "" && sourceString != "exit") {
				extensions = chooseExtensions.getExcludedExtensions(sourceString);
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				subFolders = includeSubfolders.includeSubfolders();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				newFileType = chooseNewFileType.getFileExtension();
			} else if(actionChoice.toLowerCase().trim().equals("5") && sourceString != "" && sourceString != "exit"  && newFileType != "" && newFileType != "exit") {
				List<String> fileStrings = new ArrayList<>();
				try {
					fileStrings = findFiles.findFiles(Paths.get(sourceString), extensions, subFolders);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(fileStrings.size() > 0) {
					for(String file : fileStrings) {
						File f = new File(file);
						File newFile = changeExtension(f,newFileType);
						f.renameTo(newFile);
					}
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(subFolders == 3 || newFileType == "exit" || (extensions.length == 1 && extensions[0].equals("exit")) || sourceString == "exit") {
				run = false;
			}
		}
	}

	public static File changeExtension(File f, String newExtension) {
		  int i = f.getName().lastIndexOf('.');
		  String name = f.getName().substring(0,i);
		  return new File(f.getParent(), name + "." + newExtension);
	}

}
