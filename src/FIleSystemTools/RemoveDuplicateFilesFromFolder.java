package FIleSystemTools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Utilities.MapSubFolders;
import WindowsResources.IncludeSubfolders;
import WindowsResources.RemoveDuplicateFiles;

public class RemoveDuplicateFilesFromFolder {
	
	public void removeDuplicates() {
		
		Scanner scanner = new Scanner(System.in);
		boolean executed = true;
		boolean foundDuplicatesToBeDeleted = false;
		IncludeSubfolders includeSubfolders = new IncludeSubfolders();
		MapSubFolders mapSubFolders = new MapSubFolders();
		
		while(executed) {
			try {
				System.out.println("Please supply a directory path or exit by typing \"exit\"");
				String path = scanner.nextLine();
				boolean isExit = path.equals("exit");
		        if (path != null && !isExit) {
		        	int useSubfolders = includeSubfolders.includeSubfolders();
		        	if(useSubfolders != 3) {
			        	if(useSubfolders == 1) {
			        		File originalDir = new File(path);
			        		List<File> subfolders = mapSubFolders.findAllSubdirs(originalDir);
			        		for(File dir : subfolders) {
						        Map<String, List<String>> lists = new HashMap<String, List<String>>();
						        RemoveDuplicateFiles.removeDuplicateFiles(lists, dir);
						        System.out.println("\nDeleted Duplicate Files:\n");
						        for (List<String> list : lists.values()) {
						            if (list.size() > 1) {
						                for (String file : list) {
						                    if (file != list.get(0)) {
						                        File duplicate = new File(file); 
						                        if (duplicate.delete()) { 
						                          System.out.println("Deleted the file: " + duplicate.getName() + " (Duplicate to file: " + list.get(0) + ")");
						                          foundDuplicatesToBeDeleted = true;
						                        } else {
						                          System.out.println("Failed to delete the file: " + duplicate.getName() + " (Duplicate to file: " + list.get(0) + ")");
						                        } 
						                    }
						                }
						            }
						        }
						        if(!foundDuplicatesToBeDeleted) {
						        	System.out.println("\nDuplicate files were not found in folder path: " + path + "\n");
						        }else {
						        	System.out.println("\nRemoved all duplicate files in folder path: " + path + "\n");
						        }
						        executed = false;
			        		}
			        	}
				        	File dir = new File(path);
				        	try {
								System.out.println(dir.getCanonicalPath());
							} catch (IOException e) {
							}
					        if (!dir.isDirectory()) {
					            System.out.println("Supplied directory does not exist.");
					        } else {
						        Map<String, List<String>> lists = new HashMap<String, List<String>>();
						        RemoveDuplicateFiles.removeDuplicateFiles(lists, dir);
						        System.out.println("\nDeleted Duplicate Files:\n");
						        for (List<String> list : lists.values()) {
						            if (list.size() > 1) {
						                for (String file : list) {
						                    if (file != list.get(0)) {
						                        File duplicate = new File(file); 
						                        if (duplicate.delete()) { 
						                          System.out.println("Deleted the file: " + duplicate.getName() + " (Duplicate to file: " + list.get(0) + ")");
						                          foundDuplicatesToBeDeleted = true;
						                        } else {
						                          System.out.println("Failed to delete the file: " + duplicate.getName() + " (Duplicate to file: " + list.get(0) + ")");
						                        } 
						                    }
						                }
						            }
						        }
						        if(!foundDuplicatesToBeDeleted) {
						        	System.out.println("\nDuplicate files were not found in folder path: " + path + "\n");
						        }else {
						        	System.out.println("\nRemoved all duplicate files in folder path: " + path + "\n");
						        }
						        executed = false;
					        }
		        	} else {
		        		executed = false;
		        	}
		        }
		        if(isExit){
		        	executed = false;
		        }
			} catch(InputMismatchException e) {
        		scanner.nextLine();
        		System.out.println("Please give valid input (e.g. C:\\Temp)");
        	}
		}
	}
	
}
