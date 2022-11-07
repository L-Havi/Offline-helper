package FIleSystemTools.ToolMenus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import Titles.ToolTitles.FileSystemTitles.DeleteAllFilesInFolderTitle;
import Utilities.UserInput.ChooseExtensions;
import Utilities.UserInput.ChooseSaveMethod;
import Utilities.UserInput.ConfirmDelete;
import Utilities.UserOutput.FindFiles;
import Utilities.UserOutput.OutputPaths;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class DeleteAllFilesInFolder {


	SourceFolder sourceFolder = new SourceFolder();
	ChooseExtensions chooseExtensions = new ChooseExtensions();
	IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	FindFiles findFiles = new FindFiles();
	ChooseSaveMethod chooseSaveMethod = new ChooseSaveMethod();
	OutputPaths outputPaths = new OutputPaths();
	DeleteAllFilesInFolderTitle deleteAllFilesInFolderTitle = new DeleteAllFilesInFolderTitle();
	Scanner scanner = new Scanner(System.in);
	ConfirmDelete confirmDelete = new ConfirmDelete();

	public void getAllPathsInFolder() throws IOException {

		String srcFolder = "";
		String[] extensions = {"*"};
		int subfolders = 0;
		int saveToTextFile = 0;
		String actionChoice;

		boolean run = true;

		while(run) {
			deleteAllFilesInFolderTitle.printTitle(srcFolder, extensions, subfolders);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				srcFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2") && srcFolder != "" && srcFolder != "exit") {
				extensions = chooseExtensions.getExcludedExtensions(srcFolder);
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				subfolders = includeSubfolders.includeSubfolders();
			} else if(actionChoice.toLowerCase().trim().equals("5") && srcFolder != "" && srcFolder != "exit") {
				List<String> fileStrings = new ArrayList<>();
				try {
					System.out.println("Files to be Deleted from Folder " + srcFolder);
					System.out.println("------------------------------------------------------------------------");
					fileStrings = findFiles.findFiles(Paths.get(srcFolder), extensions, subfolders);
					System.out.println("------------------------------------------------------------------------");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(fileStrings.size() > 0) {
					boolean confirmDeleteOption = confirmDelete.getDeleteConfirmation();
					if(confirmDeleteOption) {
						delete(srcFolder, fileStrings, subfolders);
					}
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(subfolders == 3 || (extensions.length == 1 && extensions[0].equals("exit")) || srcFolder == "exit") {
				run = false;
			}
		}
	}

	private void delete(String srcFolder, List<String> fileStrings, int subfolders) {
		for(String filePath : fileStrings) {
			File deletedFile = new File(filePath);
			deletedFile.delete();
		}
		if(subfolders == 1) {
			File[] directories = new File(srcFolder).listFiles(File::isDirectory);
			for(File directory : directories) {
				try {
					FileUtils.deleteDirectory(directory);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
