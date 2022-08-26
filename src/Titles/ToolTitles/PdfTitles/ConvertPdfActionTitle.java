package Titles.ToolTitles.PdfTitles;

public class ConvertPdfActionTitle {

	public void printTitle(String originalPdf, String convertFormat){
		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\tConvert PDF to other Format");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set PDF File to Convert");
		System.out.println("	Current File: " + originalPdf + "\n");
		System.out.println("2. 	Set the new Format for PDF File");
		System.out.println("	Current Format: " + convertFormat + "\n");
		System.out.println("3. 	Start\n");			
		System.out.println("4. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
}
