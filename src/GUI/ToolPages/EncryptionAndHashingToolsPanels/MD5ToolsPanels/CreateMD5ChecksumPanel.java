package GUI.ToolPages.EncryptionAndHashingToolsPanels.MD5ToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class CreateMD5ChecksumPanel extends ContentPanelBase {

	JLabel title;
	
	public CreateMD5ChecksumPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
