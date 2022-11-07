package GUI.ToolPages.PDFToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class ExtractTextFromPDFFilePanel extends ContentPanelBase {

	JLabel title;
	
	public ExtractTextFromPDFFilePanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
