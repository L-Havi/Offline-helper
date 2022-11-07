package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class MassRenameFilesPanel extends ContentPanelBase {

	JLabel title;
	
	public MassRenameFilesPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
