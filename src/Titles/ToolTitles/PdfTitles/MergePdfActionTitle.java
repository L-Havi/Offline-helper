package Titles.ToolTitles.PdfTitles;

public class MergePdfActionTitle {

	public void printTitle(String outputFolder, String[] mergeFiles, String outputFileName){
		String allMergeFiles = "";
		
		if(mergeFiles.length > 0){
			int count = 1;
			for(String file : mergeFiles) {
				if(!(count == 1)) {
					allMergeFiles = allMergeFiles + ", " + file;
				} else {
					allMergeFiles = file;
				}
				count++;
			}
		}
			
		System.out.println("");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tMerge PDF Files");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please type one of the following numbers to input parameters (Only number):\nAll parameters are mandatory unless stated otherwise");
		System.out.println("\n1.	Set Output Folder");
		System.out.println("	Current Folder: " + outputFolder + "\n");
		System.out.println("2. 	Set PDF Files to Merge (All Filepaths)");
		System.out.println("	Current Files: " + allMergeFiles + "\n");
		System.out.println("3. 	Set Name for Output File (Optional, Default: mergeOutput)");
		System.out.println("	Current Name: " + outputFileName + "\n");
		System.out.println("4. 	Start\n");			
		System.out.println("5. 	Exit to Main Menu\n");					
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
	}
	
}
