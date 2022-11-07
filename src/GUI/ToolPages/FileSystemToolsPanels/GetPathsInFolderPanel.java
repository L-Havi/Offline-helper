package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GetPathsInFolderPanel extends ContentPanelBase {

	JLabel title;
	
	public GetPathsInFolderPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
