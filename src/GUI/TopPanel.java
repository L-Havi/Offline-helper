package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JButton exitButton = new TopPanelButton("Exit");
	JLabel mainTitle = new TopPanelLabel("Offline Toolkit v2.0.0");
	JLabel filler = new TopPanelLabel("WIP");
	
	public TopPanel() {
		
		this.setLayout(new GridLayout(1,5));
		this.setPreferredSize(new Dimension(100,100));
		this.setBackground(Color.decode("#0D1B2A"));
		
		exitButton.addActionListener(this);
		
		this.add(filler);
		this.add(mainTitle);
		this.add(exitButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) {
			
			System.exit(0);
			
		}
	}
	
}
