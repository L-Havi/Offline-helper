package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class DecryptWithPGPPanel2 extends ContentPanelBase {

	JLabel title;
	
	public DecryptWithPGPPanel2(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
