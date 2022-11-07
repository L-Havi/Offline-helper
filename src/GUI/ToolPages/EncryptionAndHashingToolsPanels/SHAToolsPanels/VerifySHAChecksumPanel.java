package GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class VerifySHAChecksumPanel extends ContentPanelBase {

	JLabel title;
	
	public VerifySHAChecksumPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
