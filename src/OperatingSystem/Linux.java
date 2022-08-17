package OperatingSystem;

import java.io.IOException;

import FIleSystemTools.AllPathsInFolder;
import FIleSystemTools.FolderFileCountAndSize;
import FIleSystemTools.MassChangeFileType;
import FIleSystemTools.MassCopyOrMoveFolderContents;
import FIleSystemTools.MassRenameFilesInFolder;
import FIleSystemTools.RemoveDuplicateFilesFromFolder;
import FIleSystemTools.SortFilesToFoldersByExtension;
import FIleSystemTools.UnzipAllZipFilesInFolder;
import LinuxResources.ExecuteLinuxScripts;
import LinuxResources.LinuxCommandLineScripts;

public class Linux {

	private String commandText;
	private LinuxCommandLineScripts linuxCommandLineScripts = new LinuxCommandLineScripts();
	private ExecuteLinuxScripts executeLinuxScripts = new ExecuteLinuxScripts();
	private RemoveDuplicateFilesFromFolder removeDuplicateFilesFromFolder = new RemoveDuplicateFilesFromFolder();
	private SortFilesToFoldersByExtension sortFilesToFoldersByExtension = new SortFilesToFoldersByExtension();
	private MassCopyOrMoveFolderContents massCopyOrMoveFolderContents = new MassCopyOrMoveFolderContents();
	private MassRenameFilesInFolder massRenameFilesInFolder = new MassRenameFilesInFolder();
	private UnzipAllZipFilesInFolder unzipAllZipFilesInFolder = new UnzipAllZipFilesInFolder();
	private MassChangeFileType massChangeFileType = new MassChangeFileType();
	private AllPathsInFolder allPathsInFolder = new AllPathsInFolder();
	private FolderFileCountAndSize folderFileCountAndSize = new FolderFileCountAndSize();
	
	public void executeGivenCommand(int command) {
		commandText = linuxCommandLineScripts.getCmdCommandScript(command);
		if(commandText != null) {
			//Command Line Processes
			executeLinuxScripts.executeCommand(commandText);
		} else {
			//Not Command Line Process
			if(command == 2) {
				removeDuplicateFilesFromFolder.removeDuplicates();
			}else if(command == 3) {
				sortFilesToFoldersByExtension.sortFiles();
			} else if(command == 4) {
				massCopyOrMoveFolderContents.copyOrMoveFiles();
			}else if(command == 5) {
				massRenameFilesInFolder.massRenameFiles();
			} else if(command == 6) {
				try {
					unzipAllZipFilesInFolder.choose();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(command == 7) {
				massChangeFileType.changeAllFileExtensions();
			} else if(command == 8) {
				try {
					allPathsInFolder.getAllPathsInFolder();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(command == 9) {
				try {
					folderFileCountAndSize.getAllPathsInFolder();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
