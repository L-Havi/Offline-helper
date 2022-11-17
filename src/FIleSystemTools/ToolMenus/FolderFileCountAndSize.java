package FIleSystemTools.ToolMenus;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Titles.ToolTitles.FileSystemTitles.FolderFileCountAndSizeTitle;
import Utilities.FileCountAndSize;
import Utilities.UserInput.ChooseExtensions;
import Utilities.UserInput.ChooseSaveMethod;
import Utilities.UserOutput.FindFiles;
import Utilities.UserOutput.OutputPaths;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class FolderFileCountAndSize {

	SourceFolder sourceFolder = new SourceFolder();
	ChooseExtensions chooseExtensions = new ChooseExtensions();
	IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	FindFiles findFiles = new FindFiles();
	ChooseSaveMethod chooseSaveMethod = new ChooseSaveMethod();
	OutputPaths outputPaths = new OutputPaths();
	FileCountAndSize fileCountAndSize = new FileCountAndSize();
	FolderFileCountAndSizeTitle folderFileCountAndSizeTitle = new FolderFileCountAndSizeTitle();
	Scanner scanner = new Scanner(System.in);

	public void getAllPathsInFolder() throws IOException {


		String srcFolder = "";
		String[] extensions = {"*"};
		int subfolders = 0;
		int saveToTextFile = 0;
		String actionChoice;

		boolean run = true;

		while(run) {
			folderFileCountAndSizeTitle.printTitle(srcFolder, extensions, subfolders, saveToTextFile);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				srcFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2") && srcFolder != "" && srcFolder != "exit") {
				extensions = chooseExtensions.getExcludedExtensions(srcFolder);
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				subfolders = includeSubfolders.includeSubfolders();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				saveToTextFile = chooseSaveMethod.getSaveMethod();
			} else if(actionChoice.toLowerCase().trim().equals("5") && srcFolder != "" && srcFolder != "exit") {
				List<String> fileStrings = new ArrayList<>();
				boolean save = false;
				if(saveToTextFile == 1) {
					save = true;
				}
				try {
					fileStrings = findFiles.findFiles(Paths.get(srcFolder), extensions, subfolders);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(fileStrings.size() > 0) {
					fileCountAndSize.getFileCountAndSize(fileStrings, save, srcFolder,srcFolder,"");
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(subfolders == 3 || saveToTextFile == 3 || (extensions.length == 1 && extensions[0].equals("exit")) || srcFolder == "exit") {
				run = false;
			}
		}
	}

}
