package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class UnzipOrZipFilesPanel extends ContentPanelBase {

	JLabel title;
	
	public UnzipOrZipFilesPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
