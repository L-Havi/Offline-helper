package GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class AESEncryptFilePanel extends ContentPanelBase {

	JLabel title;
	
	public AESEncryptFilePanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
