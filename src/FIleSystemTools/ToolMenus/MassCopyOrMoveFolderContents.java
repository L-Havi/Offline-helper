package FIleSystemTools.ToolMenus;

import java.util.Scanner;

import Titles.ToolTitles.FileSystemTitles.MassCopyOrMoveFolderContentsTitle;
import Utilities.UserInput.ChooseMoveOrCopy;
import Utilities.UserOutput.ListDirectoryFiles;
import WindowsResources.CopyFilesToAnotherFolder;
import WindowsResources.DestinationFolder;
import WindowsResources.MoveFilesToAnotherFolder;
import WindowsResources.SourceFolder;

public class MassCopyOrMoveFolderContents {

	private SourceFolder sourceFolder = new SourceFolder();
	private DestinationFolder destinationFolder = new DestinationFolder();
	private ListDirectoryFiles listDirectoryFiles = new ListDirectoryFiles();
	private MoveFilesToAnotherFolder moveFilesToAnotherFolder = new MoveFilesToAnotherFolder();
	private CopyFilesToAnotherFolder copyFilesToAnotherFolder = new CopyFilesToAnotherFolder();
	private ChooseMoveOrCopy chooseMoveOrCopy = new ChooseMoveOrCopy();
	private MassCopyOrMoveFolderContentsTitle massCopyOrMoveFolderContentsTitle = new MassCopyOrMoveFolderContentsTitle();

	public void copyOrMoveFiles() {
		boolean executed = true;
		String sourceString = "";
		String[] sourceExtensions = new String[0];
		String[][] destinationFiles = new String[0][0];
		String destinationString = "";
		String moveOrCopy = "";

		String actionChoice;

		Scanner scanner = new Scanner(System.in);

		while(executed) {
			massCopyOrMoveFolderContentsTitle.printTitle(sourceString, destinationString, moveOrCopy);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				sourceString = sourceFolder.getSourceFolder();
				sourceExtensions = listDirectoryFiles.getDirectoryFiles(sourceString);
			} else if(actionChoice.toLowerCase().trim().equals("2") && sourceString != "") {
				destinationString = destinationFolder.getDestinationFolder(sourceString);
				destinationFiles = listDirectoryFiles.getFolderPaths(sourceString, destinationString, sourceExtensions);
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				moveOrCopy = chooseMoveOrCopy.getMoveOrCopy();
			} else if(actionChoice.toLowerCase().trim().equals("4") && moveOrCopy != "" && sourceString != "" && destinationString != "") {
				if(moveOrCopy.toLowerCase().trim().equals("move")) {
					moveFilesToAnotherFolder.move(destinationFiles);
				} else {
					copyFilesToAnotherFolder.copy(destinationFiles);
				}
				executed = false;
			}else if(actionChoice.toLowerCase().trim().equals("5")) {
				executed = false;
			}else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(sourceString == "exit" || destinationString == "exit" || moveOrCopy == "exit") {
				executed = false;
			}

		}

	}

}
