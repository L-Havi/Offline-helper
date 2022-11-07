package GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class CreateSHAChecksumPanel extends ContentPanelBase {

	JLabel title;
	
	public CreateSHAChecksumPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
