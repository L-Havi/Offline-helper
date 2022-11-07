package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class SortFilesByExtensionPanel extends ContentPanelBase {

	JLabel title;
	
	public SortFilesByExtensionPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
