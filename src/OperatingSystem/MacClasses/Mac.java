package OperatingSystem.MacClasses;

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
import FIleSystemTools.ToolMenus.DeleteAllFilesInFolder;
import FIleSystemTools.ToolMenus.FolderFileCountAndSize;
import FIleSystemTools.ToolMenus.MassChangeFileType;
import FIleSystemTools.ToolMenus.MassCopyOrMoveFolderContents;
import FIleSystemTools.ToolMenus.MassRenameFilesInFolder;
import FIleSystemTools.ToolMenus.RemoveDuplicateFilesFromFolder;
import FIleSystemTools.ToolMenus.SortFilesToFoldersByExtension;
import FIleSystemTools.ToolMenus.UnzipAllZipFilesInFolder;
import MacResources.ExecuteMacScripts;
import MacResources.MacCommandLineScripts;
import PdfTools.ActionChooseMenus.ConvertPdfAction;
import PdfTools.ActionChooseMenus.EditPdfMetadata;
import PdfTools.ActionChooseMenus.ExtractPdfMetadata;
import PdfTools.ActionChooseMenus.ExtractTextFromPdfAction;
import PdfTools.ActionChooseMenus.MergePdfAction;
import PdfTools.ActionChooseMenus.SplitPdfAction;
import SystemInformation.EnvironmentVariables;
import SystemInformation.OperatingSystemInfo;

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
	private AllPathsInFolder allPathsInFolder = new AllPathsInFolder();
	private FolderFileCountAndSize folderFileCountAndSize = new FolderFileCountAndSize();
	private AESChooseAction aes = new AESChooseAction();
	private MD5ChooseAction md5 = new MD5ChooseAction();
	private PasswordChooseAction passwordAction = new PasswordChooseAction();
	private PGPChooseAction pgpChooseAction = new PGPChooseAction();
	private SHAChooseAction shaChooseAction = new SHAChooseAction();
	private OperatingSystemInfo operatingSystemInfo = new OperatingSystemInfo();
	private EnvironmentVariables environmentVariables = new EnvironmentVariables();
	private MergePdfAction mergePdfAction = new MergePdfAction();
	private SplitPdfAction splitPdfAction = new SplitPdfAction();
	private ExtractTextFromPdfAction extractTextFromPdfAction = new ExtractTextFromPdfAction();
	private ConvertPdfAction convertPdfAction = new ConvertPdfAction();
	private ExtractPdfMetadata extractPdfMetadata = new ExtractPdfMetadata();
	private EditPdfMetadata editPdfMetadata = new EditPdfMetadata();
	private DeleteAllFilesInFolder deleteAllFilesInFolder = new DeleteAllFilesInFolder();
	
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
			} else if(command == 10) {
				try {
					deleteAllFilesInFolder.getAllPathsInFolder();
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
			operatingSystemInfo.networkInfo();
		} else if(command == 3) {
			environmentVariables.getAllEnvironmentVariables();
		} else if(command == 4) {
			operatingSystemInfo.printHardwareInfo();
		} else if(command == 5) {
			operatingSystemInfo.javaInfo();
		}
	}

	public void executeGivenPdfCommand(int command) {
		if(command == 1) {
			try {
				mergePdfAction.mergePdfFiles();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(command == 2) {
			try {
				splitPdfAction.mergePdfFiles();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(command == 3) {
			try {
				extractTextFromPdfAction.mergePdfFiles();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(command == 4) {
			convertPdfAction.convertPdfFile();
		} else if(command == 5) {
			extractPdfMetadata.extractPdfMetadata();
		} else if(command == 6) {
			editPdfMetadata.editPdfMetadata();
		} else if(command == 7) {
			
		}
	}
}
