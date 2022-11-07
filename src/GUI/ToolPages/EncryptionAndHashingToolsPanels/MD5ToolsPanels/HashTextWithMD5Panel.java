package GUI.ToolPages.EncryptionAndHashingToolsPanels.MD5ToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class HashTextWithMD5Panel extends ContentPanelBase {

	JLabel title;
	
	public HashTextWithMD5Panel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
