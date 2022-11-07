package GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class AESDecryptFilePanel extends ContentPanelBase {

	JLabel title;
	
	public AESDecryptFilePanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
