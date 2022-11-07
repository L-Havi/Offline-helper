package PdfTools.ActionChooseMenus;

import java.util.Scanner;

import com.aspose.pdf.Document;
import com.aspose.pdf.DocumentInfo;

import Titles.ToolTitles.PdfTitles.ExtractPdfMetadataTitle;
import WindowsResources.SourceFolder;

public class ExtractPdfMetadata {

	Scanner scanner = new Scanner(System.in);
	SourceFolder sourceFolder = new SourceFolder();
	ExtractPdfMetadataTitle extractPdfMetadataTitle = new ExtractPdfMetadataTitle();

	public void extractPdfMetadata() {

		String actionChoice;
		String originalPdf = "";

		boolean run = true;

		while(run) {
			extractPdfMetadataTitle.printTitle(originalPdf);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				originalPdf = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2") && originalPdf != "" && originalPdf != "exit") {
				getPDFFileInformation(originalPdf);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}


    public void getPDFFileInformation(String targetPdf) {
        // Create a new PDF document
        Document pdfDocument = new Document(targetPdf);
        // Get document information
        DocumentInfo docInfo = pdfDocument.getInfo();
        // Show document information
        System.out.println(targetPdf + " metadata");
		System.out.println("------------------------------------------------------------------------");
        System.out.println("Author: " + docInfo.getAuthor());
        System.out.println("Creation Date: " + docInfo.getCreationDate());
        System.out.println("Keywords: " + docInfo.getKeywords());
        System.out.println("Modify Date: " + docInfo.getModDate());
        System.out.println("Subject: " + docInfo.getSubject());
        System.out.println("Title: " + docInfo.getTitle());

        pdfDocument.close();
    }

}
