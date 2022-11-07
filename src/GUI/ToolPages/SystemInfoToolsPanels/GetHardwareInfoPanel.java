package GUI.ToolPages.SystemInfoToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GetHardwareInfoPanel extends ContentPanelBase {

	JLabel title;
	
	public GetHardwareInfoPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
