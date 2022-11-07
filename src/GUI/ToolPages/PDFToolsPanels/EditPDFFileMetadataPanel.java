package GUI.ToolPages.PDFToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class EditPDFFileMetadataPanel extends ContentPanelBase {

	JLabel title;
	
	public EditPDFFileMetadataPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
