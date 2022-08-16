package FIleSystemTools;

import java.util.Scanner;

import Utilities.ListDirectoryFiles;
import WindowsResources.CopyFilesToAnotherFolder;
import WindowsResources.DestinationFolder;
import WindowsResources.IncludeSubfolders;
import WindowsResources.MoveFilesToAnotherFolder;
import WindowsResources.SourceFolder;

public class MassCopyOrMoveFolderContents {
	
	private SourceFolder sourceFolder = new SourceFolder();
	private DestinationFolder destinationFolder = new DestinationFolder();
	private ListDirectoryFiles listDirectoryFiles = new ListDirectoryFiles();
	private MoveFilesToAnotherFolder moveFilesToAnotherFolder = new MoveFilesToAnotherFolder();
	private CopyFilesToAnotherFolder copyFilesToAnotherFolder = new CopyFilesToAnotherFolder();
	
	public void copyOrMoveFiles() {
		boolean executed = true;
		boolean sourceSetCorrectly = false;
		boolean destinationSetCorrectly = false;
		String sourceString = "";
		String destinationString = "";
		
		Scanner scanner = new Scanner(System.in);
		
		while(executed) {
			if (!sourceSetCorrectly) {
				sourceString = sourceFolder.getSourceFolder();
			}
			if(!sourceString.equals("exit")) {
				String[] sourceExtensions = listDirectoryFiles.getDirectoryFiles(sourceString);
		        if(!destinationSetCorrectly) {
				    destinationString = destinationFolder.getDestinationFolder(sourceString);
		        }
		        if(!destinationString.equals("exit")) {
			        String[][] destinationFiles = listDirectoryFiles.getFolderPaths(sourceString, destinationString, sourceExtensions);
			        System.out.println("Do you wish to move or copy files?(move/copy/exit)");
			        String moveOrCopy = scanner.nextLine();
			        if(moveOrCopy.toLowerCase().trim().equals("move")) {
			        	moveFilesToAnotherFolder.move(destinationFiles);
			        	executed = false;
						sourceSetCorrectly = false;
						destinationSetCorrectly = false;
						sourceString = "";
						destinationString = "";
			        } else if(moveOrCopy.toLowerCase().trim().equals("copy")){
			        	copyFilesToAnotherFolder.copy(destinationFiles);
						sourceSetCorrectly = false;
						destinationSetCorrectly = false;
						sourceString = "";
						destinationString = "";
			        	executed = false;
			        } else if(moveOrCopy.toLowerCase().trim().equals("exit")){
						sourceSetCorrectly = false;
						destinationSetCorrectly = false;
						sourceString = "";
						destinationString = "";
			        	executed = false;
			        }else {
			        	System.out.println("Please give correct input (Type move, copy or exit)");
			        }
		        }else {
		        	executed = false;
		        }
			} else {
				executed = false;
			}
		}
		
	}
	
}
