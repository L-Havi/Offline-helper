package GUI.ToolPages.EncryptionAndHashingToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class PasswordToolsPanel extends ContentPanelBase {

	JLabel title;
	
	public PasswordToolsPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
