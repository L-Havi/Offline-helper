package Titles.ToolTitles.PdfTitles;

public class EditPdfMetadataTitle {

	public void printTitle(String originalPdf, String author, String keywords, String Subject, String title) {
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tEdit PDF Metadata");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set PDF File to Edit");
		System.out.println("	Current File: " + originalPdf + "\n");
		System.out.println("2. 	Edit Author (Optional, Default: Current Author)");
		System.out.println("	Current Author: " + author + "\n");
		System.out.println("3. 	Edit Keywords (Optional, Default: Current Keywords)");
		System.out.println("	Current Keywords: " + keywords + "\n");
		System.out.println("4. 	Edit Subject (Optional, Default: Current Subject)");
		System.out.println("	Current Subject: " + Subject + "\n");
		System.out.println("5. 	Edit Title (Optional, Default: Current Title)");
		System.out.println("	Current Title: " + title + "\n");
		System.out.println("6. 	Save\n");
		System.out.println("7. 	Exit to Main Menu\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}

}
