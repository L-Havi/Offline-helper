package FIleSystemTools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utilities.ChooseExtensions;
import Utilities.ExtensionList;
import Utilities.FindFiles;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class MassChangeFileType {

	private SourceFolder sourceFolder = new SourceFolder();
	private ChooseExtensions chooseExtensions = new ChooseExtensions();
	private IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	private FindFiles findFiles = new FindFiles();
	private Scanner scanner = new Scanner(System.in);
	private ExtensionList extensionList = new ExtensionList();
	private boolean newFileTypeChosen;
	
	
	public void changeAllFileExtensions() {
		List<String> files = new ArrayList<String>();
		System.out.println("------------------------------------------------------------------------");
		System.out.println("NOTE! Changing file extension might have unexpected consequences!\nPlease use this feature only if you know what you are doing");
		System.out.println("------------------------------------------------------------------------");
		String sourceString = sourceFolder.getSourceFolder();
		if(!sourceString.equals("exit")) {
			String[] extensions = chooseExtensions.getExcludedExtensions(sourceString);
			if(!(extensions.length == 1 && extensions[0].equals("exit"))) {
				int subFolders = includeSubfolders.includeSubfolders();
				if(subFolders != 3) {
					try {
						files = findFiles.findFiles(Paths.get(sourceString), extensions, subFolders);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(files.size() > 0) {
						String newFileType = "";
						List<String> allExtension = extensionList.getAllExtensions();
						while(!newFileTypeChosen) {
							System.out.println("Input new filetype for file without leading comma(e.g. txt)");
							newFileType = scanner.nextLine();
							if(allExtension.contains(newFileType.trim()) || newFileType.trim().equals("exit")) {
								newFileTypeChosen = true;
							} else {
								System.out.println("Extension was not recognized. Please try again");
							}
						}
						if(!newFileType.equals("exit")) {
							for(String file : files) {
								File f = new File(file);
								File newFile = changeExtension(f,newFileType);
								f.renameTo(newFile);
							}
						}
					} else {
						System.out.println("Failed to find requested files. Please check input & try again");
					}
				}
			}
		}
	}
	
	public static File changeExtension(File f, String newExtension) {
		  int i = f.getName().lastIndexOf('.');
		  String name = f.getName().substring(0,i);
		  return new File(f.getParent(), name + "." + newExtension);
	}
	
}
