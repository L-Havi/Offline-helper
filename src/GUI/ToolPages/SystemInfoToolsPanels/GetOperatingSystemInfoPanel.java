package GUI.ToolPages.SystemInfoToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GetOperatingSystemInfoPanel extends ContentPanelBase {

	JLabel title;
	
	public GetOperatingSystemInfoPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
