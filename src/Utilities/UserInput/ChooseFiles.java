package Utilities.UserInput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Utilities.UserOutput.FindFiles;
import WindowsResources.IncludeSubfolders;

public class ChooseFiles {

	private FindFiles findFiles = new FindFiles();
	private IncludeSubfolders includeSubfolders = new IncludeSubfolders();

	public String[] getFiles(String srcFolder, String method) {
		Scanner scanner = new Scanner(System.in);
		boolean isValidinput = false;
		String sourceString;
		String[] finalFilePaths = new String[0];

			while(!isValidinput) {
				System.out.println("Input all files you want to included separated with a comma (e.g. file1.txt,file2.txt)\nIf you want to include all files in folders input \"*\"\nIf file doesn't exist in file it is skipped");
				sourceString = scanner.nextLine();
				List<String> realFiles = new ArrayList<>();
				int subfolders = 0;
				if(sourceString.trim().equals("*")) {
					if(method.equals("unzip")) {
						subfolders = includeSubfolders.includeSubfolders();
						String [] extensions = {"zip"};
						try {
							realFiles = findFiles.findFiles(Paths.get(srcFolder), extensions, subfolders);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if(method.equals("zip")) {
						realFiles.add(srcFolder);
					}
				}else {
					List<String> files = Arrays.asList(sourceString.split(","));
					for(String file : files) {
						String testPath = srcFolder + "\\" + file;
						File tempFile = new File(testPath);
						if(tempFile.exists() && !realFiles.contains(testPath)) {
							realFiles.add(testPath);
						}
					}
				}
		        if (!(realFiles.size() > 0)) {
		            System.out.println("No inputted files exist in folder. Please input files again.");
		        } else {
		        	finalFilePaths = new String[realFiles.size()];
		        	realFiles.toArray(finalFilePaths);
		        	isValidinput = true;
		        }
			}
			return finalFilePaths;
	}

	public String[] getPdfFiles() {
		Scanner scanner = new Scanner(System.in);
		boolean isValidinput = false;
		String sourceString;
		String[] finalFilePaths = new String[0];

			while(!isValidinput) {
				System.out.println("Input all files you want to included separated with a comma (e.g. C:\\folder\\file1.pdf,C:\\folder\\file2.pdf)\nIf file doesn't exist in file it is skipped");
				sourceString = scanner.nextLine();
				List<String> realFiles = new ArrayList<>();
				List<String> files = Arrays.asList(sourceString.split(","));
				for(String file : files) {
					File tempFile = new File(file);
					if(tempFile.exists() && !realFiles.contains(file)) {
						realFiles.add(file);
					}
					if (!(realFiles.size() > 0)) {
						System.out.println("No inputted files exist in folder. Please input files again.");
					} else {
						finalFilePaths = new String[realFiles.size()];
						realFiles.toArray(finalFilePaths);
						isValidinput = true;
					}
				}
			}

		return finalFilePaths;
	}

}
