package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SidePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton fileSystemToolsButton = new SidePanelButton("File System Tools");
	JButton encryptionAndHashingToolsButton = new SidePanelButton("Encryption & Hashing Tools");
	JButton systemInfoButton = new SidePanelButton("System Info");
	JButton pdfToolsButton = new SidePanelButton("PDF Tools");
	
	public SidePanel(JPanel centerPanel) {
		
		this.setLayout(new GridLayout(4,1));
		this.setBackground(Color.decode("#1B263B"));
		this.setPreferredSize(new Dimension(300,100));
		
		fileSystemToolsButton.addActionListener(this);
		
		this.add(fileSystemToolsButton);
		this.add(encryptionAndHashingToolsButton);
		this.add(systemInfoButton);
		this.add(pdfToolsButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == fileSystemToolsButton) {
			JButton button = (JButton)e.getSource();
			JPanel buttonPanel = (JPanel)button.getParent();
			JFrame rootFrame = (JFrame) buttonPanel.getParent();
		}
		
	}
	
}
