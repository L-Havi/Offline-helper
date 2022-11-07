package GUI.ToolPages.EncryptionAndHashingToolsPanels.PasswordToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class CheckPasswordStrengthPanel extends ContentPanelBase {

	JLabel title;
	
	public CheckPasswordStrengthPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
