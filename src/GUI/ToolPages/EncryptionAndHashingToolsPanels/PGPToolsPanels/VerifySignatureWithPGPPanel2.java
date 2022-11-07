package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class VerifySignatureWithPGPPanel2 extends ContentPanelBase {

	JLabel title;
	
	public VerifySignatureWithPGPPanel2(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
