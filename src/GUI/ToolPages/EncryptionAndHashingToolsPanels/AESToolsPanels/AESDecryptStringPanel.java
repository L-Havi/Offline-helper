package GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class AESDecryptStringPanel extends ContentPanelBase {

	JLabel title;
	
	public AESDecryptStringPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
