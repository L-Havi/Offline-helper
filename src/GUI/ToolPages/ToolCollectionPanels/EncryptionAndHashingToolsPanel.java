package GUI.ToolPages.ToolCollectionPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class EncryptionAndHashingToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public EncryptionAndHashingToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
