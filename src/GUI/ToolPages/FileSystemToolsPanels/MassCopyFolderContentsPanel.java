package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class MassCopyFolderContentsPanel extends ContentPanelBase {

	JLabel title;
	
	public MassCopyFolderContentsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
