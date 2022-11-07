package GUI.ToolPages.EncryptionAndHashingToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class SHAToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public SHAToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
