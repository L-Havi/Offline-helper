package GUI.ToolPages.ToolCollectionPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class SystemInfoToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public SystemInfoToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
}
