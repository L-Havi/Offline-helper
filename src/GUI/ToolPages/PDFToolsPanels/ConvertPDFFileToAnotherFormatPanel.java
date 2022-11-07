package GUI.ToolPages.PDFToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class ConvertPDFFileToAnotherFormatPanel extends ContentPanelBase {

	JLabel title;
	
	public ConvertPDFFileToAnotherFormatPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
