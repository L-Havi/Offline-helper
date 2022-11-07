package GUI.ToolPages.ToolCollectionPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class FileSystemToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public FileSystemToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
