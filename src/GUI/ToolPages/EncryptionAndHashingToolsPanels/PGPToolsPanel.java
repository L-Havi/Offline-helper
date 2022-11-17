package GUI.ToolPages.EncryptionAndHashingToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class PGPToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public PGPToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
