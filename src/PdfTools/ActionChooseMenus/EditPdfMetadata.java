package PdfTools.ActionChooseMenus;

import java.util.Scanner;

import com.aspose.pdf.Document;
import com.aspose.pdf.DocumentInfo;

import Titles.ToolTitles.PdfTitles.EditPdfMetadataTitle;
import Utilities.UserInput.ChooseDateInput;
import Utilities.UserInput.ChooseStringInput;
import WindowsResources.SourceFolder;

public class EditPdfMetadata {

	Scanner scanner = new Scanner(System.in);
	SourceFolder sourceFolder = new SourceFolder();
	EditPdfMetadataTitle editPdfMetadataTitle = new EditPdfMetadataTitle();
	ChooseStringInput chooseStringInput = new ChooseStringInput();
	ChooseDateInput chooseDateInput = new ChooseDateInput();

	public void editPdfMetadata() {

		String actionChoice;
		String originalPdf = "";
		String author = "";
		String keywords = "";
		String subject = "";
		String title = "";

		String tempAuthor = "";
		String tempKeywords = "";
		String tempSubject = "";
		String tempTitle = "";

		boolean run = true;

		while(run) {
			if(!originalPdf.equals("")) {
		        Document pdfDocument = new Document(originalPdf);
		        DocumentInfo docInfo = pdfDocument.getInfo();
				if(!tempAuthor.equals("")) {
					author = tempAuthor;
				} else {
					author = docInfo.getAuthor();
				}
				if(!tempKeywords.equals("")) {
					keywords = tempKeywords;
				} else {
					keywords = docInfo.getKeywords();
				}
				if(!tempSubject.equals("")) {
					subject = tempSubject;
				} else {
					subject = docInfo.getSubject();
				}
				if(!tempTitle.equals("")) {
					title = tempTitle;
				} else {
					title = docInfo.getTitle();
				}
			}

			editPdfMetadataTitle.printTitle(originalPdf, author, keywords, subject, title);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				originalPdf = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2") && originalPdf != "") {
				tempAuthor = chooseStringInput.getStringInput();
			}  else if(actionChoice.toLowerCase().trim().equals("3") && originalPdf != "") {
				tempKeywords = chooseStringInput.getStringInput();
			}  else if(actionChoice.toLowerCase().trim().equals("4") && originalPdf != "") {
				tempSubject = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("5") && originalPdf != "") {
				tempTitle = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("6") && originalPdf != "" && originalPdf != "exit") {
				setPDFFileInformation(originalPdf, author, keywords, subject, title);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("7")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}


    private void setPDFFileInformation(String targetPdf, String author, String keywords, String subject, String title) {
        Document pdfDocument = new Document(targetPdf);
        DocumentInfo docInfo = pdfDocument.getInfo();

        if(author != null) {
            docInfo.setAuthor(author);
        }
        if(keywords != null) {
            docInfo.setKeywords(keywords);
        }
        if(subject != null) {
            docInfo.setSubject(subject);
        }
        if(title != null) {
            docInfo.setTitle(title);
        }

        docInfo.setModDate(new java.util.Date());

        // Show document information
        System.out.println(targetPdf + "'s metadata set to:");
		System.out.println("------------------------------------------------------------------------");
        System.out.println("Author: " + docInfo.getAuthor());
        System.out.println("Creation Date: " + docInfo.getCreationDate());
        System.out.println("Keywords: " + docInfo.getKeywords());
        System.out.println("Modify Date: " + docInfo.getModDate());
        System.out.println("Subject: " + docInfo.getSubject());
        System.out.println("Title: " + docInfo.getTitle());

        pdfDocument.save(targetPdf);

        pdfDocument.close();
    }

}
