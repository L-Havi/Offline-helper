package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class EncryptWithPGPPanel extends ContentPanelBase {

	JLabel title;
	
	public EncryptWithPGPPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
