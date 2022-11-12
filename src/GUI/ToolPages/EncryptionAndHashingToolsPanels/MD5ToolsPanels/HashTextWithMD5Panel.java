package GUI.ToolPages.EncryptionAndHashingToolsPanels.MD5ToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cryptography.MD5Actions.MD5HashString;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class HashTextWithMD5Panel extends ContentPanelBase {

	MD5HashString hashString = new MD5HashString();
	
	JLabel title;	
	
	JTextField text, hashedText;
	
	JPanel titlePanel, buttonPanel;
	JButton startButton = new TopPanelButton("Hash text");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,30);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	JLabel error = new JLabel();
	
	JLabel textLabel = new JLabel("Text: ");
	JLabel hashedTextLabel = new JLabel("Hashed text: ");
	
	public HashTextWithMD5Panel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton && !text.getText().isBlank()) {
			
			String hashText = hashString.hash(text.getText());
			hashedText.setText(hashText);
			
		} else if(e.getSource() == startButton && text.getText().isBlank()) {
			error.setText("Type text to hash!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} 
		
	}
	
	private JPanel getTitlePanel(String titleText) {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(600,50));
		
		title = new JLabel(titleText);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(Component.CENTER_ALIGNMENT);
		title.setFont(titleFont);
		title.setPreferredSize(new Dimension(600, 50));
		
		titlePanel.add(title);
		
		return titlePanel;
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		
		JPanel textPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		JPanel hashedTextPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		textLabel.setLabelFor(text);
		textLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		text = new JTextField();
		text.setFont(defaultFont);
		text.addActionListener(this);

		textPanel.setMaximumSize(buttonsize2);
		textPanel.setLayout(new GridLayout(0,3));
		textPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		textPanel.add(textLabel);
		textPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		textPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		textPanel.add(text);


		hashedTextLabel.setLabelFor(hashedText);
		hashedTextLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		hashedText = new JTextField();
		hashedText.setFont(defaultFont);
		hashedText.addActionListener(this);

		hashedTextPanel.setMaximumSize(buttonsize2);
		hashedTextPanel.setLayout(new GridLayout(0,3));
		hashedTextPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		hashedTextPanel.add(hashedTextLabel);
		hashedTextPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		hashedTextPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		hashedTextPanel.add(hashedText);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(textPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		bPanel.add(hashedTextPanel);
		bPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		return bPanel;
	}
	
	
	
}
