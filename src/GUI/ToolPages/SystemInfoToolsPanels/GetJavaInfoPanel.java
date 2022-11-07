package GUI.ToolPages.SystemInfoToolsPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class GetJavaInfoPanel extends ContentPanelBase {

	JLabel title;
	
	public GetJavaInfoPanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
