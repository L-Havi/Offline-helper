package OperatingSystem.WindowsClasses;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Cryptography.ActionChooseMenus.AESChooseAction;
import Cryptography.ActionChooseMenus.MD5ChooseAction;
import Cryptography.ActionChooseMenus.PGPChooseAction;
import Cryptography.ActionChooseMenus.PasswordChooseAction;
import Cryptography.ActionChooseMenus.SHAChooseAction;
import FIleSystemTools.ToolMenus.AllPathsInFolder;
import FIleSystemTools.ToolMenus.FolderFileCountAndSize;
import FIleSystemTools.ToolMenus.MassChangeFileType;
import FIleSystemTools.ToolMenus.MassCopyOrMoveFolderContents;
import FIleSystemTools.ToolMenus.MassRenameFilesInFolder;
import FIleSystemTools.ToolMenus.RemoveDuplicateFilesFromFolder;
import FIleSystemTools.ToolMenus.SortFilesToFoldersByExtension;
import FIleSystemTools.ToolMenus.UnzipAllZipFilesInFolder;
import FIleSystemTools.ToolMenus.WindowsExecuteCmd;
import SystemInformation.OperatingSystemInfo;
import WindowsResources.CmdCommandScripts;

public class Windows {
	
	private String commandText;
	private CmdCommandScripts cmdScripts = new CmdCommandScripts();
	private WindowsExecuteCmd windowsExecuteCmd = new WindowsExecuteCmd();
	private RemoveDuplicateFilesFromFolder removeDuplicateFilesFromFolder = new RemoveDuplicateFilesFromFolder();
	private SortFilesToFoldersByExtension sortFilesToFoldersByExtension = new SortFilesToFoldersByExtension();
	private MassCopyOrMoveFolderContents massCopyOrMoveFolderContents = new MassCopyOrMoveFolderContents();
	private MassRenameFilesInFolder massRenameFilesInFolder = new MassRenameFilesInFolder();
	private UnzipAllZipFilesInFolder unzipAllZipFilesInFolder = new UnzipAllZipFilesInFolder();
	private MassChangeFileType massChangeFileType = new MassChangeFileType();
	private AllPathsInFolder allPathsInFolder = new AllPathsInFolder();
	private FolderFileCountAndSize folderFileCountAndSize = new FolderFileCountAndSize();
	private AESChooseAction aes = new AESChooseAction();
	private MD5ChooseAction md5 = new MD5ChooseAction();
	private PasswordChooseAction passwordAction = new PasswordChooseAction();
	private PGPChooseAction pgpChooseAction = new PGPChooseAction();
	private SHAChooseAction shaChooseAction = new SHAChooseAction();
	private OperatingSystemInfo operatingSystemInfo = new OperatingSystemInfo();
	
	public void executeGivenCommand(int command) {
		commandText = cmdScripts.getCmdCommandScript(command);
		if(commandText != null) {
			//CMD Processes
			windowsExecuteCmd.executeCmd(commandText);
		} else {
			//Not CMD Process
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
	
	public void executeGivenEncryptionCommand(int command) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
			if(command == 1) {
				aes.encryptOrDecrypt();
			} else if(command == 2) {
				md5.md5Choose();
			} else if(command == 3) {
				passwordAction.passwordChoose();
			} else if(command == 4) {
				try {
					pgpChooseAction.pgpChoose();
				} catch (Exception e) {
				}
			} else if(command == 5) {
				shaChooseAction.shaChoose();
			}
	}

	public void executeGivenSystemInfoCommand(int command) {
		if(command == 1) {
			operatingSystemInfo.printOsInfo();
		} else if(command == 2) {
			operatingSystemInfo.javaInfo();
		}
	}
	
}
