package OperatingSystem;

import java.io.IOException;

import FIleSystemTools.MassChangeFileType;
import FIleSystemTools.MassCopyOrMoveFolderContents;
import FIleSystemTools.MassRenameFilesInFolder;
import FIleSystemTools.RemoveDuplicateFilesFromFolder;
import FIleSystemTools.SortFilesToFoldersByExtension;
import FIleSystemTools.UnzipAllZipFilesInFolder;
import FIleSystemTools.WindowsExecuteCmd;
import MacResources.ExecuteMacScripts;
import MacResources.MacCommandLineScripts;
import WindowsResources.CmdCommandScripts;

public class Mac {

	private String commandText;
	private MacCommandLineScripts macCommandLineScripts = new MacCommandLineScripts();
	private ExecuteMacScripts executeMacScripts = new ExecuteMacScripts();
	private RemoveDuplicateFilesFromFolder removeDuplicateFilesFromFolder = new RemoveDuplicateFilesFromFolder();
	private SortFilesToFoldersByExtension sortFilesToFoldersByExtension = new SortFilesToFoldersByExtension();
	private MassCopyOrMoveFolderContents massCopyOrMoveFolderContents = new MassCopyOrMoveFolderContents();
	private MassRenameFilesInFolder massRenameFilesInFolder = new MassRenameFilesInFolder();
	private UnzipAllZipFilesInFolder unzipAllZipFilesInFolder = new UnzipAllZipFilesInFolder();
	private MassChangeFileType massChangeFileType = new MassChangeFileType();
	
	public void executeGivenCommand(int command) {
		commandText = macCommandLineScripts.getCmdCommandScript(command);
		if(commandText != null) {
			//Command Line Processes
			executeMacScripts.executeCommand(commandText);
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
			}
		}
	}

}
