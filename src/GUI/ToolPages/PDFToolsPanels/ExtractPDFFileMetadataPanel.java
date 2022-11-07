package GUI.ToolPages.PDFToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class ExtractPDFFileMetadataPanel extends ContentPanelBase {

	JLabel title;
	
	public ExtractPDFFileMetadataPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
