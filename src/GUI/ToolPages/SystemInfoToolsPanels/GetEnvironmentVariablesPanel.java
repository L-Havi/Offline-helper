package GUI.ToolPages.SystemInfoToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GetEnvironmentVariablesPanel extends ContentPanelBase {

	JLabel title;
	
	public GetEnvironmentVariablesPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
