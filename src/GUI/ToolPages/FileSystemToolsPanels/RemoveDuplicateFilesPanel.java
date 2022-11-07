package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class RemoveDuplicateFilesPanel extends ContentPanelBase {

	JLabel title;
	
	public RemoveDuplicateFilesPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
