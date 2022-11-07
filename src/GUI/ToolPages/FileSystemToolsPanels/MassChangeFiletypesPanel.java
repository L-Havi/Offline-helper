package GUI.ToolPages.FileSystemToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class MassChangeFiletypesPanel extends ContentPanelBase {

	JLabel title;
	
	public MassChangeFiletypesPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
