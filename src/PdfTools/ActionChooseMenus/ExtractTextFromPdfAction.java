package PdfTools.ActionChooseMenus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import Titles.ToolTitles.PdfTitles.ExtractTextFromPdfActionTitle;
import Titles.ToolTitles.PdfTitles.SplitPdfActionTitle;
import Utilities.ExtractPageNumbersFromString;
import Utilities.UserInput.ChooseExcludedPages;
import Utilities.UserInput.ChooseFiles;
import Utilities.UserInput.Name;
import WindowsResources.SourceFolder;

public class ExtractTextFromPdfAction {

	Scanner scanner = new Scanner(System.in);
	SourceFolder sourceFolder = new SourceFolder();
	Name name = new Name();
	ExtractTextFromPdfActionTitle extractTextFromPdfActionTitle = new ExtractTextFromPdfActionTitle();
	ChooseFiles chooseFiles = new ChooseFiles();
	PDFMergerUtility PDFmerger = new PDFMergerUtility();
	ChooseExcludedPages chooseExcludedPages = new ChooseExcludedPages();
	ExtractPageNumbersFromString extractPageNumbersFromString = new ExtractPageNumbersFromString();
	
	public void mergePdfFiles() throws IOException {
		
		String actionChoice;
		
		String originalPdf = "";
		String destinationFolder = "";
		String outputFileName = "textExtractResult";
		String excludedPagesString ="";
		int[] excludedPages = new int[0];
		
		String divider = "\\";
		if(!System.getProperty("os.name").toLowerCase().contains("windows")) {
			divider = "/";
		}
		
		boolean run = true;
		
		while(run) {
			extractTextFromPdfActionTitle.printTitle(originalPdf, destinationFolder, outputFileName, excludedPagesString);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				originalPdf = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				destinationFolder = sourceFolder.getSourceFolder();
			}  else if(actionChoice.toLowerCase().trim().equals("3")) {
				outputFileName = name.getName();
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				excludedPagesString = chooseExcludedPages.getExcludedPages();
				int[] tempArray = extractPageNumbersFromString.getPageArray(excludedPagesString);
				if(tempArray.length > 0) {
					excludedPages = new int[tempArray.length];
					excludedPages = tempArray;
				}
			} else if(actionChoice.toLowerCase().trim().equals("5") && originalPdf != "" && originalPdf != "exit" && destinationFolder != "" && destinationFolder != "exit") {
				String outputFile = destinationFolder + divider + outputFileName + ".txt";
				extract(originalPdf, outputFile, excludedPages);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("6")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}
	
	public void extract(String sourcePdf, String outputFile, int[] excludedPages) throws IOException {
		
	      File file = new File(sourcePdf);
	      PDDocument document = Loader.loadPDF(file); 

	      PDFTextStripper pdfStripper = new PDFTextStripper();
	      
	      if(excludedPages.length > 0 && excludedPages != null) {
	    	  int first = excludedPages[0];
	    	  int last = excludedPages[excludedPages.length-1];
	    	  pdfStripper.setStartPage(first);
	    	  pdfStripper.setEndPage(last);
	      }
	      
	      
	      String text = pdfStripper.getText(document);

	      document.close();
		
	      
	      try {
	          File myObj = new File(outputFile);
	          myObj.createNewFile();
	          
	          FileWriter myWriter = new FileWriter(outputFile);
	          myWriter.write(text);
	          myWriter.close();
	      } catch (IOException e) {
	          System.out.println("An error occurred.");
	          e.printStackTrace();
	      }
	}
	
}
