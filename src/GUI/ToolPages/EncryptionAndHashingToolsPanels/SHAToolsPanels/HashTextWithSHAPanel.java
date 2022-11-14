package GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cryptography.SHAActions.SHAHashString;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class HashTextWithSHAPanel extends ContentPanelBase {

	SHAHashString hashString = new SHAHashString();
	
	JLabel title;	
	
	String[] shaStrings = { "SHA-224", "SHA-256", "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256", "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512" };
	
	JTextField text, hashedText;
	
	JPanel titlePanel, buttonPanel;
	JButton startButton = new TopPanelButton("Hash text");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,30);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	JLabel error = new JLabel();
	
	JCheckBox includeSalt;
	
	JComboBox hashingAlgorithm = new JComboBox(shaStrings);
	
	JLabel textLabel = new JLabel("Text: ");
	JLabel inputTextLabel = new JLabel("Input text: ");
	JLabel inputTextLengthLabel = new JLabel("Input text length: ");
	JLabel saltLabel = new JLabel("Salt: ");
	JLabel saltLengthLabel = new JLabel("Salt length: ");
	JLabel fullTextLabel = new JLabel("Full Hashed text: ");
	JLabel fullTextLengthLabel = new JLabel("Full hashed text length: ");
	JLabel shaHexLabel = new JLabel("SHA (hex): ");
	JLabel shaLengthLabel = new JLabel("SHA (length): ");
	JLabel shaLabel = new JLabel("Hashing Algorithm: ");
	
	public HashTextWithSHAPanel(String titleText) {
		
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
			
			int salt = 0;
			
			if(includeSalt.isSelected()) {
				salt = 1;
			}
			
	        String sha = (String)hashingAlgorithm.getSelectedItem();
			
			String[] results = hashString.hash(text.getText(), sha, salt);
	        
			if(salt == 1) {
				inputTextLabel.setText("Input text: " + results[0]);
				inputTextLengthLabel.setText("Input text length: " + results[1]);
				saltLabel.setText("Salt: " + results[2]);
				saltLengthLabel.setText("Salt length: " + results[3]);
				fullTextLabel.setText("Full hashed text: " + results[4]);
				fullTextLengthLabel.setText("Full hashed text length: "  + results[5]);
				shaHexLabel.setText(sha + " (hex): "   + results[6]);
				shaLengthLabel.setText(sha + " (length): "   + results[7]);
			} else {
				inputTextLabel.setText("Input text: " + results[0]);
				inputTextLengthLabel.setText("Input text length: " + results[1]);
				shaHexLabel.setText(sha + " (hex): "   + results[2]);
				shaLengthLabel.setText(sha + " (length): "   + results[3]);
			}
			
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
		JPanel chooseAlgorithmPanel = new JPanel();
		JPanel chosenAlgorithmPanel = new JPanel();
		JPanel includeSaltPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		JPanel hashedTextPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		hashingAlgorithm.setSelectedIndex(0);
		hashingAlgorithm.setPreferredSize(buttonsize2);
		hashingAlgorithm.addActionListener(this);
		
		shaLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		chosenAlgorithmPanel.add(shaLabel);
		chooseAlgorithmPanel.add(hashingAlgorithm);
		
		includeSalt = new JCheckBox("Include Random Salt");
		includeSalt.setFont(defaultFont);
		includeSalt.setSelected(false);
		includeSalt.addActionListener(this);
		
		includeSaltPanel.add(includeSalt);
		
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

		hashedTextPanel.setLayout(new GridLayout(0,2));
		hashedTextPanel.add(inputTextLabel);
		hashedTextPanel.add(inputTextLengthLabel);
		hashedTextPanel.add(saltLabel);
		hashedTextPanel.add(saltLengthLabel);
		hashedTextPanel.add(fullTextLabel);
		hashedTextPanel.add(fullTextLengthLabel);
		hashedTextPanel.add(shaHexLabel);
		hashedTextPanel.add(shaLengthLabel);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(textPanel);
		bPanel.add(chosenAlgorithmPanel);
		bPanel.add(chooseAlgorithmPanel);
		bPanel.add(includeSaltPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		bPanel.add(hashedTextPanel);
		bPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		return bPanel;
	}
	
}
