package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WindowsResources.RemoveAllDuplicateFiles;

public class RemoveDuplicateFiles {

	MapSubFolders mapSubFolders = new MapSubFolders();
	RemoveAllDuplicateFiles removeAllDuplicateFiles = new RemoveAllDuplicateFiles();

	public void removeDuplicates(String sourceString, int usesubfolders) {

		boolean foundDuplicatesToBeDeleted = false;

    	if(usesubfolders == 1) {
    		File originalDir = new File(sourceString);
    		List<File> subfolders = mapSubFolders.findAllSubdirs(originalDir);
    		for(File dir : subfolders) {
		        Map<String, List<String>> lists = new HashMap<>();
		        RemoveAllDuplicateFiles.removeAllDuplicateFiles(lists, dir);
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
		        	System.out.println("\nDuplicate files were not found in folder path: " + sourceString + "\n");
		        }else {
		        	System.out.println("\nRemoved all duplicate files in folder path: " + sourceString + "\n");
		        }
    		}
    	}
        	File dir = new File(sourceString);
        	try {
				System.out.println(dir.getCanonicalPath());
			} catch (IOException e) {
			}
	        if (!dir.isDirectory()) {
	            System.out.println("Supplied directory does not exist.");
	        } else {
		        Map<String, List<String>> lists = new HashMap<>();
		        RemoveAllDuplicateFiles.removeAllDuplicateFiles(lists, dir);
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
		        	System.out.println("\nDuplicate files were not found in folder path: " + sourceString + "\n");
		        }else {
		        	System.out.println("\nRemoved all duplicate files in folder path: " + sourceString + "\n");
		        }
	        }
	}
}
