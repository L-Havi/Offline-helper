package GUI.ToolPages.EncryptionAndHashingToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class AESToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public AESToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
