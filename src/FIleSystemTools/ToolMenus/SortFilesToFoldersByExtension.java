package FIleSystemTools.ToolMenus;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Titles.ToolTitles.FileSystemTitles.SortFilesToFoldersByExtensionTitle;
import Utilities.CreateFolder;
import Utilities.FileExtension;
import Utilities.UserInput.ChooseExtensions;
import Utilities.UserOutput.FindFiles;
import WindowsResources.CreateFolderPaths;
import WindowsResources.MoveToFileExtensionFolder;
import WindowsResources.SourceFolder;


public class SortFilesToFoldersByExtension {

	private SortFilesToFoldersByExtensionTitle sortFilesToFoldersByExtensionTitle = new SortFilesToFoldersByExtensionTitle();
	private ChooseExtensions chooseExtensions = new ChooseExtensions();
	private MoveToFileExtensionFolder moveToFileExtensionFolder = new MoveToFileExtensionFolder();

	private SourceFolder sourceFolder = new SourceFolder();
	private FileExtension fileExtension = new FileExtension();
	private CreateFolderPaths createFolderPaths = new CreateFolderPaths();
	private CreateFolder createFolder = new CreateFolder();
	private FindFiles findFiles = new FindFiles();

	public void sortFiles() {

		String actionChoice;

		String sourceString = "";
		String[] extensionArray = {"*"};
		List <String> extensions = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);

		boolean run = true;

		while(run) {
			sortFilesToFoldersByExtensionTitle.printTitle(sourceString, extensionArray);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				sourceString = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2") && sourceString != "" && sourceString != "exit") {
				extensions = fileExtension.getUniqueFileExtensions(sourceString);
				if(extensions.size() > 0) {
					extensionArray = extensions.toArray(extensionArray);
				}
			} else if(actionChoice.toLowerCase().trim().equals("3") && sourceString != "" && sourceString != "exit") {
				if(extensionArray.length == 1 && extensionArray[0].equals("*")) {
					extensions = fileExtension.getUniqueFileExtensions(sourceString);
				}
				List<String> path = new ArrayList<>();
				for(String extension : extensions) {
					String newPath = sourceString + "\\" + extension.toUpperCase();
					path.add(newPath);
				}
				createFolder.createFolders(path);
				for(String extension : extensions) {
					try {
						String[][] files = findFiles.findFilesByExtension(Paths.get(sourceString), extension);
				        moveToFileExtensionFolder.move(files);
				    } catch (IOException e) {
				    	e.printStackTrace();
				    }
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if((extensionArray.length == 1 && extensionArray[0].equals("exit")) || sourceString == "exit") {
				run = false;
			}
		}
	}
}
