package PdfTools.ActionChooseMenus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import Titles.ToolTitles.PdfTitles.SplitPdfActionTitle;
import Utilities.ExtractPageNumbersFromString;
import Utilities.UserInput.ChooseExcludedPages;
import Utilities.UserInput.ChooseFiles;
import Utilities.UserInput.Name;
import WindowsResources.SourceFolder;

public class SplitPdfAction {

	Scanner scanner = new Scanner(System.in);
	SplitPdfActionTitle splitPdfActionTitle = new SplitPdfActionTitle();
	SourceFolder sourceFolder = new SourceFolder();
	Name name = new Name();
	ChooseFiles chooseFiles = new ChooseFiles();
	PDFMergerUtility PDFmerger = new PDFMergerUtility();
	ChooseExcludedPages chooseExcludedPages = new ChooseExcludedPages();
	ExtractPageNumbersFromString extractPageNumbersFromString = new ExtractPageNumbersFromString();

	public void mergePdfFiles() throws IOException {

		String actionChoice;

		String originalPdf = "";
		String destinationFolder = "";
		String outputFileName = "splitResult";
		String excludedPagesString ="";
		int[] excludedPages = new int[0];

		boolean run = true;

		while(run) {
			splitPdfActionTitle.printTitle(originalPdf, destinationFolder, outputFileName, excludedPagesString);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				originalPdf = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				destinationFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				outputFileName = name.getName();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				excludedPagesString = chooseExcludedPages.getExcludedPages();
				int[] tempArray = extractPageNumbersFromString.getPageArray(excludedPagesString);
				if(tempArray.length > 0) {
					excludedPages = new int[tempArray.length];
					excludedPages = tempArray;
				}
			} else if(actionChoice.toLowerCase().trim().equals("5") && originalPdf != "" && originalPdf != "exit" && destinationFolder != "" && destinationFolder != "exit") {
				split(originalPdf, destinationFolder, outputFileName, excludedPages);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}

	public void split(String sourcePdf, String destinationFolder, String newName, int[] excludedPages) throws IOException {
		String divider = "\\";
		if(!System.getProperty("os.name").toLowerCase().contains("windows")) {
			divider = "/";
		}

		List<Integer> excludedPageList = new ArrayList<>();
		if(excludedPages.length > 0 && excludedPages != null) {
			for(int excludedPage : excludedPages) {
				if (!excludedPageList.contains(excludedPage)) {
					excludedPageList.add(excludedPage);
				}
			}
		}

	    File file = new File(sourcePdf);
	    PDDocument document = Loader.loadPDF(file);

	    Splitter splitter = new Splitter();

	    List<PDDocument> Pages = splitter.split(document);
	    Iterator<PDDocument> iterator = Pages.listIterator();

	    if(excludedPageList.size() > 0) {
		    int i = 1;
		    while(iterator.hasNext()) {
		    	PDDocument pd = iterator.next();
		    	if(excludedPageList.contains(i)) {
			        pd.save(destinationFolder + divider + newName + i + ".pdf");
		    	}
		        i++;
		    }
	    } else {
		    int i = 1;
		    while(iterator.hasNext()) {
		    	PDDocument pd = iterator.next();
			    pd.save(destinationFolder + divider + newName + i + ".pdf");
		        i++;
		    }
	    }

	    System.out.println("PDF " + sourcePdf + " split successfully");
	    document.close();
	}

}
