package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GetFolderSizeAndFileCountPanel extends ContentPanelBase {

	JLabel title;
	
	public GetFolderSizeAndFileCountPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
