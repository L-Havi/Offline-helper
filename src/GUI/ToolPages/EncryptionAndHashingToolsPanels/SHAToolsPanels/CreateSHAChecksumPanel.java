package GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cryptography.SHAActions.CreateSHAChecksum;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class CreateSHAChecksumPanel extends ContentPanelBase {

	CreateSHAChecksum create = new CreateSHAChecksum();
	
	String[] shaStrings = { "SHA-224", "SHA-256", "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256", "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512" };
	
	JLabel title;	
	
	JTextField checksum;
	
	JPanel titlePanel, buttonPanel;
	JButton startButton = new TopPanelButton("Create File's checksum");

	JFileChooser fileChooser = new JFileChooser();
	
	JButton chooseFileButton = new TopPanelButton("Choose a File");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,30);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file;
	
	JComboBox hashingAlgorithm = new JComboBox(shaStrings);
	
	JLabel error = new JLabel();
	
	JLabel chosenFileLabel = new JLabel("File: ");
	JLabel checksumLabel = new JLabel("Checksum: ");
	JLabel shaLabel = new JLabel("Hashing Algorithm: ");
	
	public CreateSHAChecksumPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == chooseFileButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	file = fileChooser.getSelectedFile();
            	chosenFileLabel.setText("File: " + file.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && file != null) {
			
	        String sha = (String)hashingAlgorithm.getSelectedItem();
			
	        String result = create.printFileChecksum(file.getAbsolutePath(), sha);
	        
	        checksum.setText(result);
			
		} else if(e.getSource() == startButton && file == null) {
			error.setText("Choose a File!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} 
		
	}
	
	private JPanel getTitlePanel(String titleText) {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		
		title = new JLabel(titleText);
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		titlePanel.add(title);
		
		return titlePanel;
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		
		JPanel chooseFilePanel = new JPanel();
		JPanel chosenFilePanel = new JPanel();
		JPanel chooseAlgorithmPanel = new JPanel();
		JPanel chosenAlgorithmPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		JPanel checkSumPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		hashingAlgorithm.setSelectedIndex(0);
		hashingAlgorithm.setPreferredSize(buttonsize2);
		hashingAlgorithm.addActionListener(this);
		
		shaLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		chosenAlgorithmPanel.add(shaLabel);
		chooseAlgorithmPanel.add(hashingAlgorithm);
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		chooseFileButton.addActionListener(this);
		chooseFileButton.setPreferredSize(buttonsize2);
		chooseFileButton.setFont(defaultFont);
		
		chooseFilePanel.add(chooseFileButton);
		
		chosenFileLabel.setFont(defaultFont);
		chosenFilePanel.add(chosenFileLabel);
		
		checksum = new JTextField();
		checksum.setFont(defaultFont);
		checksum.addActionListener(this);

		checksumLabel.setLabelFor(checksum);
		checksumLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		checkSumPanel.setMaximumSize(buttonsize2);
		checkSumPanel.setLayout(new GridLayout(0,3));
		checkSumPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		checkSumPanel.add(checksumLabel);
		checkSumPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		checkSumPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		checkSumPanel.add(checksum);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(chosenAlgorithmPanel);
		bPanel.add(chooseAlgorithmPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		bPanel.add(checkSumPanel);
		bPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		return bPanel;
	}
	
}
