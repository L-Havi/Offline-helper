package PdfTools.ActionChooseMenus;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import Titles.ToolTitles.PdfTitles.MergePdfActionTitle;
import Utilities.UserInput.ChooseFiles;
import Utilities.UserInput.Name;
import WindowsResources.SourceFolder;

public class MergePdfAction {

	Scanner scanner = new Scanner(System.in);
	MergePdfActionTitle mergePdfActionTitle = new MergePdfActionTitle();
	SourceFolder sourceFolder = new SourceFolder();
	Name name = new Name();
	ChooseFiles chooseFiles = new ChooseFiles();
	PDFMergerUtility PDFmerger = new PDFMergerUtility();

	public void mergePdfFiles() throws IOException {

		String actionChoice;

		String outputFolder = "";
		String[] allMergeFiles = new String[0];
		String outputFileName = "mergeOutput";

		boolean run = true;

		while(run) {
			mergePdfActionTitle.printTitle(outputFolder, allMergeFiles, outputFileName);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				outputFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				String[] tempString = chooseFiles.getPdfFiles();
				allMergeFiles = new String[tempString.length];
				allMergeFiles = tempString;
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				outputFileName = name.getName();
			} else if(actionChoice.toLowerCase().trim().equals("4") && outputFolder != "" && allMergeFiles.length >= 2) {
				String destinationPath = outputFolder + "\\" + outputFileName + ".pdf";
				merge(destinationPath, allMergeFiles);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}

	public void merge(String destinationPath, String[] allMergeFiles) throws IOException {
		PDFmerger.setDestinationFileName(destinationPath);

		for(String mergeFile : allMergeFiles) {
			File file = new File(mergeFile);
			PDFmerger.addSource(file);

		}
		PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
	}

}
