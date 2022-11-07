package GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class AESEncryptStringPanel extends ContentPanelBase {

	JLabel title;
	
	public AESEncryptStringPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
