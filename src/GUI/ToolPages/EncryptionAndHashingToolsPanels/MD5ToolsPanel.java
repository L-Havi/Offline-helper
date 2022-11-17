package GUI.ToolPages.EncryptionAndHashingToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class MD5ToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public MD5ToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
