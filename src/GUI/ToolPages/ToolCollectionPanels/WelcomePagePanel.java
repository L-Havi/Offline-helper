package GUI.ToolPages.ToolCollectionPanels;

import javax.swing.JLabel;

import GUI.ContentPanelBase;

public class WelcomePagePanel extends ContentPanelBase {

	JLabel title;
	
	public WelcomePagePanel(String titleText) {
		
		
		title = new JLabel(titleText);
		this.add(title);
		
		
	}
	
}
