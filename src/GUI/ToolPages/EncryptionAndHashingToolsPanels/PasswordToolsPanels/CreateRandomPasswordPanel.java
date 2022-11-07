package GUI.ToolPages.EncryptionAndHashingToolsPanels.PasswordToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class CreateRandomPasswordPanel extends ContentPanelBase {

	JLabel title;
	
	public CreateRandomPasswordPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
