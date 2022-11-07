package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GeneratePGPKeypairPanel extends ContentPanelBase {

	JLabel title;
	
	public GeneratePGPKeypairPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
