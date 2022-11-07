package GUI.ToolPages.PDFToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class SplitPDFFilePanel extends ContentPanelBase {

	JLabel title;
	
	public SplitPDFFilePanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
