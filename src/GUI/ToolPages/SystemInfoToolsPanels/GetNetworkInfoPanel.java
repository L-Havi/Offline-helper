package GUI.ToolPages.SystemInfoToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GetNetworkInfoPanel extends ContentPanelBase {

	JLabel title;
	
	public GetNetworkInfoPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
