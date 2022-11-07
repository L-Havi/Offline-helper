package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class SignWithPGPPanel extends ContentPanelBase {

	JLabel title;
	
	public SignWithPGPPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
