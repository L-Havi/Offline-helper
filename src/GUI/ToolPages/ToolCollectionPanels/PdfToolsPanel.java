package GUI.ToolPages.ToolCollectionPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class PdfToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public PdfToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
}
