package Titles.ToolTitles.PdfTitles;

public class SplitPdfActionTitle {

	public void printTitle(String originalPdf, String outputFolder, String outputFileName, String excludePages){
			
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\tSplit PDF File");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set File to Split");
		System.out.println("	Current File: " + originalPdf + "\n");
		System.out.println("2. 	Set Output Folder for Split PDFs");
		System.out.println("	Current Folder: " + outputFolder + "\n");
		System.out.println("3. 	Set Name for Output Files (Optional, Default: splitResult)");
		System.out.println("	Current Name: " + outputFileName + "\n");
		System.out.println("4. 	Included Pages (Optional, Default: All pages)");
		System.out.println("	Current Pages: " + excludePages + "\n");
		System.out.println("5. 	Start\n");			
		System.out.println("6. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
}
