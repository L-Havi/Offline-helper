package GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class HashTextWithSHAPanel extends ContentPanelBase {

	JLabel title;
	
	public HashTextWithSHAPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
