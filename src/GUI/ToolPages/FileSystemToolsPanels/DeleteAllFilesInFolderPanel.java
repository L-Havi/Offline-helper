package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class DeleteAllFilesInFolderPanel extends ContentPanelBase {

	JLabel title;
	
	public DeleteAllFilesInFolderPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
